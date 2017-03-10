package io.intrepid.meddle.settings;

import android.support.annotation.Nullable;

public interface UserSettings {

    void setLastIp(String ip);

    @Nullable
    String getLastIp();
}
