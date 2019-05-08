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

package xhb.xha.com.networkapplication.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xhb.xha.com.networkapplication.Mpp;
import xhb.xha.com.networkapplication.code.DataManager;
import xhb.xha.com.networkapplication.code.http.HttpHelper;
import xhb.xha.com.networkapplication.code.preference.PreferenceHelper;
import xhb.xha.com.networkapplication.code.preference.PreferenceHelperImpl;

@Module
public class AppModule {
    private final Mpp application;

    public AppModule(Mpp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Mpp provideApplicationContext() {
        return application;
    }



    @Provides
    @Singleton
    PreferenceHelper providePreferenceHelper(PreferenceHelperImpl preferenceHelper) {
        return preferenceHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferenceHelper preferenceHelper) {
        return new DataManager(httpHelper, preferenceHelper);
    }

}
