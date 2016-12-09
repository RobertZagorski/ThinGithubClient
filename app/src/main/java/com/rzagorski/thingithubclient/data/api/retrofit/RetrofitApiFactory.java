package com.rzagorski.thingithubclient.data.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.rzagorski.thingithubclient.BuildConfig;
import com.rzagorski.thingithubclient.data.api.GithubApi;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class RetrofitApiFactory {

    public static GithubApi makeApiManager(String baseUrl) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(baseUrl)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(provideApiClient())
                .addConverterFactory(provideApiConverter())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(GithubApi.class);
    }

    private static OkHttpClient provideApiClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    private static Converter.Factory provideApiConverter() {
        Gson gson = new GsonBuilder()
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .setDateFormat("YYYY-MM-DD'T'HH:MM:SSZ")
                .create();
        return GsonConverterFactory.create(gson);
    }
}
