package xhb.xha.com.networkapplication.Base.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lwkandroid.rtpermission.listener.OnPermissionResultListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.ButterKnife;
import xhb.xha.com.networkapplication.utils.PreUtil;

/**
 * Created by ysq on 2019/5/31.
 */

public abstract class BasicActivity extends AppCompatActivity implements OnPermissionResultListener {
    private static List<Activity> activityList = new ArrayList<>();
    private static final int MY_PERMISSION_REQUEST_CODE = 100;
    private TaskSubmitter mTaskSubmitter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setStatusBar();
        mTaskSubmitter = new TaskSubmitter();
        //在这里判断是否token是否存在、是否过期之类的
        if (activityList != null)
            activityList.add(this);

        //权限申请
//        new RTPermission.Builder().permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
//                , Manifest.permission.READ_EXTERNAL_STORAGE
//                , Manifest.permission.RECORD_AUDIO
//                , Manifest.permission.CAMERA
//                , Manifest.permission.ACCESS_FINE_LOCATION).start(this, this);

        ButterKnife.bind(this);
        PreUtil.getInstance().init(this);
        initCreateView();
        initData();

    }

    @Override
    public void onAllGranted(String[] allPermissions) {
        Toast.makeText(BasicActivity.this, "所有权限都已通过", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeined(String[] dinedPermissions) {
        Toast.makeText(BasicActivity.this, "无法获取所有权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityList != null)
            activityList.remove(this);
    }

    public static List<Activity> getAllActivitys() {
        return activityList;
    }

    public static void removeAllActivitys() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {
                if (activityList.get(i) != null) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
            //            activityList = null;
        }
    }

    public static void realBack() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {
                if (activityList.get(i) != null) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
            activityList = null;
        }
    }

    public void setShowPop(PopupWindow popupWindow, View view) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            setWindowTranslucence(0.3);
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }

    //设置Window窗口的透明度
    public void setWindowTranslucence(double d) {

        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = (float) d;
        window.setAttributes(attributes);

    }

    public abstract void initCreateView();

    public void initData() {

    }

    public void setStatusBar() {

    }

    /**
     * Class for submitting a new runnable task.
     */
    private class TaskSubmitter {

        private ExecutorService executorService;

        public TaskSubmitter() {
            executorService = Executors.newSingleThreadExecutor();
        }

        public Future<?> submit(Runnable task) {
            Future<?> result = null;
            if (!executorService.isTerminated()
                    && !executorService.isShutdown() && task != null) {
                result = executorService.submit(task);
            }
            return result;
        }

        @SuppressWarnings("unused")
        public void shutDown() {
            executorService.shutdown();
        }
    }

    public void _postData(Map<String, String> params, String encode, String url, PostCallback postCallback) {

        mTaskSubmitter.submit(new Runnable() {
            @Override
            public void run() {
                getJsonData
                        (params, encode, url, postCallback);
            }
        });

    }

    public void _postImageData(Map<String, String> params, String encode, String url, PostCallback postCallback) {

        mTaskSubmitter.submit(new Runnable() {
            @Override
            public void run() {
                getImageData
                        (params, encode, url, postCallback);
            }
        });

    }

    public interface PostCallback {
        void success(String data);

        void fail(String data);
    }

    public static String readString(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        boolean len = true;

        int len1;
        while ((len1 = input.read(buffer)) != -1) {
            baos.write(buffer, 0, len1);
        }

        String retStr = new String(baos.toByteArray());
        input.close();
        return retStr;
    }


    /**
     * post方式
     *
     * @return
     * @author www.yoodb.com
     */
    public static void getJsonData(Map<String, String> params, String encode, String url_content, PostCallback postCallback) {


        URL url = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            try {
                url = new URL(url_content);
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    try {
                        stringBuilder
                                .append(entry.getKey())
                                .append("=")
                                .append(URLEncoder.encode(entry.getValue(), encode))
                                .append("&");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);

                HttpURLConnection urlConnection = (HttpURLConnection) url
                        .openConnection();
                urlConnection.setConnectTimeout(3000);
                urlConnection.setRequestMethod("POST"); // 以post请求方式提交
                urlConnection.setDoInput(true); // 读取数据
                urlConnection.setDoOutput(true); // 向服务器写数据
                // 获取上传信息的大小和长度
                byte[] myData = stringBuilder.toString().getBytes();
                // 设置请求体的类型是文本类型,表示当前提交的是文本数据
                urlConnection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("Content-Length",
                        String.valueOf(myData.length));
                // 获得输出流，向服务器输出内容
                OutputStream outputStream = urlConnection.getOutputStream();
                // 写入数据
                outputStream.write(myData, 0, myData.length);
                outputStream.close();
                // 获得服务器响应结果和状态码
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == 200) {
                    // 取回响应的结果

                    postCallback.success(changeInputStream(urlConnection.getInputStream(),
                            encode));
                } else {
                    postCallback.fail("正在请求中请稍后...");
                }
            } catch (IOException e) {
                e.printStackTrace();
                postCallback.fail("正在请求中请稍后...");
            }

        }


    }

    /**
     * get方式
     *
     * @return
     * @author www.yoodb.com
     */
    public static void getImageData(Map<String, String> params, String encode, String url_content, PostCallback postCallback) {


        HttpURLConnection conn = null;
        BufferedReader rd = null;
        StringBuilder sb = new StringBuilder();
        String line = null;
        String response = null;
        try {
            Log.e("TAG","URL==="+url_content);
            conn = (HttpURLConnection) new URL(url_content).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setUseCaches(false);
            conn.connect();
            // 获得服务器响应结果和状态码
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                // 取回响应的结果

                postCallback.success(changeInputStream(conn.getInputStream(),
                        encode));
            } else {
                postCallback.fail("正在请求中请稍后...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            postCallback.fail("正在请求中请稍后...");
        }



    }


    /**
     * 将一个输入流转换成指定编码的字符串
     *
     * @param inputStream
     * @param encode
     * @return
     */
    private static String changeInputStream(InputStream inputStream,
                                            String encode) {

        // 内存流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String result = null;
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(data)) != -1) {
                    byteArrayOutputStream.write(data, 0, len);
                }
                result = new String(byteArrayOutputStream.toByteArray(), encode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //获取状态栏的高度
    public int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getNavigationBarHeight() {
        Resources resources =getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }

}
