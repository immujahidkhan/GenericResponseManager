package com.genericresponseretrofit;


import okhttp3.ResponseBody;
import retrofit2.Response;

public interface onGenericResponseListener {

    void onComplete();

    void onNext(Response<?> response);

    void onErrorBody(ResponseBody responseErrorBody);
}