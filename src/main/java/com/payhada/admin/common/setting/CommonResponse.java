package com.payhada.admin.common.setting;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

import static com.payhada.admin.common.util.MessageSourceUtils.getMessage;

@Getter
@NoArgsConstructor
public class CommonResponse {
    private String resultCode;
    private String resultMsg;
    private Map<String, Object> data;

    @Builder
    public CommonResponse(String resultCode, String resultMsg, Map<String, Object> data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    /**
     * 예시) 현재 Locale: en, {@code MessageSourceUtils.getMessage("E0000")} 실행시 "success" 리턴
     * @param code 응답하고자 하는 code 값 (다국어처리를 위한 key 값 * resources/messages/message_{locale}.properties 에 정의되어 있어야 함)
     * @return CommonResponse 객체 리턴
     */
    public static CommonResponse create(String code) {
        return CommonResponse.builder()
                .resultCode(code)
                .resultMsg(getMessage(code))
                .build();
    }

    /**
     * {@code create(String code)} 에서 추가로 data 값을 이용할 때 사용
     */
    public static CommonResponse create(String code, Map<String, Object> data) {
        return CommonResponse.builder()
                .resultCode(code)
                .resultMsg(getMessage(code))
                .data(data)
                .build();
    }
}
