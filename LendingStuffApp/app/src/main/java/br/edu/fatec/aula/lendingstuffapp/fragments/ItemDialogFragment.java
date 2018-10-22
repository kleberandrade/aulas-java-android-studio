package br.edu.fatec.aula.lendingstuffapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Locale;

import br.edu.fatec.aula.lendingstuffapp.R;
import br.edu.fatec.aula.lendingstuffapp.models.Item;
import br.edu.fatec.aula.lendingstuffapp.utils.DatePickerDialogHelper;

public class ItemDialogFragment extends DialogFragment {

    public interface NoticeDialogListener {
        void onDialogPositiveClick(Item item, int position);
    }

    public static final String CREATE_TAG = "CREATE";
    public static final String EDIT_TAG = "EDIT";

    private static final String EXTRA_TITLE = "TITLE";
    private static final String EXTRA_POSITIVE_BUTTON = "POSITIVE_BUTTON";
    private static final String EXTRA_ITEM = "SERIALIZABLE_ITEM";
    private static final String EXTRA_POSITION = "ITEM_POSITION";

    private NoticeDialogListener listener;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;

    private Item currentItem = null;

    public ItemDialogFragment() {
    }

    public static ItemDialogFragment newInstance(String title, String positiveButton, Item item, int position) {
        ItemDialogFragment dialog = new ItemDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        bundle.putString(EXTRA_POSITIVE_BUTTON, positiveButton);
        bundle.putSerializable(EXTRA_ITEM, item);
        bundle.putInt(EXTRA_POSITION, position);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static ItemDialogFragment newInstance(String title, String positiveButton) {
        return newInstance(title, positiveButton, null, -1);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Context context = getActivity();
        final Bundle bundle = getArguments();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(bundle.getString(EXTRA_TITLE));
        builder.setPositiveButton(bundle.getString(EXTRA_POSITIVE_BUTTON), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentItem.setName(nameEditText.getText().toString());
                currentItem.setDescription(descriptionEditText.getText().toString());
                currentItem.setDate(dateEditText.getText().toString());
                listener.onDialogPositiveClick(currentItem, bundle.getInt(EXTRA_POSITION));
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_create_item, null, true);
        builder.setView(view);

        setView(view, bundle);

        DatePickerDialogHelper.setDatePickerDialog(context, dateEditText, new SimpleDateFormat(context.getString(R.string.date_formatter), new Locale("pt", "BR")));

        return builder.create();
    }

    private void setView(View view, final Bundle bundle) {
        nameEditText = view.findViewById(R.id.name);
        descriptionEditText = view.findViewById(R.id.description);
        dateEditText = view.findViewById(R.id.date);

        if (getTag().equals(EDIT_TAG)) {
            currentItem = (Item) bundle.getSerializable(EXTRA_ITEM);
            nameEditText.setText(currentItem.getName());
            descriptionEditText.setText(currentItem.getDescription());
            dateEditText.setText(currentItem.getDate());
        } else {
            currentItem = new Item();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement NoticeDialogListener");
        }
    }
}

