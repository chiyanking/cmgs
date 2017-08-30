package com.zstu.bysj.cmgs.dao;

import com.zstu.bysj.cmgs.model.entity.AutoSeries;

import java.util.List;
import java.util.Map;

public interface AutoSeriesMapper {
	
	List<AutoSeries> findList(Map<String, Object> param);
    
	int findCount(Map<String, Object> param);
	
	AutoSeries findById(Integer id);

	AutoSeries findByAutoHomeId(Integer autoHomeId);

	void insert(AutoSeries autoSeries);
	
	void update(AutoSeries autoSeries);

	void updateByAutoHomeId(AutoSeries autoSeries);

}