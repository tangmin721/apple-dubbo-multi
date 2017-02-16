package com.cachexic.apple.service.product.dao;

import com.cachexic.apple.common.core.dao.BaseDao;
import com.cachexic.apple.common.mybatis.annotation.MybatisDao;
import com.cachexic.apple.facade.product.entity.Product;
import com.cachexic.apple.facade.product.entity.ProductQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 商品管理
 * @author tangmin
 * @date 2017-02-15 11:50:23
 */
@MybatisDao
public interface ProductDao extends BaseDao<Product, ProductQuery> {
	
	/**
	 * 获取seq
	 * @return Integer
	 */
	Integer selectMaxSeq();
	
	/**
	 * 查找name为@name，且id不为@id的记录条数
	 * @param name
	 * @param id
	 * @return
	 */
	Long selectCheckNameExit(@Param("name") String name, @Param("id") Long id);
	
}
