package org.agoenka.golist.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Author: agoenka
 * Created At: 10/1/2016
 * Version: ${VERSION}
 */

public class DeleteTaskDialogFragment extends DialogFragment {

    public DeleteTaskDialogFragment() {}

    private static final String KEY_TITLE = "title";
    private static final String KEY_POSITION = "position";

    public interface DeleteTaskDialogListener {
        void onDelete(int position);
    }

    private DeleteTaskDialogListener listener;

    public void setDeleteTaskDialogListener(DeleteTaskDialogListener listener) {
        this.listener = listener;
    }

    public static DeleteTaskDialogFragment newInstance (String title, int position) {
        DeleteTaskDialogFragment fragment = new DeleteTaskDialogFragment();
        fragment.setArguments(new Bundle());
        fragment.getArguments().putString(KEY_TITLE, title);
        fragment.getArguments().putInt(KEY_POSITION, position);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String title = getArguments().getString(KEY_TITLE);
        final int position = getArguments().getInt(KEY_POSITION);
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onDelete(position);
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
    }

}
