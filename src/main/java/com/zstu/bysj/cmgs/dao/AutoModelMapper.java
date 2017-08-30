package com.zstu.bysj.cmgs.dao;

import com.zstu.bysj.cmgs.model.entity.AutoModel;

import java.util.List;
import java.util.Map;

public interface AutoModelMapper {

	List<AutoModel> findList(Map<String, Object> param);

	int findCount(Map<String, Object> param);

	AutoModel findById(Integer id);

	AutoModel findByAutoHomeId(Integer autoHomeId);

	void insert(AutoModel autoModel);

	void update(AutoModel autoModel);

	void updateByAutoHomeId(AutoModel autoModel);

}