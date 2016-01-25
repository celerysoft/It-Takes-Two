package com.celerysoft.ittakestwo.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.celerysoft.ittakestwo.R;
import com.celerysoft.ittakestwo.adapters.SocialSharingListViewAdapter;
import com.celerysoft.materialdesigndialog.MaterialDesignDialog;

/**
 * Created by Celery on 16/1/11.
 * SocialSharingUtil, contain all of the action about social sharing.
 */
public class SocialSharingUtil {
    public static MaterialDesignDialog createSocialSharingDialog(Context context) {
        final MaterialDesignDialog dialog = new MaterialDesignDialog(context);

        ListView listView = new ListView(context);
        listView.setDividerHeight(0);
        listView.setAdapter(new SocialSharingListViewAdapter(context));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case SocialSharingListViewAdapter.WECHAT:
                        shareToWechatFriend();
                        break;
                    case SocialSharingListViewAdapter.WECHAT_TIMELINE:
                        shareToTimelineOfWechat();
                        break;
                    case SocialSharingListViewAdapter.QQ:
                        shareToQqFriend();
                        break;
                    case SocialSharingListViewAdapter.QZONE:
                        shareToQzone();
                        break;
                }
                dialog.dismiss();
            }
        });

        dialog.setTitle(R.string.social_sharing_dialog_title)
                .setContentView(listView)
                .setCanceledOnTouchOutside(true);

        return dialog;
    }

    private static void shareToWechatFriend() {

    }

    private static void shareToTimelineOfWechat() {

    }

    private static void shareToQqFriend() {

    }

    private static void shareToQzone() {

    }
}
