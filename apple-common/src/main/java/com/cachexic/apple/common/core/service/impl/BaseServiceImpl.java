package com.cachexic.apple.common.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.cachexic.apple.common.core.dao.BaseDao;
import com.cachexic.apple.common.core.entity.BaseEntity;
import com.cachexic.apple.common.core.entity.BaseQuery;
import com.cachexic.apple.common.core.entity.Pagination;
import com.cachexic.apple.common.core.service.BaseService;
import com.cachexic.apple.common.exception.BizException;
import com.cachexic.apple.common.exception.ValidateException;
import com.cachexic.apple.common.validate.BeanValidator;
import com.cachexic.apple.common.validate.Insert;
import com.cachexic.apple.common.validate.Update;
import com.cachexic.apple.common.validate.ValidatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: baseService实现
 * @author: tangm
 * @date: 2016年2月18日
 * @version: 1.0
 */
public abstract class BaseServiceImpl<T extends BaseEntity, Q extends BaseQuery> implements BaseService<T, Q> {

	protected static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

	protected abstract BaseDao<T, Q> dao();

	public Long insert(T entity) {
		if (entity == null) {
			throw new RuntimeException("insert entity:T is null");
		}

		//Validator校验
		ValidatorResult validateResult = BeanValidator.validateResult(entity, Insert.class);
		if (!validateResult.getFlag()) {
			throw new ValidateException(ValidateException.INSERT_FAILD, JSON.toJSONString(validateResult.getErrorObjs()));
		}

		Long result = this.dao().insert(entity);

		if (result <= 0) {
			throw BizException.DB_INSERT_RESULT_0;
		}

		if (entity != null && entity.getId() != null && result > 0) {
			return entity.getId();
		}

		return result;
	}

	public T selectById(Long id) {
		return this.dao().selectById(id);
	}

	public List<T> selectByIds(List<Long> ids) {
		return this.dao().selectByIds(ids);
	}

	public Long update(T entity) {
		if (entity == null) {
			throw new RuntimeException("update entity:T is null");
		}

		//校验
		ValidatorResult validateResult = BeanValidator.validateResult(entity, Update.class);
		if (!validateResult.getFlag()) {
			throw new ValidateException(ValidateException.UPDATE_FAILD, JSON.toJSONString(validateResult.getErrorObjs()));
		}

		Long result = this.dao().update(entity);

		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0;
		}

		return result;
	}

	public Long deleteById(Long id) {
		return this.dao().deleteById(id);
	}

	@Transactional
	public Long deleteByIds(List<Long> ids) {
		return this.dao().deleteByIds(ids);
	}

	public Long removeById(Long id) {
		return this.dao().removeById(id);
	}

	@Transactional
	public Long removeByIds(List<Long> ids) {
		return this.dao().removeByIds(ids);
	}

	public List<T> selectList(Q query) {
		return this.dao().selectList(query);
	}

	public List<T> selectListPage(Q query) {
		return this.dao().selectListPage(query);
	}

	public Long selectListTotal(Q query) {
		return this.dao().selectListTotal(query);
	}

	@Transactional
	public Pagination<T> selectListPagination(Q query) {
		Pagination<T> pagination = new Pagination<T>(query.getPageCurrent(), query.getPageSize(), this.dao().selectListTotal(query));
		pagination.setList(this.dao().selectListPage(query));
		return pagination;
	}

}
