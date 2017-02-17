package com.cachexic.apple.service.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.cachexic.apple.common.junit.SpringJunitTest;
import com.cachexic.apple.common.mybatis.tool.generation.generator.CodeGenerator;
import com.cachexic.apple.facade.product.entity.Product;
import com.cachexic.apple.facade.product.entity.ProductQuery;
import com.cachexic.apple.service.product.dao.ProductDao;
import com.cachexic.apple.service.product.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @Description:
 * @author: tangmin
 * @date: 2017年02月15日 18:20
 * @version: 1.0
 */
public class ProductServiceImplTest extends SpringJunitTest{
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @Test
    public void isNameExit() throws Exception {
        Product entity = new Product();
        entity.setName("商品1");
        entity.setPrice(2d);
        entity.setMemo("简要描述");
        entity.setUid(1l);
        productService.isNameExit(entity);
    }

    @Test
    @Rollback(false)
    public void insert() throws Exception {
        Product entity = new Product();
        entity.setName("商品3");
        entity.setPrice(2d);
        entity.setMemo("简要描述");
        entity.setUid(1l);
        System.out.println("insertId:"+productService.insert(entity));

    }

    @Test
    @Rollback(false)
    public void insertDao() throws Exception {
        Product entity = new Product();
        entity.setName("商品1");
        entity.setPrice(2d);
        entity.setMemo("简要描述");
        entity.setUid(1l);
        System.out.println("insertId:"+productDao.insert(entity));

    }

    @Test
    @Rollback(false)
    public void update() throws Exception {
        Product entity = productService.selectById(2l);
        entity.setName("商品2");
        entity.setPrice(2d);
        entity.setMemo("简要描述");
        entity.setUid(1l);
        System.out.println("insertId:"+productService.update(entity));
    }

    @Test
    @Rollback(false)
    public void updateDao() throws Exception {
        Product entity = productService.selectById(2l);
        entity.setName("商品2");
        entity.setPrice(2d);
        entity.setMemo("简要描述");
        entity.setUid(1l);
        System.out.println("insertId:"+productDao.update(entity));
    }

    @Test
    public void saveOrUpdate() throws Exception {

    }

    @Test
    public void selectById() throws Exception {
        System.out.println("12:"+JSON.toJSONString(productService.selectById(2l)));
    }

    @Test
    public void selectListPagination() throws Exception {
        ProductQuery query = new ProductQuery();
        System.out.println("12:"+JSON.toJSONString(productService.selectListPagination(query)));
    }

    public static void main(String[] args) {
        CodeGenerator codeGen = new CodeGenerator();
        //配置需要生成的类
        codeGen.addClass(Product.class);
        //配置前缀包名,去掉entity
        codeGen.setPackagePath("com.cachexic.apple.facade.product");
        //配置requestMap,最前面不要加“/”
        codeGen.setRequestMapPath("product/productBase");
        //配置对应的数据库表名
        codeGen.setTableName("t_product_base");
        //配置模块名称
        codeGen.setModelName("商品");
        //代码生成存放位置
        try {
            codeGen.outPut("d:\\Code\\product");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("代码生成成功");
    }

}