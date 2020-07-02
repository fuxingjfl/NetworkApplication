/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package xhb.xha.com.networkapplication.code.api;


import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import xhb.xha.com.networkapplication.code.http.BaseResponse;
import xhb.xha.com.networkapplication.modules.test.bean.NewsResponse;
import xhb.xha.com.networkapplication.modules.test.bean.Testbean;

public interface ApiService {

    String GET_ARTICLE_LIST = "api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752";

//    @Headers({
//            "Content-Type:application/x-www-form-urlencoded; charset=UTF-8",
//            "Cookie:PHPSESSIID=334267171504; _ga=GA1.2.646236375.1499951727; _gid=GA1.2.951962968.1507171739; Hm_lvt_e0a6a4397bcb500e807c5228d70253c8=1507174305;Hm_lpvt_e0a6a4397bcb500e807c5228d70253c8=1507174305; _gat=1",
//            "Origin:http://toutiao.iiilab.com"
//    })

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头

    /**
     * 获取文章列表
     * https://www.wanandroid.com/article/list/0/json
     *
     * @param
     */
    @GET(GET_ARTICLE_LIST)
    Observable<NewsResponse> getTestDemo(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);
//    Observable<BaseResponse<NewsResponse>> getTestDemo(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);

}
