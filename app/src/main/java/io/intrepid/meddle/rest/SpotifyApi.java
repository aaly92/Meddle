package io.intrepid.meddle.rest;

import io.intrepid.meddle.models.SpotifyUserProfile;
import retrofit2.http.GET;
import rx.Observable;

public interface SpotifyApi {
    @GET("/v1/me")
    Observable<SpotifyUserProfile> getCurrentUserProfile();
}