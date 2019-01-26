package com.serhiiv.test.currate.data.remote;

import com.serhiiv.test.currate.data.remote.response.BaseResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CurrateService {
    @GET("currency_list?get=currency_list")
    Single<BaseResponse<List<String>>> currencyPairs();
}
