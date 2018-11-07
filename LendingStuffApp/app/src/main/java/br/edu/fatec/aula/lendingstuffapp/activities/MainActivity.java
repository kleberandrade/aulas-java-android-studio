package br.edu.fatec.aula.lendingstuffapp.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.edu.fatec.aula.lendingstuffapp.R;
import br.edu.fatec.aula.lendingstuffapp.fragments.ItemDialogFragment;
import br.edu.fatec.aula.lendingstuffapp.providers.ItemDAO;
import br.edu.fatec.aula.lendingstuffapp.adapters.ItemAdapter;
import br.edu.fatec.aula.lendingstuffapp.models.Item;

public class MainActivity extends AppCompatActivity implements ItemDialogFragment.NoticeDialogListener, ItemAdapter.ItemAdapterListener {

    private RecyclerView recyclerView;
    private List<Item> items;
    private ItemDAO itemDAO;
    private ItemAdapter adapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListItem();
        setRecyclerView();
        setHideAndShowScrolledFloatingButton();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createItem(v);
            }
        });
    }

    private void setListItem() {
        itemDAO = new ItemDAO(this);
        try {
            items = itemDAO.searchAll();
        } catch (Exception e) {
            Toast.makeText(this, items.size() + " itens carregados", Toast.LENGTH_LONG).show();
        }
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.recycler);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new ItemAdapter(items, this);
        recyclerView.setAdapter(adapter);
    }

    private void setHideAndShowScrolledFloatingButton(){
        fab = findViewById(R.id.add_action_button);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });
    }

    private void showItemDialogFragment(String tag, int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ItemDialogFragment dialog;
        if (tag.equals(ItemDialogFragment.CREATE_TAG)) {
            dialog = ItemDialogFragment.newInstance(getString(R.string.new_landing), getString(R.string.add));
            dialog.setCancelable(false);
        } else {
            dialog = ItemDialogFragment.newInstance(getString(R.string.edit_landing), getString(R.string.update), items.get(position), position);
        }

        dialog.show(fragmentManager, tag);
    }

    @Override
    public void onDialogPositiveClick(Item item, int position) {
        if (item.getId() == 0) {
            itemDAO.create(item);
            items.add(item);
            recyclerView.scrollToPosition(adapter.getItemCount());
            Toast.makeText(this, R.string.create_item_message, Toast.LENGTH_LONG).show();
        } else {
            itemDAO.update(item);
            items.set(position, item);
            Toast.makeText(this, R.string.edit_item_message, Toast.LENGTH_LONG).show();
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEditItemClick(View v, int position) {
        showItemDialogFragment(ItemDialogFragment.EDIT_TAG, position);
    }

    @Override
    public void onDeleteItemClick(View v, final int position) {
        showConfirmDeleteDialog(position);
    }

    private void showConfirmDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.deleting);
        builder.setMessage(R.string.delete_message);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int arg1) {
                itemDAO.delete(items.get(position));
                items.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.create();
        builder.show();
    }

    public void createItem(View view) {
        showItemDialogFragment(ItemDialogFragment.CREATE_TAG, -1);
    }
}
