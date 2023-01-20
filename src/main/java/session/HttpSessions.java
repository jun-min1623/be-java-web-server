package session;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;

public class HttpSessions {
    private String id;
    public static Map<String, HttpSession> httpSessions = Maps.newHashMap();

    public static void addHttpSession(String id) {
        httpSessions.put(id, new HttpSession(getRandStringForSessionId()));
    }

    // todo : 적절한 이름 찾자..
    public static String findSessionById(String userId) {
        return httpSessions.get(userId).getSessionIdById();
    }

    public static String getRandStringForSessionId() {
        int leftLimit = 48; // digit '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 12;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            if (randomLimitedInt > 57 && randomLimitedInt < 97) {
                randomLimitedInt = 97; // if it's not a digit or lowercase letter, set to 'a'
            }
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
