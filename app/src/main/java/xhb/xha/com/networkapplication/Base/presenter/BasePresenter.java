package xhb.xha.com.networkapplication.Base.presenter;

import javax.inject.Inject;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import xhb.xha.com.networkapplication.Base.View.IView;
import xhb.xha.com.networkapplication.code.DataManager;
import xhb.xha.com.networkapplication.code.api.ApiRetrofit;
import xhb.xha.com.networkapplication.code.api.ApiService;

/**
 * Created by ysq on 2019/4/26.
 */

public class BasePresenter<T extends IView> implements Ipresenter<T> {

    protected T mView;

    protected ApiService mApiService = ApiRetrofit.getInstance().getApiService();

    @Inject
    public DataManager mDataManager;

    private CompositeDisposable compositeDisposable;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(T view) {

        this.mView=view;

        registerEventBus();

    }

    @Override
    public void detachView() {
        this.mView=null;

        unregisterEventBus();
    }

    @Override
    public void reload() {

    }

    @Override
    public void registerEventBus() {

    }

    @Override
    public void unregisterEventBus() {

    }

    @Override
    public void setLoginStatus(boolean loginStatus) {
        mDataManager.setLoginStatus(loginStatus);
    }

    @Override
    public boolean getLoginStatus() {
        return mDataManager.getLoginStatus();
    }


    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
