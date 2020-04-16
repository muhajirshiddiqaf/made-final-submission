package com.ids.madesubmission4.di.component;

import android.app.Application;

import com.ids.madesubmission4.BaseApplication;
import com.ids.madesubmission4.di.modules.ActivityBindingModule;
import com.ids.madesubmission4.di.modules.ContextModule;
import com.ids.madesubmission4.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 14,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */

@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
