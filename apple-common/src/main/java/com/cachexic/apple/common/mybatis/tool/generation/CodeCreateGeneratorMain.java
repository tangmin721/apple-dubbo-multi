package com.cachexic.apple.common.mybatis.tool.generation;

import com.cachexic.apple.common.core.entity.BaseEntity;
import com.cachexic.apple.common.mybatis.tool.generation.generator.CodeGenerator;
//import com.cachexic.apple.facade.product.entity.Product;

/**
 * 运行此方法生成代码
 * @author tangmin
 * @date 2016年2月26日
 */
public class CodeCreateGeneratorMain {

	/**
	 * 生成代码的main方法，运行此方法即可
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		CodeGenerator codeGen = new CodeGenerator();
		//配置需要生成的类
		codeGen.addClass(BaseEntity.class);
		//配置前缀包名,去掉entity
		codeGen.setPackagePath("com.cachexic.apple.facade.product");
		//配置requestMap,最前面不要加“/”
		codeGen.setRequestMapPath("product/productBase");
		//配置对应的数据库表名
		codeGen.setTableName("t_product_base");
		//配置模块名称  
		codeGen.setModelName("商品");
		//代码生成存放位置  
		codeGen.outPut("d:\\Code\\product");
		System.out.println("代码生成成功");
	}

}
