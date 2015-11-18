package com.example.usr0200393.retrofit18test;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class ServiceGenerator {

    public static <S> S getService(Class<S> serviseClass) {

        OkHttpClient client = new OkHttpClient();

        //header追加とか
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                Log.d("rtTest", "intercept");
            }
        };

        //共通のエラー処理
        ErrorHandler errorHandler = new ErrorHandler() {
            @Override
            public Throwable handleError(RetrofitError cause) {
                Log.d("rtTest", "handle now!!:" + cause.getMessage());
                if (cause.getResponse() == null) {
                    Log.d("rtTest", "net work error");
                } else {
                    int statusCode = cause.getResponse().getStatus();
                }
                return cause;
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setRequestInterceptor(requestInterceptor)
                .setErrorHandler(errorHandler)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .setEndpoint("http://weather.livedoor.com/")
                .build();

        return restAdapter.create(serviseClass);
    }
}
