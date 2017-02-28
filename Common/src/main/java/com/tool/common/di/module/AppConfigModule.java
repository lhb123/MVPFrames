package com.tool.common.di.module;

import android.text.TextUtils;

import com.tool.common.http.NetworkHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * App全局配置信息
 */
@Module
public class AppConfigModule {

    private HttpUrl httpUrl;
    private File httpCacheFile;
    private NetworkHandler networkHandler;
    private List<Interceptor> interceptors;

    public AppConfigModule(Buidler buidler) {
        this.httpUrl = buidler.httpUrl;
        this.httpCacheFile = buidler.httpCacheFile;
        this.networkHandler = buidler.networkHandler;
        this.interceptors = buidler.interceptors;
    }

    public static Buidler buidler() {
        return new Buidler();
    }

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return httpUrl;
    }

    @Singleton
    @Provides
    File providehttpCacheFile() {
        return httpCacheFile;
    }

    @Singleton
    @Provides
    NetworkHandler provideNetworkHandler() {
        return networkHandler;
    }

    @Singleton
    @Provides
    List<Interceptor> provideInterceptors() {
        return interceptors;
    }

    /**
     * App全局配置
     */
    public static class Buidler {

        // Http通信Base接口
        private HttpUrl httpUrl;
        // Http缓存路径
        private File httpCacheFile;
        // 网络通信拦截器
        private NetworkHandler networkHandler;
        // 其他拦截器
        private List<Interceptor> interceptors = new ArrayList<>();

        private Buidler() {
            ;
        }

        public Buidler httpUrl(String httpUrl) {
            if (TextUtils.isEmpty(httpUrl)) {
                throw new IllegalArgumentException("httpUrl can not be empty!");
            }
            this.httpUrl = HttpUrl.parse(httpUrl);
            return this;
        }

        public Buidler httpCacheFile(File httpCacheFile) {
            this.httpCacheFile = httpCacheFile;
            return this;
        }

        public Buidler networkHandler(NetworkHandler networkHandler) {
            this.networkHandler = networkHandler;
            return this;
        }

        public Buidler interceptors(Interceptor[] interceptors) {
            this.interceptors = Arrays.asList(interceptors);
            return this;
        }

        public AppConfigModule build() {
            return new AppConfigModule(this);
        }
    }
}