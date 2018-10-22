package br.edu.fatec.aula.lendingstuffapp.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.edu.fatec.aula.lendingstuffapp.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView descriptionTextView;
    private TextView nameTextView;
    private TextView dateTextView;
    private Button editButton;
    private Button deleteButton;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        descriptionTextView = itemView.findViewById(R.id.description);
        nameTextView = itemView.findViewById(R.id.name);
        dateTextView = itemView.findViewById(R.id.date);
        editButton = itemView.findViewById(R.id.edit_button);
        deleteButton = itemView.findViewById(R.id.delete_button);
    }

    public TextView getDescriptionTextView() {
        return descriptionTextView;
    }

    public TextView getNameTextView() {
        return nameTextView;
    }

    public TextView getDateTextView() {
        return dateTextView;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
