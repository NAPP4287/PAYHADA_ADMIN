package com.payhada.admin.service;

import com.payhada.admin.dao.KeyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyServiceImpl implements KeyService {
	Logger log = LoggerFactory.getLogger(KeyServiceImpl.class);

	@Autowired
	KeyDAO keyDao;

	@Override
	public int setDbKey(String dbKey) {
		return keyDao.setAesKey(dbKey);
	}
}
