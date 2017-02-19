package com.cachexic.apple.common.core.entity;

import java.io.Serializable;

/**
 * @description: 排序字段公共类
 * @author: tangmin
 * @date: 2017-02-13 17:09:32
 * @version: 1.0
 */
public class OrderField implements Serializable {

	private String fieldName;
	private String order;

	public OrderField(String fieldName, String order) {
		super();
		this.fieldName = fieldName.toLowerCase();
		this.order = order;
	}

	public String getFieldName() {
		return fieldName;
	}
	public OrderField setFieldName(String fieldName) {
		this.fieldName = fieldName;
		return this;
	}
	public String getOrder() {
		return order;
	}
	public OrderField setOrder(String order) {
		this.order = order;
		return this;
	}
}