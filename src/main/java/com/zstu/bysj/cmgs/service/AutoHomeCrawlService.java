package com.zstu.bysj.cmgs.service;

/**
 * 爬虫
 * 
 * @author irving
 *
 */
public interface AutoHomeCrawlService {

	/**
	 * 爬取所有数据
	 * 
	 * <pre>
	 * 功能描述:
	 * 〈功能详细描述〉
	 * 
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	void crawlAll();

	/**
	 * 按品牌爬取车系和车型数据
	 *
	 * @param brandId
	 * @param brandAutoHomeId
	 */
	void crawlSeriesAndModel(Integer brandId, Integer brandAutoHomeId);

	/**
	 * 按品牌爬取车系数据
	 *
	 * @param brandId
	 * @param brandAutoHomeId
	 */
	void crawlSeries(Integer brandId, Integer brandAutoHomeId);

	/**
	 * 按车系爬取车型数据
	 *
	 * @param seriesId
	 * @param seriesAutoHomeId
	 */
	void crawlModel(Integer seriesId, Integer seriesAutoHomeId);

}
