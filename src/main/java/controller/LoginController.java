package controller;

import db.Database;
import enums.ContentTypeEnum;
import enums.ControllerTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.Request;
import response.ResponseFactory;
import session.HttpSessions;
import utils.UserInfoParseUtils;
import webserver.RequestResponseHandler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginController implements Controller{
    private String sid;
    private static final Logger logger = LoggerFactory.getLogger(RequestResponseHandler.class);
    private static final int USERINFO_INDEX=0;
    private static final int USERID_INDEX = 0;
    private static final int USERPWD_INDEX = 1;

    @Override
    public ResponseFactory controllerService(Request request) throws IOException, SQLException {
        logger.debug("login request : "+request.getRequestLine().getMETHOD());
        logger.debug("login request : "+request.getRequestLine().getURL());
        logger.debug("login request : "+request.getRequestLine().getVERSION());
        logger.debug("login request : "+request.getRequestBody().getBodyLines());
        List<String> bodyLine = request.getRequestBody().getBodyLines();
        List<String> userInfos = UserInfoParseUtils.parseUrlToGetUserInfo(bodyLine);
        byte[] body = Files.readAllBytes(new File("./src/main/resources/templates" + "/index.html").toPath());
        String addedLine="Location : /index.html";
        // 로그인이 성공하면? session add
        if(checkIdPwdIsInDatabase(userInfos.get(USERID_INDEX),userInfos.get(USERPWD_INDEX))){
            HttpSessions.addHttpSession(userInfos.get(USERID_INDEX));
            sid = HttpSessions.findSessionById(userInfos.get(USERID_INDEX));
        }else{
            // 실패하면?
            logger.debug("login failed!");
            addedLine="Location : /user/login_failed.html";
        }

        ResponseFactory responseFactory = new ResponseFactory.Builder()
                .setResponseStatusLine(ControllerTypeEnum.LOGIN)
                .setResponseHeader(ContentTypeEnum.CSS,body.length)
                .addResponseHeader(addedLine)
                .addCookieHeader(sid)
                .setResponseBody(body)
                .build();
        return responseFactory;

    }
    // util 로 빼주자
    public boolean checkIdPwdIsInDatabase(String id, String pwd) throws SQLException {
        if(Database.findUserById(id)==null){
            return false;
        }
        if(Database.findUserById(id).getPassword().equals(pwd)){
            return true;
        }return false;
    }

}
