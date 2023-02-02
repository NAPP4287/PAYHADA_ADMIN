package com.payhada.admin.service;

import com.payhada.admin.dao.StatDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;

@Service
@Slf4j
public class StatService {

    private final StatDAO statDAO;

    public StatService(StatDAO statDAO) {
        this.statDAO = statDAO;
    }

    public ZoneId getZoneId(HttpServletRequest request) {
        TimeZone timeZone = RequestContextUtils.getTimeZone(request);
        if (timeZone == null) {
            return ZoneId.of("Asia/Seoul");
        } else {
            return timeZone.toZoneId();
        }
    }

    // 해당월의 일별 유저 수
    public List<Map<String, Object>> getDailyUserCount(Map<String, Object> paramMap) {
        return statDAO.selectDailyUserCount(paramMap);
    }

    // 해당월의 일별 인증 받은 유저 수
    public List<Map<String, Object>> getDailyCertUserCount(Map<String, Object> paramMap) {
        return statDAO.selectDailyCertUserCount(paramMap);
    }

    // 해당월의 일별 송금 건 수
    public List<Map<String, Object>> getDailyTxnCount(Map<String, Object> paramMap) {
        return statDAO.selectDailyTxnCount(paramMap);
    }

    // 해당월의 일별 송금액
    public List<Map<String, Object>> getDailyRemittance(Map<String, Object> paramMap) {
        return statDAO.selectDailyRemittance(paramMap);
    }

    public List<Map<String, Object>> getDailyData(Integer year, Integer month, int dayOfMonth) {
        List<Map<String, Object>> list = new LinkedList<>();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("year", year);
        paramMap.put("month", month);

        List<Map<String, Object>> dailyUserCountList = getDailyUserCount(paramMap);
        List<Map<String, Object>> dailyCertUserCountList = getDailyCertUserCount(paramMap);
        List<Map<String, Object>> dailyTxnCountList = getDailyTxnCount(paramMap);
        List<Map<String, Object>> dailyRemittanceList = getDailyRemittance(paramMap);

        for (int i = 1; i <= dayOfMonth; i++) {
            Map<String, Object> map = new LinkedHashMap<>();

            int finalI = i;
            Predicate<Map<String, Object>> thisDayPredicate = obj -> {
                Number day = (Number) obj.get("day");
                return day.intValue() == finalI;
            };

            map.put("day", i);
            map.put("userCount", Optional.ofNullable(getCountFromList(dailyUserCountList, thisDayPredicate, "userCount")).orElse(0));
            map.put("certUserCount", Optional.ofNullable(getCountFromList(dailyCertUserCountList, thisDayPredicate, "certUserCount")).orElse(0));
            map.put("txnCount", Optional.ofNullable(getCountFromList(dailyTxnCountList, thisDayPredicate, "txnCount")).orElse(0));
            map.put("remittance", Optional.ofNullable(getCountFromList(dailyRemittanceList, thisDayPredicate, "remittance")).orElse(BigDecimal.ZERO));

            list.add(map);
        }

        // 정렬
        list.sort((o1, o2) -> {
            Integer o1Day = (Integer) o1.get("day");
            Integer o2Day = (Integer) o2.get("day");

            return o1Day.compareTo(o2Day);
        });

        return list;
    }

    public Object getCountFromList(List<Map<String, Object>> list, Predicate<Map<String, Object>> predicate, String fieldName) {
        Map<String, Object> tempMap = list.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);

        if (tempMap != null) {
            return tempMap.get(fieldName);
        } else {
            return null;
        }
    }

    // 월별 유저 수
    public List<Map<String, Object>> getMonthlyUserCount() {
        return statDAO.selectMonthlyUserCount();
    }

    // 월별 인증 유저 수
    public List<Map<String, Object>> getMonthlyCertUserCount() {
        return statDAO.selectMonthlyCertUserCount();
    }

    // 월별 송금 건 수
    public List<Map<String, Object>> getMonthlyTxnCount() {
        return statDAO.selectMonthlyTxnCount();
    }

    // 월별 송금액
    public List<Map<String, Object>> getMonthlyRemittance() {
        return statDAO.selectMonthlyRemittance();
    }

    public List<Map<String, Object>> getMonthlyData(HttpServletRequest request) {
        List<Map<String, Object>> list = new LinkedList<>();

        List<Map<String, Object>> monthlyUserCountList = getMonthlyUserCount();
        List<Map<String, Object>> monthlyCertUserCountList = getMonthlyCertUserCount();
        List<Map<String, Object>> monthlyTxnCountList = getMonthlyTxnCount();
        List<Map<String, Object>> monthlyRemittanceList = getMonthlyRemittance();

        int currentMonth = LocalDate.now(getZoneId(request)).getMonthValue();

        for (int i = 1; i <= currentMonth; i++) {
            Map<String, Object> map = new LinkedHashMap<>();

            int finalI = i;
            Predicate<Map<String, Object>> thisMonthPredicate = obj -> {
                Number month = (Number) obj.get("month");
                return month.intValue() == finalI;
            };

            map.put("month", i);
            map.put("userCount", Optional.ofNullable(getCountFromList(monthlyUserCountList, thisMonthPredicate, "userCount")).orElse(0));
            map.put("certUserCount", Optional.ofNullable(getCountFromList(monthlyCertUserCountList, thisMonthPredicate, "certUserCount")).orElse(0));
            map.put("txnCount", Optional.ofNullable(getCountFromList(monthlyTxnCountList, thisMonthPredicate, "txnCount")).orElse(0));
            map.put("remittance", Optional.ofNullable(getCountFromList(monthlyRemittanceList, thisMonthPredicate, "KRW")).orElse(0));

            list.add(map);
        }

        list.sort((o1, o2) -> {
            Integer o1Month = (Integer) o1.get("month");
            Integer o2Month = (Integer) o2.get("month");

            return o1Month.compareTo(o2Month);
        });

        return list;
    }

    // 지난 달 유저 수
    public Integer getLastMonthUserCount() {
        return statDAO.selectLastMonthUserCount();
    }

    // 지난 달 인증유저 수
    public Integer getLastMonthCertUserCount() {
        return statDAO.selectLastMonthCertUserCount();
    }

    // 지난 달 송금 건수
    public Integer getLastMonthTxnCount() {
        return statDAO.selectLastMonthTxnCount();
    }

    // 지난 달 송금액
    public BigDecimal getLastMonthRemittance() {
        String remittance = statDAO.selectLastMonthRemittance();

        return remittance == null ? BigDecimal.ZERO : new BigDecimal(remittance);
    }

    // 이번주 유저 수
    public Integer getThisWeekUserCount() {
        return statDAO.selectThisWeekUserCount();
    }

    // 이번주 인증유저 수
    public Integer getThisWeekCertUserCount() {
        return statDAO.selectThisWeekCertUserCount();
    }

    // 이번주 송금건 수
    public Integer getThisWeekTxnCount() {
        return statDAO.selectThisWeekTxnCount();
    }

    // 이번주 송금액
    public BigDecimal getThisWeekRemittance() {
        String remittance = statDAO.selectThisWeekRemittance();

        return remittance == null ? BigDecimal.ZERO : new BigDecimal(remittance);
    }

    // 이번달 유저 수
    public Integer getThisMonthUserCount() {
        return statDAO.selectThisMonthUserCount();
    }

    // 이번달 인증유저 수
    public Integer getThisMonthCertUserCount() {
        return statDAO.selectThisMonthCertUserCount();
    }

    // 이번달 송금건 수
    public Integer getThisMonthTxnCount() {
        return statDAO.selectThisMonthTxnCount();
    }

    // 이번달 송금액
    public BigDecimal getThisMonthRemittance() {
        String remittance = statDAO.selectThisMonthRemittance();

        return remittance == null ? BigDecimal.ZERO : new BigDecimal(remittance);
    }

    // 총 유저 수
    public Integer getAllUserCount() {
        return statDAO.selectAllUserCount();
    }

    // 총 인증 유저 수
    public Integer getAllCertUserCount() {
        return statDAO.selectAllCertUserCount();
    }

    // 총 송금건 수
    public Integer getAllTxnCount() {
        return statDAO.selectAllTxnCount();
    }

    // 총 송금액
    public BigDecimal getAllRemittance() {
        String remittance = statDAO.selectAllRemittance();

        return remittance == null ? BigDecimal.ZERO : new BigDecimal(remittance);
    }

    // 어제 유저 수
    public Integer getLastDayUserCount() {
        return statDAO.selectLastDayUserCount();
    }

    // 어제 인증 유저 수
    public Integer getLastDayCertUserCount() {
        return statDAO.selectLastDayCertUserCount();
    }

    // 어제 송금건 수
    public Integer getLastDayTxnCount() {
        return statDAO.selectLastDayTxnCount();
    }

    // 어제 송금액
    public BigDecimal getLastDayRemittance() {
        String remittance = statDAO.selectLastDayRemittance();

        return remittance == null ? BigDecimal.ZERO : new BigDecimal(remittance);
    }

}
