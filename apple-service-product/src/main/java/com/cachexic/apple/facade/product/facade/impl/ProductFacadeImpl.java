package com.cachexic.apple.facade.product.facade.impl;

import com.cachexic.apple.common.core.facade.impl.BaseFacadeImpl;
import com.cachexic.apple.common.core.service.BaseService;
import com.cachexic.apple.facade.product.entity.Product;
import com.cachexic.apple.facade.product.entity.ProductQuery;
import com.cachexic.apple.facade.product.facade.ProductFacade;
import com.cachexic.apple.service.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: tangmin
 * @date: 2017年02月16日 13:49
 * @version: 1.0
 */
@Component("productFacade")
public class ProductFacadeImpl extends BaseFacadeImpl<Product,ProductQuery> implements ProductFacade{

    @Autowired
    private ProductService productService;

    @Override
    protected BaseService<Product, ProductQuery> service() {
        return this.productService;
    }

    /**
     * 获取seq
     *
     * @return Integer
     */
    @Override
    public Integer selectMaxSeq() {
        return this.productService.selectMaxSeq();
    }

    /**
     * 校验是否存在
     *
     * @param entity@return
     */
    @Override
    public Boolean isNameExit(Product entity) {
        return this.productService.isNameExit(entity);
    }

    /**
     * 保存或添加
     *
     * @param entity
     * @return
     */
    @Override
    public Long saveOrUpdate(Product entity) {
        return productService.saveOrUpdate(entity);
    }
}
