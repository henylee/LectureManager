package kr.co.tjeit.lecturemanager.util;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by the on 2017-08-31.
 */

public class ContextUtil {

    private static User loginUser = null;

    private static final String prefName = "LectureManagerPref";

    private static final String USER_ID = "USER_ID";
    private static final String USER_PW = "USER_PW";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PROFILE_URL = "USER_PROFILE_URL";

    public static void setUserId(Context context, String id) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_ID, id).commit();
    }

    public static String getUserId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_ID, "");
    }

    public static void setUserPw(Context context, String pw) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_PW, pw).commit();
    }

    public static String getUserPw(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_PW, "");
    }


    public static void setUserName(Context context, String input) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_NAME, input).commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_NAME, "");
    }


    public static void setUserProfileUrl(Context context, String input) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_PROFILE_URL, input).commit();
    }

    public static String getUserProfileUrl(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_PROFILE_URL, "");
    }

    public static void login(Context context, String id, String pw, String name, String url) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_ID, id).commit();
        pref.edit().putString(USER_PW, pw).commit();
        pref.edit().putString(USER_NAME, name).commit();
        pref.edit().putString(USER_PROFILE_URL, url).commit();
    }

    public static void logout(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_ID, "").commit();
        pref.edit().putString(USER_PW, "").commit();
        pref.edit().putString(USER_NAME, "").commit();
        pref.edit().putString(USER_PROFILE_URL, "").commit();

    }

    public static User getLoginUser(Context context) {

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (pref.getString(USER_ID, "").equals("")) {
//            사용자 아이디를 가져와보니 빈칸인가? => O
//            로그인이 되어있지 않은 상태.
            loginUser = null;
        }

        else {
//            빈칸이 아니다? 아이디가 O, 누군가 로그인 해있다.
            loginUser = new User();
            loginUser.setUserId(pref.getString(USER_ID, ""));
            loginUser.setPassword(pref.getString(USER_PW, ""));
            loginUser.setName(pref.getString(USER_NAME, ""));
            loginUser.setProfileURL(pref.getString(USER_PROFILE_URL, ""));

        }

        return loginUser;
    }

}
