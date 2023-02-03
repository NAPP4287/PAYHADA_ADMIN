package com.payhada.admin.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Component
@Slf4j
public class MessageSourceUtils {

    // 기본 디폴트 Locale
    private static final Locale DEFAULT_LOCALE = Locale.KOREA;

    // Spring-Context MessageSource 모듈
    private static MessageSource messageSourceStatic;

    @Autowired
    public void setMessageSourceStatic(MessageSource messageSource) {
        MessageSourceUtils.messageSourceStatic = messageSource;
    }

    /**
     * 예시) 현재 Locale: en, {@code MessageSourceUtils.getMessage("E0000")} 실행시 "success" 리턴
     * @param code 다국어처리를 위한 key 값 * resources/messages/message_{locale}.properties 에 정의되어 있어야 함
     * @return message_{locale}.properties 에 정의 되어 있는 메세지
     */
    public static String getMessage(String code) {
        Locale locale = DEFAULT_LOCALE;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (attributes.getRequest().getSession() != null) {
            HttpSession session = attributes.getRequest().getSession();
            locale = getLocaleFromSession(session);
        }

        return messageSourceStatic.getMessage(code, null, locale);
    }

    public static String getMessage(String code, Locale locale) {
        return messageSourceStatic.getMessage(code, null, locale);
    }

    private static Locale getLocaleFromSession(HttpSession session) {
        Locale locale = DEFAULT_LOCALE;

        try {
            locale = (Locale) session.getAttribute("locale");
            if (locale == null) {
                return DEFAULT_LOCALE;
            }
        } catch (Exception e) {
            log.error("Occurred From MessageSourceUtils.getLocaleFromSession() :: {}", e.getMessage());
        }

        return locale;
    }
}
