package com.payhada.admin.common.setting;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

import static com.payhada.admin.common.util.MessageSourceUtils.getMessage;

@Getter
@NoArgsConstructor
public class Response {
    private String resultCode;
    private String resultMsg;
    private Map<String, Object> data;

    @Builder
    public Response(String resultCode, String resultMsg, Map<String, Object> data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public static Response create(String responseCode) {
        return Response.builder()
                .resultCode(responseCode)
                .resultMsg(getMessage(responseCode))
                .build();
    }

    public static Response create(String responseCode, Map<String, Object> data) {
        return Response.builder()
                .resultCode(responseCode)
                .resultMsg(getMessage(responseCode))
                .data(data)
                .build();
    }
}
