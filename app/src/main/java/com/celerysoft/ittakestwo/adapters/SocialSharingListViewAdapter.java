package com.celerysoft.ittakestwo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.celerysoft.ittakestwo.R;

import java.util.ArrayList;

/**
 * Created by Celery on 16/1/11.
 *
 */
public class SocialSharingListViewAdapter extends BaseAdapter {
    public static final int WECHAT = 0;
    public static final int WECHAT_TIMELINE = 1;
    public static final int QQ = 2;
    public static final int QZONE = 3;
    private Context mContext;

    private ArrayList<ListItem> mItems;

    public SocialSharingListViewAdapter(Context context) {
        super();

        mItems = createItems();

        mContext = context;
    }

    private ArrayList<ListItem> createItems() {
        ArrayList<ListItem> items = new ArrayList<>();

        ListItem item0 = new ListItem();
        item0.id = WECHAT;
        item0.titleResId = R.string.social_sharing_send_to_wechat_friend;
        item0.iconResId = R.mipmap.ic_wechat;
        items.add(item0);

        ListItem item1 = new ListItem();
        item1.id = WECHAT_TIMELINE;
        item1.titleResId = R.string.social_sharing_send_to_wechat_timeline;
        item1.iconResId = R.mipmap.ic_wechat_discover;
        items.add(item1);

        ListItem item2 = new ListItem();
        item2.id = QQ;
        item2.titleResId = R.string.social_sharing_send_to_qq_friend;
        item2.iconResId = R.mipmap.ic_qq;
        items.add(item2);

        ListItem item3 = new ListItem();
        item3.id = QZONE;
        item3.titleResId = R.string.social_sharing_send_to_qzone;
        item3.iconResId = R.mipmap.ic_qzone_80;
        items.add(item3);

        return items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((ListItem) getItem(position)).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ListItem item = mItems.get(position);
        ImageView icon;
        TextView title;

        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.social_sharing_list_view_item, null);
            //item = new ListItem();
        } else {
            view = convertView;
            //item = (ListItem) view.getTag();
        }

        icon = (ImageView) view.findViewById(R.id.social_sharing_icon);
        icon.setImageResource(item.iconResId);
        title = (TextView) view.findViewById(R.id.social_sharing_title);
        title.setText(item.titleResId);

        view.setTag(item);

        return view;
    }

    public class ListItem {
        public int id;
        public int titleResId;
        public int iconResId;
    }
}
