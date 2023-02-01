package com.payhada.admin.config.aop;

import com.payhada.admin.common.keymanager.AesKeyConfig;
import com.payhada.admin.service.KeyService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DbKeyAop {

//    final String SET_KEY_EXECUTION = "execution(public * com.payhada.admin.service.*.*(..))";
    private static final String POINT_CUT = "within(com.payhada.admin.service..*)";

    private final AesKeyConfig keyConfig;

    private final KeyService keyService;

    public DbKeyAop(AesKeyConfig keyConfig, KeyService keyService) {
        this.keyConfig = keyConfig;
        this.keyService = keyService;
    }

    @Before(POINT_CUT)
    public void before(JoinPoint joinPoint) {
        if (Boolean.TRUE.equals(matchStr(joinPoint.getTarget().toString(), "KeyServiceImpl")))
            return;
        keyService.setDbKey(keyConfig.getDbKey());
    }

    public static Boolean matchStr(String str1, String str2) {
        return str1.contains(str2);
    }
}
