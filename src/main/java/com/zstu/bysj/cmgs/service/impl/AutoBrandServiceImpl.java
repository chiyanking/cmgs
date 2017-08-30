package com.zstu.bysj.cmgs.service.impl;

import com.zstu.bysj.cmgs.dao.AutoBrandMapper;
import com.zstu.bysj.cmgs.model.entity.AutoBrand;
import com.zstu.bysj.cmgs.service.AutoBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AutoBrandServiceImpl implements AutoBrandService {

	@Resource
	private AutoBrandMapper autoBrandMapper;

	@Override
	public List<AutoBrand> findList(AutoBrand autoBrand, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("pageSize", pageSize);
		if (null != autoBrand) {
			param.put("name", autoBrand.getName());
		}
		return autoBrandMapper.findList(param);
	}

	@Override
	public int findCount(AutoBrand autoBrand) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null != autoBrand) {
			param.put("name", autoBrand.getName());
		}
		return autoBrandMapper.findCount(param);
	}

	@Override
	public List<AutoBrand> findList() {
		return this.findList(null, null, null);
	}

	@Override
	public AutoBrand findById(Integer id) {
		return autoBrandMapper.findById(id);
	}

	@Override
	public AutoBrand findByAutoHomeId(Integer autoHomeId) {
		return autoBrandMapper.findByAutoHomeId(autoHomeId);
	}

	@Override
	public void save(AutoBrand autoBrand) {
		AutoBrand entity = this.findById(autoBrand.getId());
		if (null == entity) {
			autoBrandMapper.insert(autoBrand);
		} else {
			autoBrandMapper.update(autoBrand);
		}
	}

	@Override
	public void saveByCrawl(AutoBrand autoBrand) {
		AutoBrand entity = this.findByAutoHomeId(autoBrand.getAutoHomeId());
		if (null == entity) {
			autoBrandMapper.insert(autoBrand);
		} else {
			autoBrandMapper.updateByAutoHomeId(autoBrand);
		}
	}

}
