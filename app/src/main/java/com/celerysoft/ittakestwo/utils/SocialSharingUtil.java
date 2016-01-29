package com.celerysoft.ittakestwo.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.celerysoft.ittakestwo.R;
import com.celerysoft.ittakestwo.adapters.SocialSharingListViewAdapter;
import com.celerysoft.materialdesigndialog.MaterialDesignDialog;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Celery on 16/1/11.
 * SocialSharingUtil, contain all of the action about social sharing.
 */
public class SocialSharingUtil {

    private static final String TENCENT_QQ_APP_ID = "1105101856";
    private static final String TENCENT_WECHAT_APP_ID = "wxd95296e7466c05a6";

    private Tencent mTencent;
    private IWXAPI mWechat;
    private Bitmap mShareToWechatThumb;

    private Activity mContext;

    private static SocialSharingUtil sInstance;

    public static synchronized SocialSharingUtil getInstance() {
        if (sInstance == null) {
            sInstance = new SocialSharingUtil();
        }
        return sInstance;
    }

    private SocialSharingUtil() {
    }

    public MaterialDesignDialog createSocialSharingDialog(Activity context) {
        mContext = context;

        if (mTencent == null) {
            mTencent = Tencent.createInstance(TENCENT_QQ_APP_ID, mContext.getApplicationContext());
        }

        if (mWechat == null) {
            mWechat = WXAPIFactory.createWXAPI(mContext, TENCENT_WECHAT_APP_ID, true);
            mWechat.registerApp(TENCENT_WECHAT_APP_ID);
        }

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

    private void shareToWechatFriend() {
        shareToWechat(true);
    }

    private void shareToTimelineOfWechat() {
        shareToWechat(false);
    }

    private void shareToQqFriend() {
        if (!Util.isConnectToInternet(mContext)) {
            DialogUtil.showConnectToInternetDialog(mContext);
            return;
        }

        String imagePath = createScreenshot();

//        // 分享图文消息
//        final Bundle params = new Bundle();
//        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
//        params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
//        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "要分享的摘要");
//        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "http://www.qq.com/news/1.html");
//        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imagePath);
//        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试应用222222");
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
//        mTencent.shareToQQ(mContext, params, null);

        // 分享纯图片
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imagePath);
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        mTencent.shareToQQ(mContext, params, null);
    }

    private void shareToQzone() {
        if (!Util.isConnectToInternet(mContext)) {
            DialogUtil.showConnectToInternetDialog(mContext);
            return;
        }

        String imagePath = createScreenshot();

//        // 分享图文信息
//        ArrayList<String> imagePaths = new ArrayList<>();
//        imagePaths.add(imagePath);
//
//        final Bundle params = new Bundle();
//        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
//        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "标题");//必填
//        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "摘要");//选填
//        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/news/1.html");//必填
//        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imagePaths);
//        mTencent.shareToQzone(mContext, params, null);

        // 分享纯图片
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imagePath);
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        mTencent.shareToQQ(mContext, params, null);
    }

    /**
     * share photo to wechat
     * @param isToFriend true if share to wechat friend, false to share to wechat timeline
     */
    private void shareToWechat(boolean isToFriend) {

        if (!Util.isConnectToInternet(mContext)) {
            DialogUtil.showConnectToInternetDialog(mContext);
            return;
        }

        if (!mWechat.isWXAppInstalled()) {
            DialogUtil.showInstallWechatDialog(mContext);
            return;
        }

        String imagePath = createScreenshot();

        WXImageObject imageObject = new WXImageObject();
        imageObject.imagePath = imagePath;

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imageObject;
        msg.mediaTagName = "name";
        msg.description = "description";
        msg.mediaTagName = "tag";
        if (mShareToWechatThumb != null && !mShareToWechatThumb.isRecycled()) {
            mShareToWechatThumb.recycle();
        }

        mShareToWechatThumb = SocialSharingUtil.getBitmapThumbForWechat(imagePath);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());

        req.message = msg;
        if (isToFriend) {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        }

        mWechat.sendReq(req);
    }

    private String createScreenshot() {
        View view = mContext.findViewById(R.id.playing_card_root);
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = view.getDrawingCache();
        String imagePath = null;
        if (bitmap != null) {
            System.out.println("bitmap got!");
            try {
                String fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".png";

                File dir = mContext.getExternalCacheDir();
                if (dir != null && !dir.exists())
                    dir.mkdirs();
                FileOutputStream fos = new FileOutputStream(new File(dir,
                        fileName));
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                imagePath = dir.getPath() + File.separator + fileName;
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }

        return imagePath;
    }

    public static Bitmap getBitmapThumbForWechat(String filePath) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max(
                    (double) options.outWidth / 120f,
                    (double) options.outHeight / 160f)));
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }

        return bitmap;
    }
}
