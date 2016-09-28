package com.tools.knife;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WTree on 2016/9/28.
 */

public class Utils {



    @SuppressLint("NewApi")
    public static void copy(Context context,String text){

        if(Build.VERSION.SDK_INT>=11){
            ClipboardManager cm=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText(null, text));
        }else{
            ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setText(text);
        }

    }

    public static int sp2px(Context context, float sp) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    public static int px2dip(Context context, float px){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px / scale + 0.5f);
    }

    public static int dip2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }



    /**
     * 文件夹删除
     * @param path
     */
    public static void delete(String path){
        File file=new File(path);
        if(file.isDirectory()){
            File[] fs=file.listFiles();
            for(File f:fs){
                if(f.isDirectory()){
                    delete(f.getAbsolutePath());
                }else{
                    f.delete();
                }
            }
        }else{
            file.delete();
        }
    }


    /**
     * 计算可用的存储空间
     * @return
     */
    public static long calcAvailableMemery(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize =0;
            long availableBlocks =0;
            if(Build.VERSION.SDK_INT>=18){
                blockSize=stat.getBlockSizeLong();
                availableBlocks=stat.getAvailableBlocksLong();
            }else{
                blockSize = stat.getBlockSize();
                availableBlocks = stat.getAvailableBlocks();
            }
            return availableBlocks * blockSize;
        }
        return 0;
    }


    /**
     * 设置状态栏颜色
     * api>=L
     * @param context
     * @param color
     */
    public static void setStatusBarColor(Activity context, int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            context.getWindow().setStatusBarColor(color);
        }
    }


    /**  获取状态栏高度  */
    public static int getStatusHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return 0;
    }


    /**  打开软键盘　  */
    public static void showKeyboar(final Context context,final View view) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager inputManager =

                        (InputMethodManager) context.getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(view, 0);
            }
        }, 300);
    }

    /**
     *  关闭键盘
     */
    public static void closeKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null && view.getWindowToken() != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
    public static String byteToString(byte[] data) {
        String result = "";
        try {
            result = new String(data, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return result;
    }

    /**
     *手机号码是否合法
     * @param phoneNumber
     * @return
     */
    public  static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pat = Pattern.compile("1[3|4|5|7|8|9][0-9]{9}");
        Matcher matcher = pat.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * 是否为数字
     * @param string
     * @return
     */
    public static boolean isNumber(String string) {
        String reg="(^[-]?\\d+(\\.\\d*)?|\\.\\d+)([eE][-+]?\\d+)?[dD]?$";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher=pattern.matcher(string);
        return matcher.matches();
    }


    /**
     * 安装apk
     *
     * @param path
     */
    public static void installDownload(Context context, String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "application/vnd.android.package-archive"); // mimeType
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String packnameString = "com.android.packageinstaller";// info.activityInfo.applicationInfo.packageName;//使用安卓应用程序包安装
        String className = "com.android.packageinstaller.PackageInstallerActivity";// info.activityInfo.name;
        Intent targetIntent = new Intent();
        targetIntent.setClassName(packnameString, className);
        targetIntent.setDataAndType(uri,
                "application/vnd.android.package-archive");
        try {
            ((Activity) context).startActivityForResult(targetIntent, 100);
        } catch (Exception e) {
            try {
                ((Activity) context).startActivityForResult(intent, 100);
            } catch (Exception e2) {
                context.startActivity(intent);
            }
        }
    }

}
