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

package xhb.xha.com.networkapplication.code;


import xhb.xha.com.networkapplication.code.http.BaseResponse;
import xhb.xha.com.networkapplication.code.http.HttpHelper;
import xhb.xha.com.networkapplication.code.preference.PreferenceHelper;
import xhb.xha.com.networkapplication.modules.test.bean.Testbean;


public class DataManager implements HttpHelper,PreferenceHelper {
    private HttpHelper mHttpHelper;
    private PreferenceHelper mPreferenceHelper;

    public DataManager(HttpHelper httpHelper,  PreferenceHelper preferenceHelper) {
        mHttpHelper = httpHelper;
        mPreferenceHelper = preferenceHelper;
    }

    @Override
    public rx.Observable<BaseResponse<Testbean>> getTestDemo() {
        return mHttpHelper.getTestDemo();
    }



    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferenceHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferenceHelper.getLoginStatus();
    }



    @Override
    public void setNightMode(boolean isNightMode) {
        mPreferenceHelper.setNightMode(isNightMode);
    }

    @Override
    public boolean isNightMode() {
        return mPreferenceHelper.isNightMode();
    }


}
