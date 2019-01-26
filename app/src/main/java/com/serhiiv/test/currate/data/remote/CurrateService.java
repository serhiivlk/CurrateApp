package com.serhiiv.test.currate.data.remote;

import com.serhiiv.test.currate.data.remote.response.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrateService {
    @GET("currency_list?get=currency_list")
    Single<BaseResponse<List<String>>> currencyPairs();

    @GET("?get=rates")
    Single<BaseResponse<Map<String, String>>> currencyRates(@Query("pairs") String pairs);
}
