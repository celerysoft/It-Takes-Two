package com.celerysoft.ittakestwo.utils;

import android.content.Context;
import android.view.View;

import com.celerysoft.ittakestwo.R;
import com.celerysoft.materialdesigndialog.MaterialDesignDialog;

/**
 * Created by Celery on 16/1/29.
 * Dialog util
 */
public class DialogUtil {

    public static void showInstallWechatDialog(Context context) {
        final MaterialDesignDialog dialog = new MaterialDesignDialog(context);
        dialog.setTitle(context.getString(R.string.dialog_util_not_install_wechat_title))
                .setMessage(context.getString(R.string.dialog_util_not_install_wechat_message))
                .setPositiveButton(context.getString(R.string.ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    public static void showConnectToInternetDialog(Context context) {
        final MaterialDesignDialog dialog = new MaterialDesignDialog(context);
        dialog.setTitle(context.getString(R.string.dialog_util_not_connect_to_internet_title))
                .setMessage(context.getString(R.string.dialog_util_not_connect_to_internet_message))
                .setPositiveButton(context.getString(R.string.ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }
}
