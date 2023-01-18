package response;

import enums.ContentTypeEnum;
import enums.ControllerTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class NewResponse {


    private ResponseStatusLine responseStatusLine;
    private ResponseHeader responseHeader;
    private ResponseBody responseBody;
    private List<String> responseAdder;
    private static final String NEW_LINE = "\r\n";

    public NewResponse(Builder builder){
        this.responseStatusLine = builder.responseStatusLine;
        this.responseHeader = builder.responseHeader;
        this.responseBody = builder.responseBody;
        this.responseAdder = builder.responseAdder;
    }

    public ResponseStatusLine getResponseStatusLine() {
        return responseStatusLine;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public List<String> getResponseAdder() {
        return responseAdder;
    }

    // 추가된 명령들을 response에 적기 위해 하나의 string 문장으로 형성

    public static class Builder {
        private ResponseStatusLine responseStatusLine;
        private ResponseHeader responseHeader;
        private ResponseBody responseBody;
        private List<String> responseAdder = new ArrayList<>();
        public Builder builder(){
            return this;
        }

        public Builder setResponseStatusLine(ControllerTypeEnum controllerTypeEnum) {
            this.responseStatusLine = new ResponseStatusLine(controllerTypeEnum);
            return this;
        }

        public Builder setResponseHeader(ContentTypeEnum contentTypeEnum, int lengthOfBodyContent) {
            this.responseHeader = new ResponseHeader(contentTypeEnum,lengthOfBodyContent);
            return this;
        }

        public Builder setResponseBody(byte[] body) {
            this.responseBody = new ResponseBody(body);
            return this;
        }
        public Builder addResponseHeader(String addedLine) {
            this.responseAdder.add(addedLine);
            return this;
        }
        public Builder addCookieHeader(String sid){
            this.responseHeader.addCookieLine(sid);
            return this;
        }

        public NewResponse build(){
            return new NewResponse(this);
        }
    }

}
