package com.zstu.bysj.cmgs.service.impl;

import com.zstu.bysj.cmgs.dao.AutoModelMapper;
import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.service.AutoModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AutoModelServiceImpl implements AutoModelService {

	@Resource
	private AutoModelMapper autoModelMapper;

	@Override
	public List<AutoModel> findList(AutoModel autoModel, Integer start, Integer pageSize) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (null == autoModel) {
			autoModel = new AutoModel();
		}
		param.put("search", autoModel);
		param.put("start", start);
		param.put("pageSize", pageSize);
		return autoModelMapper.findList(param);
	}

	@Override
	public int findCount(AutoModel autoModel) {
		if (null == autoModel) {
			autoModel = new AutoModel();
		}
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("search", autoModel);
		return autoModelMapper.findCount(param);
	}

	@Override
	public List<AutoModel> findList(Integer seriesId) {
		AutoModel autoModel = new AutoModel();
		autoModel.setSeriesId(seriesId);
		return this.findList(autoModel, null, null);
	}

	@Override
	public AutoModel findById(Integer id) {
		return autoModelMapper.findById(id);
	}

	@Override
	public AutoModel findByAutoHomeId(Integer autoHomeId) {
		return autoModelMapper.findByAutoHomeId(autoHomeId);
	}

	private void insert(AutoModel autoModel) {
		autoModelMapper.insert(autoModel);
	}

	private void update(AutoModel autoModel) {
		autoModelMapper.update(autoModel);
	}

	@Override
	public void save(AutoModel autoModel) {
		AutoModel entity = this.findById(autoModel.getId());
		if (null == entity) {
			autoModelMapper.insert(autoModel);
		} else {
			autoModelMapper.update(autoModel);
		}
	}

	@Override
	public void saveByCrawl(AutoModel autoModel) {
		AutoModel entity = this.findByAutoHomeId(autoModel.getAutoHomeId());
		if (null == entity) {
			autoModelMapper.insert(autoModel);
		} else {
			autoModelMapper.updateByAutoHomeId(autoModel);
		}
	}

}
