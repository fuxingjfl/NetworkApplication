package xhb.xha.com.networkapplication.modules.test.presenter;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import xhb.xha.com.networkapplication.Base.presenter.BasePresenter;
import xhb.xha.com.networkapplication.code.DataManager;

import xhb.xha.com.networkapplication.code.api.ApiService;
import xhb.xha.com.networkapplication.code.http.HttpHelper;
import xhb.xha.com.networkapplication.code.rx.BaseObserver;
import xhb.xha.com.networkapplication.modules.test.bean.News;
import xhb.xha.com.networkapplication.modules.test.bean.NewsData;
import xhb.xha.com.networkapplication.modules.test.bean.NewsResponse;
import xhb.xha.com.networkapplication.modules.test.bean.Testbean;
import xhb.xha.com.networkapplication.modules.test.contract.TestContract;
import xhb.xha.com.networkapplication.utils.RxUtils;

/**
 * Created by ysq on 2019/4/26.
 */

public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    private long lastTime;
    @Inject
    public TestPresenter(){
    }


    @Override
    public void getTestData(String channelCode) {
        if (lastTime == 0){
            //如果为空，则是从来没有刷新过，使用当前时间戳
            lastTime = System.currentTimeMillis() / 1000;
        }

        addSubscription(mApiService.getTestDemo(channelCode,lastTime,System.currentTimeMillis()/1000), new Subscriber<NewsResponse>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {
                mView.showError();
            }

            @Override
            public void onNext(NewsResponse response) {
                List<NewsData> data = response.data;
                List<News> newsList = new ArrayList<>();

                    for (NewsData newsData : data) {
                        News news = new Gson().fromJson(newsData.content, News.class);
                        newsList.add(news);
                    }

                mView.testSuccess(newsList);
            }
        });

    }
}
