package com.frame.mvp.di.login;

import com.frame.mvp.mvp.login.LoginContract;
import com.frame.mvp.mvp.login.LoginModel;
import com.tool.common.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * LoginModule
 */
@Module
public class LoginModule {

    private LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginContract.View provideView() {
        return view;
    }

    @ActivityScope
    @Provides
    LoginContract.Model provideModel(LoginModel model) {
        return model;
    }
}
