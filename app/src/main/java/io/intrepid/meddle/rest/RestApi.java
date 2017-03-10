package io.intrepid.meddle.rest;

import io.intrepid.meddle.models.IpModel;
import io.intrepid.meddle.models.UserModel;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import rx.Observable;

public interface RestApi {
    @GET("/?format=json")
    Observable<IpModel> getMyIp();

    @PUT("/prod/user")
    Observable<UserModel> saveUser(@Body UserModel userModel);
}
