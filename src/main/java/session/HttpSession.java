package session;

import java.util.Random;

public class HttpSession {
    private String sessionId;

    public HttpSession(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionIdById() {
        return sessionId;
    }

    // todo : 같은 세션아이디 있는지 validate 확인
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
