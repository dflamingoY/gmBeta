package org.xiaoxingqi.gmdoc.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import org.xiaoxingqi.gmdoc.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.regex.Pattern;

public class AppTools {
    public final static String emailRegex = "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
    public final static String telRegex = "[1][34578]\\d{9}";
    private static ImageLoaderConfiguration config = null;

    public static DisplayImageOptions options = null;

    public static void initDisplayImageOptions() {
        if (null != options) {
            return;
        }
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.img_empty_square)//加载过程中显示的图片
                .showImageForEmptyUri(R.mipmap.img_empty_square)//加载内容为空显示的图片
                .showImageOnFail(R.mipmap.img_empty_square)//加载失败显示的图片
                .cacheInMemory(true)//启用内存缓存
                .cacheOnDisk(true)//启用硬盘缓存
                .considerExifParams(true)//是否考虑JPEG图像EXIF参数（旋转，翻转）
                //                .delayBeforeLoading(1000)//设置延迟部分时间再开始加载
                .bitmapConfig(Bitmap.Config.RGB_565)    //设置图片的解码类型
                //        ALPHA_8 代表8位Alpha位图
                //        ARGB_4444 代表16位ARGB位图，由4个4位组成
                //        ARGB_8888 代表32位ARGB位图，由4个8位组成
                //        RGB_565 代表16位RGB位图，R为5位，G为6位，B为5位
                .displayer(new FadeInBitmapDisplayer(1500))//是否图片加载好后渐入的动画时间
                //                .displayer(new RoundedBitmapDisplayer(20))//圆角
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(388))
                .build();
    }

    public static void initImageLoader(Context context) {
        if (null != config) {
            return;
        }
        config = new ImageLoaderConfiguration.Builder(context)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSizePercentage(13)
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileCount(200)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .threadPriority(Thread.MAX_PRIORITY)
                .imageDownloader(new BaseImageDownloader(context))
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }


    /**
     * @param context
     * @param value
     * @return
     */
    public static int dp2px(Context context, int value) {
        return (int) (context.getResources().getDisplayMetrics().density * value + 0.5f);
    }

    /**
     * @param context
     * @param value
     * @return
     */
    public static int px2dp(Context context, int value) {
        return (int) (value * 1f / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int getWindowsWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getWindowsHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getPhoneDensity(Context context) {
        return (int) (context.getResources().getDisplayMetrics().density + 0.5f);
    }


    /**
     * 获取顶部状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    public static String getVersion(Activity activity) {
        PackageManager manager = activity.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            String versionName = info.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断当前网络是否可用
     */
    public static boolean isNetOk(Activity act) {
        try {
            ConnectivityManager manager = (ConnectivityManager) act
                    .getApplicationContext().getSystemService(
                            Context.CONNECTIVITY_SERVICE);
            if (manager == null) {
                return false;
            }
            NetworkInfo networkinfo = manager.getActiveNetworkInfo();

            if (networkinfo == null || !networkinfo.isAvailable()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 匹配是否是手机号码
     *
     * @param number
     * @return
     */
    public static boolean isMobilePhone(String number) {
        return number.matches(telRegex);
    }

    /**
     * 匹配是否是邮箱地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(emailRegex, email);
    }

    public static String getPath(Uri data, Activity activity) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(data, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            cursor = null;
            if (picturePath == null || picturePath.equals("null")) {
                Toast toast = Toast.makeText(activity, "图片路径不合法", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return "";
            }
            return picturePath;
        }
        return null;
    }

    /**
     * 判断是否内存卡可用
     *
     * @return
     */
    public static boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static void openGallery(Activity context, int request) {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        context.startActivityForResult(intent, request);
    }
}
