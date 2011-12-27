package com.mobile.police.legalcase;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-10
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */

/**
 * 案件信息实体
 */

public class LegalCase implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4192579047303805166L;
	
	private Integer id;
	private String code;
	private String category;
	private String reportTime; // 报案时间
	private String source; // 案情来源
	private String findTime; // 发现时间
	private String eventTime; // 发案时间
	private String eventAddr;
	private String brief; // 简要案情
	private Integer crimeNumber; // 作案人数
	private Integer injuredNumber; // 受伤人数
	private Integer deathNumber; // 死亡人数
	private Double loss; // 损失价值
	private String trace; // 作案痕迹
	private String evidence; // 物证
	private String siteFeature;// 现场特征
	private String timing; // 选择时机
	private String site; // 选择处所
	private String target; // 选择对象
	private String method; // 作案手段
	private String characteristic;// 作案特点
	private String tool; // 作案工具
	private String solve; // 是否破案，0，未破；1，已破

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	
	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getFindTime() {
		return findTime;
	}

	public void setFindTime(String findTime) {
		this.findTime = findTime;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEventAddr() {
		return eventAddr;
	}

	public void setEventAddr(String eventAddr) {
		this.eventAddr = eventAddr;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Integer getCrimeNumber() {
		return crimeNumber;
	}

	public void setCrimeNumber(Integer crimeNumber) {
		this.crimeNumber = crimeNumber;
	}

	public Integer getInjuredNumber() {
		return injuredNumber;
	}

	public void setInjuredNumber(Integer injuredNumber) {
		this.injuredNumber = injuredNumber;
	}

	public Integer getDeathNumber() {
		return deathNumber;
	}

	public void setDeathNumber(Integer deathNumber) {
		this.deathNumber = deathNumber;
	}

	public Double getLoss() {
		return loss;
	}

	public void setLoss(Double loss) {
		this.loss = loss;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	public String getSiteFeature() {
		return siteFeature;
	}

	public void setSiteFeature(String siteFeature) {
		this.siteFeature = siteFeature;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getSolve() {
		return solve;
	}

	public void setSolve(String solve) {
		this.solve = solve;
	}
}
