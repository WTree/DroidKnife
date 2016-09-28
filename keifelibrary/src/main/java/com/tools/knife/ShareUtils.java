package com.tools.knife;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by WTree on 2016/9/28.
 */

public class ShareUtils {

    /**
     * 分享Intent
     */
    public static void shareIntent(Context context, String titleString, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, titleString);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        try {
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.more_share)));
        } catch (Exception e) {
            Toast.makeText(context, context.getString(R.string.more_share_soft), Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 发起市场评价
     */
    public static void intentMarket(Context context,String packageName) {
        Uri uri = Uri.parse("market://"+packageName);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(Intent.createChooser(it, context.getString(R.string.open_choose)));
        } catch (Exception e) {
            Toast.makeText(context, context.getString(R.string.not_found_app), Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 获取版本名(String)
     * @param context
     * @return
     */
    public static String getVersionString(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    /**
     * 获取版本号(int)
     * @param context
     * @return
     */
    public static int getVersionInt(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG","tree this is :"+e.toString());
            return 1;
        }
    }
}
