package utils;

import db.BoardDatabase;
import model.Article;
import model.User;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Collection;

public class StringBuilderUtils {
    private static StringBuilder stringBuilder;
    // 현재 하드코딩으로 javajigi slipp 들어가있어서 임시로 상수 설정. 해당부분 지워도 되면 수정하겠음.
    private static int LIST_START_INDEX = 3;

    public static String byteArrayToString(byte[] body) {
        stringBuilder  = new StringBuilder();
        stringBuilder.append(new String(body));
        return stringBuilder.toString();
    }
    public static byte[] stringToByteArray(String target){
        return target.getBytes();
    }
    public static int getStringIndex(String target){
        return stringBuilder.indexOf(target);
    }
    public static String userListStringBuilder(Collection<User> allUser){
        int index=LIST_START_INDEX;
        stringBuilder = new StringBuilder();
        for (User user:allUser) {
            stringBuilder.append("                <tr>\n");
            stringBuilder.append("                    <th scope=\"row\">");
            stringBuilder.append(index);
            index++;
            stringBuilder.append("</th> <td>"+user.getUserId());
            stringBuilder.append("</th> <td>"+user.getName());
            stringBuilder.append("</th> <td>"+user.getEmail());
            stringBuilder.append("</td><td><a href=\"#\" class=\"btn btn-success\" role=\"button\">수정</a></td>\n");
            stringBuilder.append("                </tr>\n");
        }
        return stringBuilder.toString();
    }
    public static String userNameButtonBuilder(User user){
        stringBuilder = new StringBuilder();
        stringBuilder.append("role=\"button\" disabled>");
        stringBuilder.append(user.getName());
        return stringBuilder.toString();
    }
    public static String viewAllBoardBuilder() throws SQLException {
        stringBuilder = new StringBuilder();
        Collection<Article> board = BoardDatabase.getAllArticles();
        int articleNum = 0;
        for(Article article:board){
            stringBuilder.append("                <li>\n" +
                    "                    <div class=\"wrap\">\n" +
                    "                        <div class=\"main\">\n" +
                    "                            <strong class=\"subject\">\n" +
                    "                                <a href=\"./qna/show.html\">");
            stringBuilder.append(article.getBody());
            stringBuilder.append("</a>\n" +
                    "                            </strong>\n" +
                    "                            <div class=\"auth-info\">\n" +
                    "                                <i class=\"icon-add-comment\"></i>\n" +
                    "                                <span class=\"time\">");
            stringBuilder.append(article.getDate());
            stringBuilder.append("</span>\n" +
                    "                                <class=\"author\">");
            stringBuilder.append(article.getUserId());
            stringBuilder.append("</a>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"reply\" title=\"댓글\">\n" +
                    "                                <i class=\"icon-reply\"></i>\n" +
                    "                                <span class=\"point\">"+ (++articleNum)+"</span>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </li>\n");

        }

        return stringBuilder.toString();
    }
}
