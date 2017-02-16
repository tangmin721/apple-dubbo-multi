package com.cachexic.apple.service.product.service.impl;


import com.cachexic.apple.common.core.dao.BaseDao;
import com.cachexic.apple.common.core.service.impl.BaseServiceImpl;
import com.cachexic.apple.common.exception.ValidateOtherException;
import com.cachexic.apple.facade.product.entity.Product;
import com.cachexic.apple.facade.product.entity.ProductQuery;
import com.cachexic.apple.service.product.dao.ProductDao;
import com.cachexic.apple.service.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品管理
 * @author tangmin
 * @date 2017-02-15 11:50:23
 */
@Service("productService")
//@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductQuery> implements ProductService {
	@Autowired
	private ProductDao dao;

	@Override
	protected BaseDao<Product, ProductQuery> dao() {
		return this.dao;
	}
	
	/**
	 * 获取seq
	 */
	@Override
	public Integer selectMaxSeq() {
		Integer selectMaxSeq = this.dao.selectMaxSeq();
		if(selectMaxSeq!=null){
			return selectMaxSeq;
		}
		return 0;
	}

	/**
	 * 校验entity是否可修改（name是否存在）
	 */
	@Override
	public Boolean isNameExit(Product entity) {
		Long count = this.dao.selectCheckNameExit(entity.getName(), entity.getId());
		return count > 0;
	}

	/**
	 * 重写insert方法，判断是否可以插入
	 * @param entity
	 * @return
	 */
	@Override
	public Long insert(Product entity) {
		if(isNameExit(entity)){
			throw new ValidateOtherException(ValidateOtherException.INSERT_FAILD,"名称已经存在，新增失败");
		}
		return super.insert(entity);
	}

	/**
	 * 重写update方法
	 * @param entity
	 * @return
	 */
	@Override
	public Long update(Product entity) {
		if(isNameExit(entity)){
			throw new ValidateOtherException(ValidateOtherException.UPDATE_FAILD,"名称已经存在，修改失败");
		}
		super.update(entity);
		return entity.getId();
	}
	
	/**
	 * 新增or修改
	 */
	@Override
	@Transactional
	public Long saveOrUpdate(Product entity) {
		Long id = entity.getId();
		if(isNameExit(entity)){
			if(id ==null){
				throw new ValidateOtherException(ValidateOtherException.INSERT_FAILD,"名称已经存在，新增失败");
			}else {
				throw new ValidateOtherException(ValidateOtherException.UPDATE_FAILD,"名称已经存在，修改失败");
			}
		}

		if(id ==null){
			return this.insert(entity);
		}else {
			this.update(entity);
			return id;
		}
	}

}