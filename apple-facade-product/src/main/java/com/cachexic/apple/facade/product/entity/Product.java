package com.cachexic.apple.facade.product.entity;

import com.cachexic.apple.common.core.entity.BaseEntity;
import com.cachexic.apple.common.validate.Insert;
import com.cachexic.apple.common.validate.Update;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description:商品
 * @author: tangmin
 * @date: 2017年02月14日 17:25
 * @version: 1.0
 */
public class Product extends BaseEntity {

    /**
     * 商品名称
     */
    @NotBlank(message="商品名称不能为空",groups={Insert.class,Update.class})
    @Length(max=50,message="商品名称长度不能超过50",groups={Insert.class,Update.class})
    private String name;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 简要描述
     */
    @NotBlank(message="简要描述不能为空",groups={Insert.class,Update.class})
    @Length(max=50,message="简要描述长度不能超过50",groups={Insert.class,Update.class})
    private String memo;


    /**
     * 商品创建人用户id
     */
    private Long uid;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
