package com.genericresponseretrofit;


import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

public class GenericResponseManager {

    APIRequests apiRequests;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public GenericResponseManager(String baseUrl) {
        if (apiRequests == null) {
            apiRequests = RetrofitClient.createService(baseUrl, APIRequests.class);
        }
    }

    public GenericResponseManager(String baseUrl, String withToken) {
        if (apiRequests == null) {
            apiRequests = RetrofitClient.createService(baseUrl, withToken, APIRequests.class);
        }
    }

    private static final String TAG = GenericResponseManager.class.getSimpleName();

    public void postRequest(Map<String, String> map, String endPoint, final onGenericResponseListener onGenericResponseListener) {

        apiRequests.dynamicPOSTResponse(endPoint, map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                onGenericResponseListener.onNext(response.body().toString());
                            }
                        } else {
                            if (response.errorBody() != null) {
                                onGenericResponseListener.onErrorBody(response.errorBody().toString());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.tag(TAG).d("Error %s", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        onGenericResponseListener.onComplete();
                    }
                });
    }

    public void getRequest(Map<String, String> map, String endPoint, final onGenericResponseListener onGenericResponseListener) {

        apiRequests.dynamicGETResponse(endPoint, map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Object> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                onGenericResponseListener.onNext(response.body().toString());
                            }
                        } else {
                            if (response.errorBody() != null) {
                                onGenericResponseListener.onErrorBody(response.errorBody().toString());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.tag(TAG).d("Error %s", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        onGenericResponseListener.onComplete();
                    }
                });
    }
}