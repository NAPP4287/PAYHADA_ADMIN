package com.payhada.admin.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StatDAO {
    List<Map<String, Object>> selectDailyUserCount(Map<String, Object> paramMap);

    List<Map<String, Object>> selectDailyCertUserCount(Map<String, Object> paramMap);

    List<Map<String, Object>> selectDailyTxnCount(Map<String, Object> paramMap);

    List<Map<String, Object>> selectDailyRemittance(Map<String, Object> paramMap);

    List<Map<String, Object>> selectMonthlyUserCount();

    List<Map<String, Object>> selectMonthlyCertUserCount();

    List<Map<String, Object>> selectMonthlyTxnCount();

    List<Map<String, Object>> selectMonthlyRemittance();

    Integer selectLastMonthUserCount();

    Integer selectLastMonthCertUserCount();

    Integer selectLastMonthTxnCount();

    String selectLastMonthRemittance();

    Integer selectThisWeekUserCount();

    Integer selectThisWeekCertUserCount();

    Integer selectThisWeekTxnCount();

    String selectThisWeekRemittance();

    Integer selectThisMonthUserCount();

    Integer selectThisMonthCertUserCount();

    Integer selectThisMonthTxnCount();

    String selectThisMonthRemittance();

    Integer selectAllUserCount();

    Integer selectAllCertUserCount();

    Integer selectAllTxnCount();

    String selectAllRemittance();

    Integer selectLastDayUserCount();

    Integer selectLastDayCertUserCount();

    Integer selectLastDayTxnCount();

    String selectLastDayRemittance();
}
