package minio.material.dash.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String
            PREFERENCES_NAME = "DASHBOARD_PREFERENCES",
            FIRSTRUN = "firstrun";

    private final Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setNotFirstrun() {
        getSharedPreferences().edit().putBoolean(FIRSTRUN, false).apply();
    }
}