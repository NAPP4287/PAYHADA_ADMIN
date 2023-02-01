package com.payhada.admin.common.setting;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class Response {
    private Integer resultCode;
    private String resultMsg;
    private Map<String, Object> data;
    private Error error;

    @Builder
    public Response(Integer resultCode, String resultMsg, Map<String, Object> data, Error error) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
        this.error = error;
    }

    @Getter
    @NoArgsConstructor
    public static class Error {
        private String code;
        private String message;

        @Builder
        public Error(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
