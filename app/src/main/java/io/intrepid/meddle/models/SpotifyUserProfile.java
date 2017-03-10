package io.intrepid.meddle.models;


import com.google.gson.annotations.SerializedName;

public class SpotifyUserProfile {
    @SerializedName("display_name")
    public String displayName;
    @SerializedName("product")
    public String accountType;
    public String userId;
    public String response;

    public SpotifyUserProfile(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}