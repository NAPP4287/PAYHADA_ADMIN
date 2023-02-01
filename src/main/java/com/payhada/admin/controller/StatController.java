package com.payhada.admin.controller;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.Response;
import com.payhada.admin.service.StatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

import static com.payhada.admin.common.util.MessageSourceUtils.getMessage;

@RestController
@Slf4j
@RequestMapping("/api/v2/stat")
public class StatController {

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/daily")
    public ResponseEntity<Response> getDailyData(HttpServletRequest request,
                                                 @RequestParam(required = false) Integer year,
                                                 @RequestParam(required = false) Integer month) {
        // year, month 가 null 일 경우 현재 년도와 월을 가져옴
        ZoneId zoneId = statService.getZoneId(request);
        LocalDate localDateNow = LocalDate.now(zoneId);
        if (year == null) year = localDateNow.getYear();
        if (month == null) month = localDateNow.getMonthValue();

        int dayOfMonth;
        // 조회하려는 데이터가 이번달 이면 오늘 날짜 / 아니라면 마지막 일을 가져옴
        if (year.equals(localDateNow.getYear()) && month.equals(localDateNow.getMonthValue())) {
            dayOfMonth = localDateNow.getDayOfMonth();
        } else {
            YearMonth yearMonth = YearMonth.from(LocalDate.of(year, month, 1));
            dayOfMonth = yearMonth.lengthOfMonth();
        }

        List<Map<String, Object>> list = statService.getDailyData(year, month, dayOfMonth);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);

        return ResponseCode.API_STATUS_OK.toResponseEntity(resultMap);
    }

    @GetMapping("/monthly")
    public ResponseEntity<Response> getMonthlyData(HttpServletRequest request) {
        List<Map<String, Object>> list = statService.getMonthlyData(request);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);

        return ResponseCode.API_STATUS_OK.toResponseEntity(resultMap);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> statisticAllData() {
        // 지난달 데이터 조회
        Integer lastMonthUserCount = statService.getLastMonthUserCount();
        Integer lastMonthCertUserCount = statService.getLastMonthCertUserCount();
        Integer lastMonthTxnCount = statService.getLastMonthTxnCount();
        BigDecimal lastMonthRemittance = statService.getLastMonthRemittance();

        Map<String, Object> lastMonth = new LinkedHashMap<>();
        lastMonth.put("userCount", lastMonthUserCount);
        lastMonth.put("certUserCount", lastMonthCertUserCount);
        lastMonth.put("txnCount", lastMonthTxnCount);
        lastMonth.put("remittance", lastMonthRemittance);

        // 이번주 데이터 조회
        Integer thisWeekUserCount = statService.getThisWeekUserCount();
        Integer thisWeekCertUserCount = statService.getThisWeekCertUserCount();
        Integer thisWeekTxnCount = statService.getThisWeekTxnCount();
        BigDecimal thisWeekRemittance = statService.getThisWeekRemittance();

        Map<String, Object> thisWeek = new LinkedHashMap<>();
        thisWeek.put("userCount", thisWeekUserCount);
        thisWeek.put("certUserCount", thisWeekCertUserCount);
        thisWeek.put("txnCount", thisWeekTxnCount);
        thisWeek.put("remittance", thisWeekRemittance);

        // 이번달 데이터 조회
        Integer thisMonthUserCount = statService.getThisMonthUserCount();
        Integer thisMonthCertUserCount = statService.getThisMonthCertUserCount();
        Integer thisMonthTxnCount = statService.getThisMonthTxnCount();
        BigDecimal thisMonthRemittance = statService.getThisMonthRemittance();

        Map<String, Object> thisMonth = new LinkedHashMap<>();
        thisMonth.put("userCount", thisMonthUserCount);
        thisMonth.put("certUserCount", thisMonthCertUserCount);
        thisMonth.put("txnCount", thisMonthTxnCount);
        thisMonth.put("remittance", thisMonthRemittance);

        // 전체 데이터 조회
        Integer allUserCount = statService.getAllUserCount();
        Integer allCertUserCount = statService.getAllCertUserCount();
        Integer allTxnCount = statService.getAllTxnCount();
        BigDecimal allRemittance = statService.getAllRemittance();

        Map<String, Object> all = new LinkedHashMap<>();
        all.put("userCount", allUserCount);
        all.put("certUserCount", allCertUserCount);
        all.put("txnCount", allTxnCount);
        all.put("remittance", allRemittance);

        // 어제 데이터 조회
        Integer lastDayUserCount = statService.getLastDayUserCount();
        Integer lastDayCertUserCount = statService.getLastDayCertUserCount();
        Integer lastDayTxnCount = statService.getLastDayTxnCount();
        BigDecimal lastDayRemittance = statService.getLastDayRemittance();

        Map<String, Object> lastDay = new LinkedHashMap<>();
        lastDay.put("userCount", lastDayUserCount);
        lastDay.put("certUserCount", lastDayCertUserCount);
        lastDay.put("txnCount", lastDayTxnCount);
        lastDay.put("remittance", lastDayRemittance);

        // 월별 송금액 조회
        List<Map<String, Object>> monthlyRemittance = statService.getMonthlyRemittance();

        Map<String, Object> monthlyRemittanceMap = new LinkedHashMap<>();
        for (Month month : Month.values()) {
            String monthName = month.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ROOT).toUpperCase(Locale.ROOT);
            int monthIntValue = month.getValue();

            BigDecimal krw;
            BigDecimal usd;
            Map<String, Object> amountMap = new HashMap<>();
            Map<String, Object> map = monthlyRemittance.stream().filter(v -> {
                Number monthValue = (Number) v.get("month");
                return monthValue.intValue() == monthIntValue;
            }).findFirst().orElse(new HashMap<>());
            if (map.isEmpty()) {
                krw = null;
                usd = null;
            } else {
                krw = new BigDecimal(map.get("KRW").toString());
                usd = new BigDecimal(map.get("USD").toString());
            }

            amountMap.put("KRW", krw);
            amountMap.put("USD", usd);

            monthlyRemittanceMap.put(monthName, amountMap);
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();

        resultMap.put("lastMonth", lastMonth);
        resultMap.put("thisWeek", thisWeek);
        resultMap.put("thisMonth", thisMonth);
        resultMap.put("all", all);
        resultMap.put("lastDay", lastDay);
        resultMap.put("monthlyRemittance", monthlyRemittanceMap);

        return ResponseCode.API_STATUS_OK.toResponseEntity(resultMap);
    }
}
