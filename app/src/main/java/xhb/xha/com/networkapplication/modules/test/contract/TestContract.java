package xhb.xha.com.networkapplication.modules.test.contract;

import java.util.List;

import xhb.xha.com.networkapplication.Base.View.IView;
import xhb.xha.com.networkapplication.Base.presenter.Ipresenter;
import xhb.xha.com.networkapplication.modules.test.bean.News;

/**
 * Created by ysq on 2019/4/26.
 */

public interface TestContract {

    interface View extends IView{

        void testSuccess(List<News> newsList);

    }


    interface Presenter extends Ipresenter<View>{

        void getTestData(String channelCode);

    }

}
