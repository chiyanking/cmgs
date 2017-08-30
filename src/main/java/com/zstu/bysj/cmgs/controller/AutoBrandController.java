package com.zstu.bysj.cmgs.controller;

import com.zstu.bysj.cmgs.model.dto.AutoBrandDto;
import com.zstu.bysj.cmgs.model.entity.AutoBrand;
import com.zstu.bysj.cmgs.service.AutoBrandService;
import com.zstu.bysj.cmgs.service.AutoHomeCrawlService;
import com.zstu.bysj.cmgs.service.AutoSeriesService;
import com.zstu.bysj.cmgs.util.ResultWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌
 * 
 * @author irving
 *
 */
@Controller
public class AutoBrandController {

	@Resource
	private AutoBrandService autoBrandService;
	@Resource
	private AutoSeriesService autoSeriesService;
	@Resource
	private AutoHomeCrawlService autoHomeCrawlService;

	/**
	 * 品牌列表界面
	 */
	@RequestMapping("/brand/list")
	public ModelAndView brandView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/brand/brandList");
		return mv;
	}

	/**
	 * 获得品牌列表
	 */
	@RequestMapping("/brand/list/get/data")
	public @ResponseBody AutoBrandDto getBrandList(@RequestBody AutoBrandDto param) {
		AutoBrand autoBrand = new AutoBrand();
		autoBrand.setName(param.getSearch().getName());
		List<AutoBrand> brandList = autoBrandService.findList(autoBrand, param.getStart(), param.getPageSize());
		param.setData(brandList);
		param.setTotalSize(autoBrandService.findCount(autoBrand));
		return param;
	}

	/**
	 * 获得所有品牌
	 */
	@RequestMapping("/brand/all")
	public @ResponseBody String getAllBrand() {
		List<AutoBrand> brandList = autoBrandService.findList();
		return ResultWrapper.success(brandList);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/brand/save")
	public @ResponseBody String saveBrand(@RequestBody AutoBrand autoBrand) {
		autoBrandService.save(autoBrand);
		return ResultWrapper.success("品牌更新成功");
	}

	/**
	 * 同步所有数据
	 */
	@RequestMapping("/brand/crawl/all")
	public @ResponseBody String crawlBrand() {
		autoHomeCrawlService.crawlAll();
		return ResultWrapper.success("同步所有数据成功");
	}

	/**
	 * 按品牌同步数据
	 */
	@RequestMapping("/brand/crawl")
	public @ResponseBody String crawlBrand(Integer brandId) {
		AutoBrand brand = autoBrandService.findById(brandId);
		if (null != brand && null != brand.getAutoHomeId() && 0 != brand.getAutoHomeId()) {
			autoHomeCrawlService.crawlSeriesAndModel(brandId, brand.getAutoHomeId());
			return ResultWrapper.success("按品牌同步数据成功");
		}
		return ResultWrapper.fail("该品牌不存在");
	}

}
