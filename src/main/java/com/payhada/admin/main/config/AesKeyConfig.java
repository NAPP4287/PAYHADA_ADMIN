package com.payhada.admin.main.config;

import com.payhada.admin.main.service.KeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AesKeyConfig {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    KeyService keyService;

    String dbKey = "";

    public void setDbKey(String key) {
        keyService.setDbKey(key);
        dbKey = key;
    }

    public String getDbKey() {
        return dbKey;
    }

}
