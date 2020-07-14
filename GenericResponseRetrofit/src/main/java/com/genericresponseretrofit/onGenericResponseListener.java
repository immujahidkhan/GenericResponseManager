package com.genericresponseretrofit;


public interface onGenericResponseListener {

    void onComplete();

    void onNext(String responseSuccessBody);

    void onErrorBody(String responseErrorBody);
}