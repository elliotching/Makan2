package fcsit.makan_place;

/**
 * Created by elliotching on 04-Jul-16.
 */

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CustomDrawerAdapter extends ArrayAdapter<ITEM> {

    Context context;
    List<ITEM> drawerItemList;
    int layoutResID;

    public CustomDrawerAdapter(Context context, int layoutResourceID,//R.layout.custom_drawer_item
                               List<ITEM> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
//            LinearLayout mLinearLayout = (LinearLayout) view.findViewById(R.id.drawer_header_layout);
//            mLinearLayout.setVisibility(View.GONE);

            drawerHolder.mText = (TextView) view.findViewById(R.id.m_text_item);



            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }
        drawerHolder.mText.setText(drawerItemList.get(position).mItem);

//        LinearLayout.LayoutParams mParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);
//        drawerHolder.mText.setLayoutParams(mParam);
        drawerHolder.mText.setGravity(Gravity.CENTER_VERTICAL);

        return view;
    }

    private static class DrawerItemHolder {
        TextView mText;
    }
}