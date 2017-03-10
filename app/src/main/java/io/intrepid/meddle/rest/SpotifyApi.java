package io.intrepid.meddle.rest;

import io.intrepid.meddle.models.SpotifyUserProfile;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SpotifyApi {
    @GET("/v1/me")
    Call<SpotifyUserProfile> getCurrentUserProfile();
}