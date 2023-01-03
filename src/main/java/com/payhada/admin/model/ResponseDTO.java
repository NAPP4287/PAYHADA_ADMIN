package com.payhada.admin.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ResponseDTO {
    private Integer resultCode;
    private String resultMsg;
    private Map<String, Object> data;

    @Builder
    public ResponseDTO(Integer resultCode, String resultMsg, Map<String, Object> data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }
}
