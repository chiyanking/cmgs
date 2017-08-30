package com.zstu.bysj.cmgs.service.impl;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;
import com.zstu.bysj.cmgs.model.entity.AutoModel;
import com.zstu.bysj.cmgs.model.entity.AutoSeries;
import com.zstu.bysj.cmgs.service.AutoBrandService;
import com.zstu.bysj.cmgs.service.AutoHomeCrawlService;
import com.zstu.bysj.cmgs.service.AutoModelService;
import com.zstu.bysj.cmgs.service.AutoSeriesService;
import com.zstu.bysj.cmgs.service.crawl.*;
import com.zstu.bysj.cmgs.util.AutoConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.util.List;

/**
 * 爬虫获取车系车型数据
 * 
 * <pre>
 * 〈一句话是什么〉
 * 〈详细描述做什么〉
 * 
 * @author Irving
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class AutoHomeCrawlServiceImpl implements AutoHomeCrawlService {

	private static Logger logger = LoggerFactory.getLogger(AutoHomeCrawlServiceImpl.class);

	@Resource
	private AutoBrandService autoBrandService;
	@Resource
	private AutoSeriesService autoSeriesService;
	@Resource
	private AutoModelService autoModelService;
	@Resource
	private AutoSeriesPageProcessor autoSeriesPageProcessor;
	@Resource
	private AutoSeriesPipeline autoSeriesPipeline;
	@Resource
	private AutoModelPageProcessor autoModelPageProcessor;
	@Resource
	private AutoModelPipeline autoModelPineline;
	@Resource
	private AutoModelExtDataPageProcessor autoModelExtDataPageProcessor;
	@Resource
	private AutoModelExtDataPipeline autoModelExtDataPipeline;
	@Resource
	private AutoModelExtDataPageProcessor2 autoModelExtDataPageProcessor2;
	@Resource
	private AutoModelExtDataPipeline2 autoModelExtDataPipeline2;

	@Override
	public void crawlAll() {
		List<AutoBrand> brandList = autoBrandService.findList();
		for (AutoBrand brand : brandList) {
			Integer brandId = brand.getId();
			Integer brandAutoHomeId = brand.getAutoHomeId();
			if (null == brandAutoHomeId || 0 == brandAutoHomeId) {
				continue;
			}
			this.crawlSeries(brandId, brandAutoHomeId);

			List<AutoSeries> serieslList = autoSeriesService.findList(brandId);
			for (AutoSeries series : serieslList) {
				this.crawlModel(series.getId(), series.getAutoHomeId());
			}
		}
	}

	@Override
	public void crawlSeriesAndModel(Integer brandId, Integer brandAutoHomeId) {
		if (null == brandId || 0 == brandId) {
			return;
		}
		this.crawlSeries(brandId, brandAutoHomeId);

		List<AutoSeries> serieslList = autoSeriesService.findList(brandId);
		for (AutoSeries series : serieslList) {
			this.crawlModel(series.getId(),  series.getAutoHomeId());
		}
	}

	@Override
	public void crawlSeries(Integer brandId, Integer brandAutoHomeId) {
		if (null == brandAutoHomeId || 0 == brandAutoHomeId) {
			return;
		}
		Spider spider = Spider.create(autoSeriesPageProcessor).addPipeline(autoSeriesPipeline)
				.addUrl(AutoConstants.BRAND_INTERNAL_URL.replaceAll("\\{ID\\}", String.valueOf(brandAutoHomeId)))
				.thread(5);
		spider.run();
	}

	@Override
	public void crawlModel(Integer seriesId, Integer seriesAutoHomeId) {
		if (null == seriesId || 0 == seriesId || null == seriesAutoHomeId || 0 == seriesAutoHomeId) {
			return;
		}
		Spider spider = Spider.create(autoModelPageProcessor).addPipeline(autoModelPineline)
		                      .addUrl(AutoConstants.SERIES_URL.replaceAll("\\{ID\\}", String.valueOf(seriesAutoHomeId)))
		                      .thread(5);
		spider.run();

		if (Spider.Status.Stopped == spider.getStatus()) {
			List<AutoModel> list = autoModelService.findList(seriesId);
			int total = list.size();
			for (int i = 0; i < total; i++) {
				AutoModel autoModel = list.get(i);
				logger.info("正在抓取车型补充数据：{}[{}] - {}/{}", autoModel.getName(), autoModel.getAutoHomeId(), i + 1, total);
				String url = String.format(AutoConstants.MODEL_CONFIG_URL, autoModel.getAutoHomeId());
				Spider.create(autoModelExtDataPageProcessor).addPipeline(autoModelExtDataPipeline)
				      .addUrl(url).thread(5).run();

				logger.info("正在抓取车型轮胎规格,整备质量和大小车型：{}[{}] - {}/{}", autoModel.getName(), autoModel.getAutoHomeId(), i + 1, total);
				String detailUrl = String.format(AutoConstants.MODEL_CONFIG_DETAIL_URL, autoModel.getAutoHomeId());
				Spider.create(autoModelExtDataPageProcessor2).addPipeline(autoModelExtDataPipeline2)
				      .addUrl(detailUrl).thread(5).run();
			}
		}
	}

}
