package xhb.xha.com.networkapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class PerferenceManager {
    private static final String PERF_NAME = "com.suznxyong.util.my_perf";
    private static final int CURRENT_VERSION_CODE = 1;
    private volatile static PerferenceManager instance;
    private final SharedPreferences preferences;

    private PerferenceManager(Context context, String filename) {
        preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        checkPrefVersion();
    }

    public static PerferenceManager getInstance(Context context, String filename) {
        if (instance == null) {
            synchronized (PerferenceManager.class) {
                if (instance == null)
                    instance = new PerferenceManager(context,filename);
            }
        }
        return instance;
    }

    public final void putValue(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public final String getValue(String key) {
        checkIsLegal(key);
        return preferences.getString(key, "");
    }

    public final void deleteValue(String key) {
        checkIsLegal(key);
        preferences.edit().remove(key).apply();
    }

    public final void clear() {
        preferences.edit().clear().apply();
    }

    private void checkIsLegal(String key) {
        if (TextUtils.isEmpty(key))
            throw new IllegalArgumentException("This parameter is illegal,key : " + key);
    }

    private void checkPrefVersion() {
        final int oldVersion = preferences.getInt(PERF_NAME, 0);
        if (oldVersion < CURRENT_VERSION_CODE) {
            preferences.edit()
                    .clear()
                    .putInt(PERF_NAME, CURRENT_VERSION_CODE).apply();
        }
    }
}
