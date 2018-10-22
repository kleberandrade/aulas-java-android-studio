package br.fatec.aula.tictactoeapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import br.fatec.aula.tictactoeapp.R;

public class AlertDialogFragment extends DialogFragment {

    private static final String EXTRA_TITLE = "TITLE";
    private static final String EXTRA_BUTTON = "BUTTON";
    private static final String EXTRA_MESSAGE = "MESSAGE";

    public AlertDialogFragment() {
    }

    public static AlertDialogFragment newInstance(String title, String message, String button) {
        AlertDialogFragment dialog = new AlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        bundle.putString(EXTRA_MESSAGE, message);
        bundle.putString(EXTRA_BUTTON, button);
        dialog.setArguments(bundle);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final Bundle bundle = getArguments();

        View view = inflater.inflate(R.layout.fragment_dialog_result, container, true);

        TextView titleEditText = view.findViewById(R.id.title);
        titleEditText.setText(bundle.getString(EXTRA_TITLE));

        TextView messageEditText = view.findViewById(R.id.message);
        messageEditText.setText(bundle.getString(EXTRA_MESSAGE));

        Button positiveButton = view.findViewById(R.id.positive_button);
        positiveButton.setText(bundle.getString(EXTRA_BUTTON));

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }
}
