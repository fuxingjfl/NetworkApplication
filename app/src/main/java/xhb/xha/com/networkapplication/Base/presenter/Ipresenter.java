package xhb.xha.com.networkapplication.Base.presenter;

import xhb.xha.com.networkapplication.Base.View.IView;
import xhb.xha.com.networkapplication.Base.activity.BaseActivity;

/**
 * Created by ysq on 2019/4/26.
 */

public interface Ipresenter<T extends IView> {

    void attachView(T View);

    void detachView();

    void reload();

    void registerEventBus();

    void unregisterEventBus();


    void setLoginStatus(boolean loginStatus);


    boolean getLoginStatus();

}
