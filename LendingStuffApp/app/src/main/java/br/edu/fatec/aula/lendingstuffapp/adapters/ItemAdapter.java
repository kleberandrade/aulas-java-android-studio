package br.edu.fatec.aula.lendingstuffapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.edu.fatec.aula.lendingstuffapp.utils.ItemViewHolder;
import br.edu.fatec.aula.lendingstuffapp.R;
import br.edu.fatec.aula.lendingstuffapp.models.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    public interface ItemAdapterListener {
        void onEditItemClick(View v, int position);
        void onDeleteItemClick(View v, int position);
    }

    private List<Item> items;
    private Context context;
    private ItemAdapterListener listener;

    public ItemAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
        this.listener = (ItemAdapterListener)context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_item, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int position) {
        Item item = items.get(position);

        itemViewHolder.getDescriptionTextView().setText(item.getDescription());
        itemViewHolder.getNameTextView().setText(item.getName());
        itemViewHolder.getDateTextView().setText(item.getDate());
        itemViewHolder.getEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditItemClick(v, position);
            }
        });
        itemViewHolder.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}