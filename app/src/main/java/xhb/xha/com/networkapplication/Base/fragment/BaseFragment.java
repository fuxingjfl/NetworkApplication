package xhb.xha.com.networkapplication.Base.fragment;


import xhb.xha.com.networkapplication.Base.View.IView;
import xhb.xha.com.networkapplication.Base.presenter.Ipresenter;

/**
 * Fragment预加载问题的解决方案：
 * 1.可以懒加载的Fragment
 * 2.切换到其他页面时停止加载数据（可选）
 * Created by yuandl on 2016-11-17.
 * blog ：http://blog.csdn.net/linglongxin24/article/details/53205878
 */

public abstract class BaseFragment<T extends Ipresenter> extends BasicFragment implements IView {

    protected T mPresenter;


    @Override
    public void setPresenter() {
        super.setPresenter();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 视图销毁的时候讲Fragment是否初始化的状态变为false
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideLoading();
    }

    @Override
    public void hideLoading() {

    }
    public abstract T createPresenter();
}

