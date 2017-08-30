package com.zstu.bysj.cmgs.service.impl;

import com.zstu.bysj.cmgs.dao.AutoSeriesMapper;
import com.zstu.bysj.cmgs.model.entity.AutoSeries;
import com.zstu.bysj.cmgs.service.AutoSeriesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AutoSeriesServiceImpl implements AutoSeriesService {

	@Resource
	private AutoSeriesMapper autoSeriesMapper;

	@Override
	public List<AutoSeries> findList(AutoSeries autoSeries, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("pageSize", pageSize);

		if (null != autoSeries) {
			param.put("brandId", autoSeries.getBrandId());
			param.put("name", autoSeries.getName());
		}
		return autoSeriesMapper.findList(param);
	}

	@Override
	public int findCount(AutoSeries autoSeries) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null != autoSeries) {
			param.put("brandId", autoSeries.getBrandId());
			param.put("name", autoSeries.getName());
		}
		return autoSeriesMapper.findCount(param);
	}

	@Override
	public List<AutoSeries> findList(Integer brandId) {
		AutoSeries autoSeries = new AutoSeries();
		autoSeries.setBrandId(brandId);
		return this.findList(autoSeries, null, null);
	}

	@Override
	public AutoSeries findById(Integer id) {
		return autoSeriesMapper.findById(id);
	}

	@Override
	public AutoSeries findByAutoHomeId(Integer autoHomeId) {
		return autoSeriesMapper.findByAutoHomeId(autoHomeId);
	}

	@Override
	public void save(AutoSeries autoSeries) {
		AutoSeries entity = this.findById(autoSeries.getId());
		if (null == entity) {
			autoSeriesMapper.insert(autoSeries);
		} else {
			autoSeriesMapper.update(autoSeries);
		}
	}

	@Override
	public void saveByCrawl(AutoSeries autoSeries) {
		AutoSeries entity = this.findByAutoHomeId(autoSeries.getAutoHomeId());
		if (null == entity) {
			autoSeriesMapper.insert(autoSeries);
		} else {
			autoSeriesMapper.updateByAutoHomeId(autoSeries);
		}
	}

}
