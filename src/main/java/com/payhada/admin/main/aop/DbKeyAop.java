package com.payhada.admin.main.aop;

import com.payhada.admin.main.config.AesKeyConfig;
import com.payhada.admin.main.service.KeyService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DbKeyAop {

    static final Logger log = LoggerFactory.getLogger(DbKeyAop.class.getName());

    final String SET_KEY_EXECUTION = "execution(public * com.payhada.admin.main.service.*.*(..))";

    @Autowired
    AesKeyConfig keyConfig;

    @Autowired
    KeyService keyService;

    @Before(SET_KEY_EXECUTION)
    public void before(JoinPoint joinPoint) {
        if(matchStr(joinPoint.getTarget().toString(), "KeyServiceImpl"))
            return;
        keyService.setDbKey(keyConfig.getDbKey());
    }

    public static Boolean matchStr(String str1, String str2) {
        return str1.indexOf(str2) > -1;
    }
}
