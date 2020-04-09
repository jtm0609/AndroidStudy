package com.jtmcompany.android_study_test.Part25_Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("/v2/everything")
    Call<PageListModel> getList (@Query("q")String q,
                                 @Query("from")String from,
                                 @Query("sortBy")String sortBy,
                                 @Query("apiKey")String apiKey
                                 );
}
