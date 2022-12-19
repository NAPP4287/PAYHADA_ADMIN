package com.payhada.admin.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KeyDAO {

	int setAesKey(String dbKey);
	
}
