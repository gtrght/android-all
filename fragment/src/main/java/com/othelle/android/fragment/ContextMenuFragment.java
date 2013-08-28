package com.othelle.android.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.othelle.android.action.Action;
import com.othelle.android.action.UIAction;
import com.othelle.java.util.Strings;

/**
 * author: v.vlasov
 */
public class ContextMenuFragment extends ListDialogFragment {
    private UIAction[] actions;

    public ContextMenuFragment() {
        setShowDivider(true);
    }

    @Override
    protected ListAdapter getAdapter() {
        return new ArrayAdapter<UIAction>(getActivity(), -1, actions) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null)
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.menu_item_layout, null);

                ImageView icon = (ImageView) convertView.findViewById(R.id.menu_icon);
                TextView titleView = (TextView) convertView.findViewById(R.id.menu_title);
                TextView subTitleView = (TextView) convertView.findViewById(R.id.menu_subtitle);

                UIAction item = getItem(position);

                if (item.getIcon() != null) {
                    icon.setVisibility(View.VISIBLE);
                    icon.setImageDrawable(item.getIcon());
                } else
                    icon.setVisibility(View.INVISIBLE);

                titleView.setText(item.getTitle());

                if (!Strings.isEmpty(item.getSubTitle())) {
                    subTitleView.setText(item.getSubTitle());
                    subTitleView.setVisibility(View.VISIBLE);
                } else
                    subTitleView.setVisibility(View.GONE);

                return convertView;
            }
        };
    }

    @Override
    protected AdapterView.OnItemClickListener getItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Action action = (Action) adapterView.getItemAtPosition(index);
                action.performAction();
                dismiss();
            }
        };
    }

    public static ContextMenuFragment create(UIAction... actions) {
        ContextMenuFragment fragment = new ContextMenuFragment();
        fragment.setActions(actions);

        return fragment;
    }

    @Override
    protected ButtonCallback getNegativeButton() {
//        return new ButtonCallback() {
//            @Override
//            public CharSequence getName() {
//                return getActivity().getText(android.R.string.cancel);
//            }
//
//            @Override
//            public void actionPerformed(DialogInterface result) {
//                result.cancel();
//            }
//        };
        return null;
    }

    @Override
    protected ButtonCallback getNeutralButton() {
        return null;
    }

    @Override
    protected ButtonCallback getPositiveButton() {
        return null;
    }

    public void setActions(UIAction[] actions) {
        this.actions = actions;
    }
}
