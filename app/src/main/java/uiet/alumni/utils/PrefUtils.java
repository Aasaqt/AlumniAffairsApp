package uiet.alumni.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by aasaqt on 31/10/15.
 */
public final class PrefUtils {
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static String prefName = "user_pref";
    private static String IS_USER_LOGGED_IN = "is_user_logged_in";
    public static void init(Context ctx){
        pref = ctx.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
    }
    public static void setUserLogin(boolean pUserLoggedIn){
        editor.putBoolean(IS_USER_LOGGED_IN,pUserLoggedIn);
        editor.apply();
    }
    public static boolean getUserLogin() {
        return pref.getBoolean(IS_USER_LOGGED_IN, false);
    }
}
