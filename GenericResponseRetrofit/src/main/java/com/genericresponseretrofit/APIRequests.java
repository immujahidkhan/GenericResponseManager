package com.genericresponseretrofit;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface APIRequests {

    /**
     * --------------------------------------------------DYNAMIC METHOD FOR GETTING RESPONSE---------------------------------------------------------
     **/
    @FormUrlEncoded
    @POST
    Observable<Response<Object>> dynamicPOSTResponse(@Url String endPoint, @FieldMap Map<String, String> map);

    @GET
    Observable<Response<Object>> dynamicGETResponse(@Url String endPoint, @QueryMap Map<String, String> map);

    /**
     * --------------------------------------------------DYNAMIC METHOD FOR GETTING RESPONSE---------------------------------------------------------
     **/
}