package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {

    private int pageViewCnt;
    private int startIdx;
    private int pageNum;
    private String startDate;
    private String endDate;
    private String searchType;
    private String searchOption;
    private String sortName;
    private String sortType;

    private List<String> searchTypeList;

}
