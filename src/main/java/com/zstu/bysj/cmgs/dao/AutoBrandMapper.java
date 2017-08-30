package com.zstu.bysj.cmgs.dao;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;

import java.util.List;
import java.util.Map;

public interface AutoBrandMapper {
	
	List<AutoBrand> findList(Map<String, Object> param);
    
	int findCount(Map<String, Object> param);
	
	AutoBrand findById(Integer id);

	AutoBrand findByAutoHomeId(Integer autoHomeId);

	void insert(AutoBrand autoBrand);
	
	void update(AutoBrand autoBrand);

	void updateByAutoHomeId(AutoBrand autoBrand);

}