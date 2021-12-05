package ne.iot.gulshatdiploma;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {
    private static final String MY_PREFERENCE_NAME = "com.iot.smart_home";
    private static final String PREF_LOGIN_KEY = "pref_secret_key";
    private static final String PREF_LAUNCH_KEY = "pref_launch_key";
    private static final String PREF_SECRET_KEY = "pref_secret_key";
    private static final String PREF_TIME_KEY = "pref_time_key";
    private static final String PREF_TIME2_KEY = "pref_time2_key";
    private static final String PREF_TIME3_KEY = "pref_time3_key";
    private static final String PREF_SW_KEY = "pref_sw_key";
    private static final String PREF_SW2_KEY = "pref_sw2_key";
    private static final String PREF_SW3_KEY = "pref_sw3_key";

    // for LAUNCH PROGRAM FIRST
    public static void saveLaunch(Context context, int launch) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_LAUNCH_KEY, launch);
        editor.apply();
    }
    public static int loadLaunch(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_LAUNCH_KEY, 0);
    }

    // for Login
    public static void saveKey(Context context, int key) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_LOGIN_KEY, key);
        editor.apply();
    }
    public static int loadKey(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_LOGIN_KEY, 1111);
    }

    // for QR CODE
    public static void saveQrCode(Context context, String qr) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_SECRET_KEY, qr);
        editor.apply();
    }
    public static String loadQrCode(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_SECRET_KEY, "");
    }
    // for SETTING TIME
    public static void saveTime(Context context, String time) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_TIME_KEY, time);
        editor.apply();
    }
    public static String loadTime(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_TIME_KEY, "");
    }

    // for SETTING TIME2
    public static void saveTime2(Context context, String time2) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_TIME2_KEY, time2);
        editor.apply();
    }
    public static String loadTime2(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_TIME2_KEY, "");
    }

    // for SETTING TIME3
    public static void saveTime3(Context context, String time3) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_TIME3_KEY, time3);
        editor.apply();
    }
    public static String loadTime3(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_TIME3_KEY, "");
    }


    // for SWITCH
    public static void saveSW(Context context, int sw) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_SW_KEY, sw);
        editor.apply();
    }
    public static int loadSW(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_SW_KEY, 0);
    }
    // for SWITCH2
    public static void saveSW2(Context context, int sw2) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_SW2_KEY, sw2);
        editor.apply();
    }
    public static int loadSW2(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_SW2_KEY, 0);
    }
    // for SWITCH3
    public static void saveSW3(Context context, int sw3) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_SW3_KEY, sw3);
        editor.apply();
    }
    public static int loadSW3(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_SW3_KEY, 0);
    }

}