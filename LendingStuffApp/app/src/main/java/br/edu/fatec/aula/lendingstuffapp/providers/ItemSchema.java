package br.edu.fatec.aula.lendingstuffapp.providers;

public class ItemSchema {

    private ItemSchema(){}

    public static final String TABLE_ITEM = "item";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";

    public static final String CREATE_TABLE_ITEM = "CREATE TABLE " + TABLE_ITEM + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + COLUMN_DATE + " TEXT NOT NULL " + ")";

    public static final String DROP_TABLE_ITEM = "DROP TABLE IF EXISTS " + TABLE_ITEM;

    public static final String WHERE_ID_EQUAL = ItemSchema.COLUMN_ID + " = ? ";

    public static final String SELECT_ALL = "SELECT * FROM " + ItemSchema.TABLE_ITEM;

    public static final String SELECT_BY_ID = "SELECT * FROM " + ItemSchema.TABLE_ITEM + " WHERE " + WHERE_ID_EQUAL;
}
