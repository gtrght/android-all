package com.othelle.android.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.othelle.android.action.Callback;

/**
 * author: v.vlasov
 */
public abstract class ListDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Holo_Panel)
                .setIcon(getIcon())
                .setTitle(getTitle())
                .setView(getContentView());

        ButtonCallback positiveButton = getPositiveButton();
        ButtonCallback neutralButton = getNeutralButton();
        ButtonCallback negativeButton = getNegativeButton();

        if (positiveButton != null) builder.setPositiveButton(positiveButton.getName(), new ButtonWrapper(positiveButton));
        if (neutralButton != null) builder.setNeutralButton(neutralButton.getName(), new ButtonWrapper(neutralButton));
        if (negativeButton != null) builder.setNegativeButton(negativeButton.getName(), new ButtonWrapper(negativeButton));

        return builder.create();
    }

    protected CharSequence getTitle() {
        return null;
    }

    protected Drawable getIcon() {
        return null;
    }

    protected String getHeaderText() {
        return null;
    }

    protected abstract ListAdapter getAdapter();

    protected abstract AdapterView.OnItemClickListener getItemClickListener();


    protected View getContentView() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View content = inflater.inflate(R.layout.dialog_fragment_list, null);

        TextView headerView = (TextView) content.findViewById(R.id.header_text);
        if (getHeaderText() != null)
            headerView.setText(getHeaderText());
        else
            headerView.setVisibility(View.GONE);

        ListView listView = (ListView) content.findViewById(android.R.id.list);

        if (!showDivider)
            listView.setDivider(getActivity().getResources().getDrawable(android.R.color.transparent));

        listView.setAdapter(getAdapter());
        listView.setOnItemClickListener(getItemClickListener());
        return content;
    }

    protected boolean showDivider;

    public ListDialogFragment setShowDivider(boolean showDivider) {
        this.showDivider = showDivider;
        return this;
    }

    protected ButtonCallback getPositiveButton() {
        return null;
    }

    protected ButtonCallback getNeutralButton() {
        return null;
    }

    protected ButtonCallback getNegativeButton() {
        return null;
    }

    static interface ButtonCallback extends Callback<DialogInterface> {
        CharSequence getName();
    }

    private static class ButtonWrapper implements DialogInterface.OnClickListener {
        private ButtonCallback button;

        public ButtonWrapper(ButtonCallback button) {
            this.button = button;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            button.actionPerformed(dialogInterface);
        }
    }
}
