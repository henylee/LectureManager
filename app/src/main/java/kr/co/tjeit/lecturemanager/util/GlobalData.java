package kr.co.tjeit.lecturemanager.util;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.lecturemanager.R;
import kr.co.tjeit.lecturemanager.data.Reply;
import kr.co.tjeit.lecturemanager.data.User;

/**
 * Created by the on 2017-08-31.
 */

public class GlobalData {

    public static List<User> allUsers = new ArrayList<>();

    public static void intitGlobalData() {
        allUsers.clear();
        allUsers.add(new User("AAA", "고동윤", "tempURL"));
        allUsers.add(new User("BBB", "권성민", "tempURL"));
        allUsers.add(new User("CCC", "김현철", "tempURL"));
        allUsers.add(new User("DDD", "박석영", "tempURL"));
        allUsers.add(new User("EEE", "박수현", "tempURL"));
        allUsers.add(new User("FFF", "박영주", "tempURL"));
        allUsers.add(new User("GGG", "손익상", "tempURL"));
        allUsers.add(new User("HHH", "이승헌", "tempURL"));
        allUsers.add(new User("III", "이요한", "tempURL"));
        allUsers.add(new User("JJJ", "최종환", "tempURL"));
        allUsers.add(new User("KKK", "한상열", "tempURL"));
    }
}
