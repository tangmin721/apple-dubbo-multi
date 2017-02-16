package com.cachexic.apple.common.core.facade.impl;

import com.cachexic.apple.common.core.entity.BaseEntity;
import com.cachexic.apple.common.core.entity.BaseQuery;
import com.cachexic.apple.common.core.entity.Pagination;
import com.cachexic.apple.common.core.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description: baseFacade实现
 * @author: tangm
 * @date: 2016年2月18日
 * @version: 1.0
 */
public abstract class BaseFacadeImpl<T extends BaseEntity, Q extends BaseQuery> implements BaseService<T, Q> {

	protected static final Logger logger = LoggerFactory.getLogger(BaseFacadeImpl.class);

	protected abstract BaseService<T, Q> service();

	public Long insert(T entity) {
		return this.service().insert(entity);
	}

	public T selectById(Long id) {
		return this.service().selectById(id);
	}

	public List<T> selectByIds(List<Long> ids) {
		return this.service().selectByIds(ids);
	}

	public Long update(T entity) {
		return this.service().update(entity);
	}

	public Long deleteById(Long id) {
		return this.service().deleteById(id);
	}

	public Long deleteByIds(List<Long> ids) {
		return this.service().deleteByIds(ids);
	}

	public Long removeById(Long id) {
		return this.service().removeById(id);
	}

	public Long removeByIds(List<Long> ids) {
		return this.service().removeByIds(ids);
	}

	public List<T> selectList(Q query) {
		return this.service().selectList(query);
	}

	public List<T> selectListPage(Q query) {
		return this.service().selectListPage(query);
	}

	public Long selectListTotal(Q query) {
		return this.service().selectListTotal(query);
	}

	public Pagination<T> selectListPagination(Q query) {
		return this.service().selectListPagination(query);
	}

}
