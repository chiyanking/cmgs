package com.zstu.bysj.cmgs.service;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;

import java.util.List;

/**
 * 品牌
 * 
 * @author irving
 *
 */
public interface AutoBrandService {

	List<AutoBrand> findList(AutoBrand autoBrand, Integer start, Integer pageSize);

	int findCount(AutoBrand autoBrand);

	List<AutoBrand> findList();

	AutoBrand findById(Integer id);

	AutoBrand findByAutoHomeId(Integer autoHomeId);

	void save(AutoBrand autoBrand);

	void saveByCrawl(AutoBrand autoBrand);

}