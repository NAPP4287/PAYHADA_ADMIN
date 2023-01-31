package com.payhada.admin.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Component
@Slf4j
public class MessageSourceUtils {

    private static final Locale DEFAULT_LOCALE = Locale.KOREA;

    private static MessageSource messageSourceStatic;

    @Autowired
    public void setMessageSourceStatic(MessageSource messageSource) {
        MessageSourceUtils.messageSourceStatic = messageSource;
    }

    public static String getMessage(String code, @Nullable HttpSession session) {
        Locale locale;

        if (session == null) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            if (attributes.getRequest().getSession() == null) {
                locale = DEFAULT_LOCALE;
            } else {
                session = attributes.getRequest().getSession();
                locale = getLocaleFromSession(session);
            }
        } else {
            locale = getLocaleFromSession(session);
        }

        return messageSourceStatic.getMessage(code, null, locale);
    }

    private static Locale getLocaleFromSession(HttpSession session) {
        Locale locale = (Locale) session.getAttribute("locale");

        if (locale == null) {
            return DEFAULT_LOCALE;
        }

        return locale;
    }
}
