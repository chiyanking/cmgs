package com.zstu.bysj.cmgs.service;

import com.zstu.bysj.cmgs.model.entity.AutoModel;

import java.util.List;

/**
 * 车型
 * 
 * @author irving
 *
 */
public interface AutoModelService {

	List<AutoModel> findList(AutoModel autoModel, Integer start, Integer pageSize);

	int findCount(AutoModel autoModel);

	List<AutoModel> findList(Integer seriesId);

	AutoModel findById(Integer id);

	AutoModel findByAutoHomeId(Integer autoHomeId);

	void save(AutoModel autoModel);

	void saveByCrawl(AutoModel autoModel);

}