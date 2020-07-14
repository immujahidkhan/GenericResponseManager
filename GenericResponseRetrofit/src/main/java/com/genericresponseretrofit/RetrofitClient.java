package com.genericresponseretrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit ourInstance, gitInstance;

    private static Retrofit getInstance(String baseUrl) {
        if (ourInstance == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                logging.level(HttpLoggingInterceptor.Level.HEADERS);
                logging.level(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.level(HttpLoggingInterceptor.Level.NONE);
            }
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request.Builder newRequest = chain.request().newBuilder();
                            newRequest.header("Content-Type", "application/json");
                            return chain.proceed(newRequest.build());
                        }
                    })
                    .retryOnConnectionFailure(true);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            ourInstance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();
        }
        return ourInstance;
    }

    private static Retrofit getInstanceWithToken(String baseUrl, final String token) {
        if (ourInstance == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                logging.level(HttpLoggingInterceptor.Level.HEADERS);
                logging.level(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.level(HttpLoggingInterceptor.Level.NONE);
            }
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request.Builder newRequest = chain.request().newBuilder();
                            newRequest.header("Content-Type", "application/json");
                            newRequest.header("Authorization", "Bearer " + token);
                            return chain.proceed(newRequest.build());
                        }
                    })
                    .retryOnConnectionFailure(true);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            ourInstance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();
        }
        return ourInstance;
    }

    public static <S> S createService(String baseUrl, Class<S> serviceClass) {
        ourInstance = getInstance(baseUrl);
        return ourInstance.create(serviceClass);
    }

    public static <S> S createService(String baseUrl, String withToken, Class<S> serviceClass) {
        ourInstance = getInstanceWithToken(baseUrl, withToken);
        return ourInstance.create(serviceClass);
    }
}