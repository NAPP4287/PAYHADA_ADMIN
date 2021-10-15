package com.payhada.admin.main.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KeyDao {

	public int setAesKey(String dbKey);
	
}
