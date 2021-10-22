package com.payhada.admin.main.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KeyDAO {

	int setAesKey(String dbKey);
	
}
