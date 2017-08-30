package com.zstu.bysj.cmgs.service;

import com.zstu.bysj.cmgs.model.entity.AutoSeries;

import java.util.List;

/**
 * 车系
 * 
 * @author irving
 *
 */
public interface AutoSeriesService {

	List<AutoSeries> findList(AutoSeries autoSeries, Integer start, Integer pageSize);

	int findCount(AutoSeries autoSeries);

	List<AutoSeries> findList(Integer brandId);

	AutoSeries findById(Integer id);

	AutoSeries findByAutoHomeId(Integer autoHomeId);

	void save(AutoSeries autoSeries);

	void saveByCrawl(AutoSeries autoSeries);

}