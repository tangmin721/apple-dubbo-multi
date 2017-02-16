package com.cachexic.apple.facade.product.facade;

import com.cachexic.apple.common.core.facade.BaseFacade;
import com.cachexic.apple.facade.product.entity.Product;
import com.cachexic.apple.facade.product.entity.ProductQuery;

/**
 * @Description:
 * @author: tangmin
 * @date: 2017年02月15日 17:12
 * @version: 1.0
 */
public interface ProductFacade extends BaseFacade<Product, ProductQuery>{

    /**
     * 获取seq
     * @return Integer
     */
    Integer selectMaxSeq();

    /**
     * 校验是否存在
     * @param
     * @return
     */
    Boolean isNameExit(Product entity);

    /**
     * 保存或添加
     * @return
     */
    Long saveOrUpdate(Product entity);
}
