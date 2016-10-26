package com.celerysoft.ittakestwo.application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Celery on 16/1/8.
 * Write crash info to sdcard
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final String TAG = CrashHandler.class.getSimpleName();

    private Thread.UncaughtExceptionHandler mHandler;

    private Context mContext;

    private Map<String, String> mDeviceInformation = new LinkedHashMap<>();

    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private static CrashHandler sInstance = new CrashHandler();

    public static CrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mContext = context;
        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable ex) {
//        if (!handleException(ex) && mHandler != null) {
//            mHandler.uncaughtException(thread, ex);
//        } else {
//            System.exit(1);
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }
        MobclickAgent.reportError(mContext, ex);
        handleException(ex);
        if (mHandler != null) {
            mHandler.uncaughtException(thread, ex);
        }
    }

    public boolean handleException(Throwable ex) {
        if (ex == null)
            return false;

        if(!isExternalStorageWritable()) {
            return false;
        }

        collectDeviceInfo(mContext);

        saveCrashInfo2File(ex);

        return false;
    }

    private void collectDeviceInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                mDeviceInformation.put("versionName", versionName);
                mDeviceInformation.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceInformation.put(field.getName(), field.get("").toString());
                Log.d(TAG, field.getName() + ":" + field.get(""));
            } catch (IllegalAccessException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private String saveCrashInfo2File(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("==========Device Information==========" + "\r\n");
        for (Map.Entry<String, String> entry : mDeviceInformation.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\r\n");
        }
        sb.append("\r\n" + "==========Crash  Information==========" + "\r\n");
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        Throwable cause = ex.getCause();

        while (cause != null) {
            cause.printStackTrace(pw);
            cause = cause.getCause();
        }
        pw.close();
        String result = writer.toString();
        sb.append(result);

        long timestamp = System.currentTimeMillis();
        String time = mSimpleDateFormat.format(new Date());
        String fileName = time + "-" + timestamp + ".log";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File dir = new File(mContext.getExternalFilesDir(null) + File.separator + "crash");
                Log.i("CrashHandler", dir.toString());
                if (!dir.exists())
                    dir.mkdirs();
                FileOutputStream fos = new FileOutputStream(new File(dir,
                        fileName));
                fos.write(sb.toString().getBytes());
                fos.close();
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
