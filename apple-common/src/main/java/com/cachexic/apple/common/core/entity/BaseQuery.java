package com.cachexic.apple.common.core.entity;

import com.cachexic.apple.common.constant.AppConstant;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;


/**
 * @Description: 基础查询封装工具类
 * @author: tangmin
 * @date: 2017.02.13 15:38
 * @version: 1.0
 */
public class BaseQuery implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Long pageSize = Long.parseLong(AppConstant.PAGE_SIZE);

	/**
	 * 起始行
	 */
	protected Long startRow;

	/**
	 * 当前页
	 */
	protected Long pageCurrent = 1l;

	/**
	 * Sql查询字段,可自定义只取哪几列的信息
	 */
	protected String fields;

	/**
	 * 删除标记
	 */
	protected Integer deleted=0;

	/**
	 * 排序字段：默认按id，如果默认按其他，set一下
	 */
	protected String orderField;

	/**
	 * asc or desc  默认正序
	 */
	protected String orderDirection="asc";
	
	
	/** 排序列表字段 **/
	private List<OrderField> orderFields = Lists.newArrayList();

	public BaseQuery() {
		this.startRow = (this.pageCurrent - 1) * this.pageSize;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
		this.startRow = (this.pageCurrent - 1) * this.pageSize;
	}

	public Long getStartRow() {
		return startRow;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public Long getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Long pageCurrent) {
		this.pageCurrent = pageCurrent;
		this.startRow = (this.pageCurrent - 1) * this.pageSize;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
		if(StringUtils.isNotBlank(orderField)){
			this.orderFields.clear();
			this.orderFields.add(new OrderField(this.orderField, this.orderDirection));
		}
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
		if(StringUtils.isNotBlank(orderDirection)){
			this.orderFields.clear();
			this.orderFields.add(new OrderField(this.orderField, this.orderDirection));
		}
	}

	public List<OrderField> getOrderFields() {
		return orderFields;
	}

	public void setOrderFields(List<OrderField> orderFields) {
		this.orderFields = orderFields;
	}

}