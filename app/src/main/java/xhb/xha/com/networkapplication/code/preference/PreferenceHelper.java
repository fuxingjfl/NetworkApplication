package xhb.xha.com.networkapplication.code.preference;

/**
 * Created by ysq on 2019/4/26.
 */

public interface PreferenceHelper {

    void setLoginStatus(boolean isLogin);
    boolean getLoginStatus();

    void setNightMode(boolean isNightMode);
    boolean isNightMode();


}
