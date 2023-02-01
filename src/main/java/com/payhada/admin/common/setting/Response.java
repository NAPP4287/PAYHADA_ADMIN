package com.payhada.admin.common.setting;

import com.payhada.admin.code.ErrorCode;
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

    public static Response create(Integer resultCode, String resultMsg) {
        return Response.builder()
                .resultCode(resultCode)
                .resultMsg(resultMsg)
                .build();
    }

    public static Response create(Integer resultCode, Map<String, Object> data) {
        return Response.builder()
                .resultCode(resultCode)
                .data(data)
                .build();
    }

    public static Response create(Integer resultCode, ErrorCode errorCode) {
        return Response.builder()
                .resultCode(resultCode)
                .error(Error.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build())
                .build();
    }

    public static Response create(Integer resultCode, String resultMsg, Map<String, Object> data) {
        return Response.builder()
                .resultCode(resultCode)
                .resultMsg(resultMsg)
                .data(data)
                .build();
    }

    public static Response create(Integer resultCode, String resultMsg, ErrorCode errorCode) {
        return Response.builder()
                .resultCode(resultCode)
                .resultMsg(resultMsg)
                .error(Error.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build())
                .build();
    }
}
