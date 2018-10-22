package br.edu.fatec.aula.lendingstuffapp.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.aula.lendingstuffapp.models.Item;

public class ItemDAO implements SQLiteGenericDAO<Item> {

    private LendingStuffSQLHelper helper;

    public ItemDAO(Context context) {
        this.helper = new LendingStuffSQLHelper(context);
    }

    @Override
    public long create(Item item) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(item);
        long id = database.insert(ItemSchema.TABLE_ITEM, null, contentValues);
        if (id != -1) {
            item.setId(id);
            Log.e(LendingStuffSQLHelper.DATABASE_NAME, item.toString());
        }

        database.close();
        return id;
    }

    @Override
    public void update(Item item) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(item);
        database.update(ItemSchema.TABLE_ITEM, contentValues, ItemSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(item.getId())});
        database.close();
    }

    @Override
    public void delete(Item item) {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(ItemSchema.TABLE_ITEM, ItemSchema.WHERE_ID_EQUAL, new String[]{String.valueOf(item.getId())});
        database.close();
    }

    @Override
    public Item searchById(long id) {

        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(ItemSchema.SELECT_BY_ID, new String[]{String.valueOf(id)});

        if (cursor != null)
            cursor.moveToNext();

        assert cursor != null;
        Item item = getItemFromCursor(cursor);

        cursor.close();
        database.close();
        return item;
    }

    @Override
    public List<Item> searchAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(ItemSchema.SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = getItemFromCursor(cursor);
                list.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return list;
    }

    private Item getItemFromCursor(Cursor cursor){

        long id = Long.parseLong(cursor.getString(0));
        String name = cursor.getString(1);
        String description = cursor.getString(2);
        String date = cursor.getString(3);

        return new Item(id, name, description, date);
    }

    private ContentValues getContentValues(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemSchema.COLUMN_NAME, item.getName());
        contentValues.put(ItemSchema.COLUMN_DESCRIPTION, item.getDescription());
        contentValues.put(ItemSchema.COLUMN_DATE, item.getDate());
        return contentValues;
    }
}
