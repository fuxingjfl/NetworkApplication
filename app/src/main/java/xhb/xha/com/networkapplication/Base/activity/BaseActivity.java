package xhb.xha.com.networkapplication.Base.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;


import java.util.Calendar;
import java.util.Date;

import xhb.xha.com.networkapplication.Base.View.IView;
import xhb.xha.com.networkapplication.Base.presenter.Ipresenter;


/**
 * Created by lxk on 2017/6/10.
 */

public abstract class BaseActivity<T extends Ipresenter> extends BasicActivity implements IView {

    private static String TAG="TAG";
    protected T mPresenter;



    //获取状态栏的高度
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    //    检查是否拥有指定的所有权限
    private boolean checkSelfPermissionAll(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //        overridePendingTransition(R.anim.start_activity_in, R.anim.start_activity_out);
    }

    @Override
    public void finish() {
        super.finish();
        //        overridePendingTransition(R.anim.finish_activity_in, R.anim.finish_activity_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /*
    * 必须调用 MobclickAgent.onResume() 和 MobclickAgent.onPause()方法，
    * 才能够保证获取正确的新增用户、活跃用户、启动次数、使用时长等基本数据。
    * 这两个方法是用来统计应用时长的(也就是Session时长,当然还包括一些其他功能)
    * MobclickAgent.onPageStart() 和 MobclickAgent.onPageEnd() 方法是用来统计页面跳转的
    * 在仅有Activity的应用中，SDK 自动帮助开发者调用了上面的方法，并把Activity 类名作为页面名称统计。
    * 但是在包含fragment的程序中我们希望统计更详细的页面，所以需要自己调用方法做更详细的统计。
    * */
    @Override
    protected void onResume() {
        super.onResume();
        // MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //  MobclickAgent.onPause(this);
    }




    /**
     * 根据提供的年月获取该月份的最后一天
     *
     * @param year
     * @param monthOfYear
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:29:38
     */
    public Date getSupportEndDayofMonth(int year, int monthOfYear) {
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cal.getTime();
        return lastDate;
    }


    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void handleLoginSuccess() {

    }

    @Override
    public void handleLogoutSuccess() {

    }

    public abstract T createPresenter();

    @Override
    public void initData() {
        super.initData();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }

}
