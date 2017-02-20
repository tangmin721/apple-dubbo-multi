package com.cachexic.apple.common.core.entity;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页对象
 * @author: tangmin
 * @date: 2017-02-13 17:09:42
 * @version: 1.0
 */
public class Pagination<T extends BaseEntity> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 实体的list
	 */
	private List<T> list = Lists.newArrayList();

	/**
	 * 总记录条数
	 */
	private Long total;

	/**
	 * 总页数
	 */
	private Long totalPage=0l;

	/**
	 * 每页记录条数
	 */
	private Long pageSize;

	/**
	 * 当前页
	 */
	private Long pageCurrent;

	/**
	 * 起始行,用于页面序号相加
	 */
	private Long pageStart=0l;

	/**
	 * dubbo 序列化机制使用的是Hessian，需要增加无参构造
	 */
	public Pagination(){

	}

	public Pagination(Long pageCurrent, Long pageSize, Long total) {
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.total = total;

		if (total > 0) {
			this.totalPage = (total + pageSize - 1) / pageSize;
			this.pageStart = pageSize * (pageCurrent - 1);
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Long pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public Long getPageStart() {
		return pageStart;
	}

	public void setPageStart(Long pageStart) {
		this.pageStart = pageStart;
	}

}
