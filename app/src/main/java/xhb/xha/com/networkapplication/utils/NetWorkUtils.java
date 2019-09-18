package xhb.xha.com.networkapplication.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetWorkUtils {



    /**
     * 检查用户的网络:是否有网
     */
    public static boolean checkNet(Context context) {
        // 判断：WIFI链接
        boolean isWIFI = isWIFIConnection(context);
        // 判断：Mobile链接
        boolean isMOBILE = isMOBILEConnection(context);

        // 如果Mobile在链接，判断是哪个APN被�?�中�?
        // if (isMOBILE) {
        // // APN被�?�中,的代理信息是否有内容，如果有wap方式
        // readAPN(context);// 判断是哪个APN被�?�中�?
        // }

        if (!isWIFI && !isMOBILE) {
            return false;
        }

        return true;
    }

    /**
     * APN被�?�中,的代理信息是否有内容，如果有wap方式
     *
     * @param context
     */
    private static void readAPN(Context context) {
        Uri PREFERRED_APN_URI = Uri
                .parse("content://telephony/carriers/preferapn");// 4.0模拟器屏蔽掉该权�?

        // 操作联系人类�?
        ContentResolver resolver = context.getContentResolver();
        // 判断是哪个APN被�?�中�?
        Cursor cursor = resolver.query(PREFERRED_APN_URI, null, null, null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            GlobalParams.PROXY = cursor.getString(cursor
                    .getColumnIndex("proxy"));
            GlobalParams.PORT = cursor.getInt(cursor.getColumnIndex("port"));
        }

    }

    /**
     * 判断：Mobile链接
     *
     * @param context
     * @return
     */
    private static boolean isMOBILEConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    /**
     * 判断：WIFI链接
     *
     * @param context
     * @return
     */
    private static boolean isWIFIConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }



    /**
     * 检查网络是否可用
     *
     * @param paramContext
     * @return
     */
    public static boolean checkEnable(Context paramContext) {
        boolean i = false;
        NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
                .getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
        return false;
    }

    public static boolean isNetworkAvailable(Context context) {
        if(context !=null){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if(info !=null){
                return info.isAvailable();
            }
        }
        return false;
    }

    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 获取当前ip地址
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {
            // for (Enumeration<NetworkInterface> en = NetworkInterface
            // .getNetworkInterfaces(); en.hasMoreElements();) {
            // NetworkInterface intf = en.nextElement();
            // for (Enumeration<InetAddress> enumIpAddr = intf
            // .getInetAddresses(); enumIpAddr.hasMoreElements();) {
            // InetAddress inetAddress = enumIpAddr.nextElement();
            // if (!inetAddress.isLoopbackAddress()) {
            // return inetAddress.getHostAddress().toString();
            // }
            // }
            // }
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2ip(i);
        } catch (Exception ex) {
            return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
        }
        // return null;
    }
}
