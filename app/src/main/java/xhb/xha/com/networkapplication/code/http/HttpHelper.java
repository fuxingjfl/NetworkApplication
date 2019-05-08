package xhb.xha.com.networkapplication.code.http;

import xhb.xha.com.networkapplication.modules.test.bean.Testbean;

/**
 * Created by ysq on 2019/4/26.
 */

public interface HttpHelper {


    //获取测试数据
    rx.Observable<BaseResponse<Testbean>> getTestDemo();

}
