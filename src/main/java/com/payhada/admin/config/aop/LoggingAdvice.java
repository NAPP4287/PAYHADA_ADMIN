/**
 * LogginAspect 의 Pointcut 과 매칭되어 실행되는 부분
 */
package com.payhada.admin.config.aop;

import com.payhada.admin.model.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Slf4j
@Component
public class LoggingAdvice {

    private static final String SYSTEM_ID = "PAYHADA";
    private static final String APPLICATION_ID = "Admin";
    private static final DateFormat LOG_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final String REQUIRED_RESPONSE_URI_ARR = "/users";

    /**
     * 기본 로그 출력 대상 정의 : 모든 API 컨트롤러 대상
     */
    @Pointcut("execution(* com.payhada.admin.controller..*Controller.*(..))")
    private static void advicePoint() {}

    /**
     * 요청 실행 전/후에 로그 출력
     */
    @Around("advicePoint()")
    public Object logAction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceedReturnValue = null;

        String reqId = UUID.randomUUID().toString(); // 요청에 대한 requestId 를 부여하여 이슈 추적 시 사용
        String httpMethod = null, requestUrl = null, clientIp = null, clientUsername = null,
                clientUserNo = null, payload = null, response = null, requestUri = null;

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest();

            httpMethod = request.getMethod();
            requestUri = request.getRequestURI();
            requestUrl = request.getRequestURL().toString();
            LoginDTO loginDTO = (LoginDTO) principal;
            Enumeration<String> params = request.getParameterNames();
            clientIp = getClientIP(request);
            clientUsername = loginDTO.getUsername();
            clientUserNo = loginDTO.getUserNo();
            payload = getRequestPayload(request, params);
        } catch (Exception e) {
            // 파싱 에러가 발생해도 요청 실행은 진행되어야함
            log.error("logAction() HttpServletRequest 파싱에 실패하였습니다. >> " + e.toString());
        }

        /** 요청 실행 전 로그 출력 */
        log.info(setLogFormat(reqId, httpMethod, requestUrl, clientIp, clientUsername,
                clientUserNo, payload, null));

        /** 요청 실행 */
        proceedReturnValue = joinPoint.proceed();

        /** 요청 실행 후 로그 출력 */
//        if(isRequiredResponseData(requestUri)) {
//            log.info(setLogFormat(reqId, httpMethod, requestUrl, clientIp, clientUsername,
//                    clientUserNo, null, proceedReturnValue.toString()));
//        }

        return proceedReturnValue;
    }

    /**
     * 로그 출력 포맷
     * ==========================
     * {
     *     systemId: "PAYHADA",
     *     applicationId: "Admin",
     *     currentTime: "yyyyMMddHHmmss",
     *     requestId: "",
     *     httpMethod: "",
     *     requestUrl: "",
     *     clientIp: "",
     *     clientUsername: "",
     *     clientUserNo: "",
     *     payload: "",
     *     response: ""
     * }
     * ==========================
     */
    protected String setLogFormat(String reqId, String httpMethod, String requestUrl,
                                   String clientIp, String clientUsername, String clientUserNo,
                                   String payload, String response) {
        Map<String, Object> logMap = new LinkedHashMap<>();

        logMap.put("systemId", SYSTEM_ID);
        logMap.put("applicationId", APPLICATION_ID);
        logMap.put("currentTime", LOG_DATE_FORMAT.format(new Date()));
        logMap.put("requestId", reqId);
        logMap.put("httpMethod", httpMethod);
        logMap.put("requestUrl", requestUrl);
        logMap.put("clientIp", clientIp);
        logMap.put("clientUsername", clientUsername);
        logMap.put("clientUserNo", clientUserNo);
        if(payload != null) logMap.put("payload", payload);
        if(response != null) logMap.put("response", response);

        return logMap.toString();
    }

    /**
     * 응답 데이터를 출력해야하는 URL인지 확인
     *  개인 정보 열람 or 중요한 정보가 포함된 URI PATH 체크
     *  - return true 시, 요청 실행 후 응답데이터 출력
     */
    private static boolean isRequiredResponseData(String param) {
        boolean isRequired = false;
        String[] requiredUris = REQUIRED_RESPONSE_URI_ARR.split(",");
        try {
            for(String uri : requiredUris) {
                if(param.startsWith(uri)) {
                    isRequired = true;
                }//end if
            }//end for
        } catch (Exception e) {
            log.error("isRequiredResponseData() ERROR >> " + e.toString());
        }
        return isRequired;
    }


    protected static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    protected String getRequestPayload(HttpServletRequest request, Enumeration<String> params) {
        return Collections.list(request.getParameterNames()).stream()
                        .map(p -> p + " : " + Arrays.asList( request.getParameterValues(p)) )
                        .collect(Collectors.joining(", "));
    }
}
