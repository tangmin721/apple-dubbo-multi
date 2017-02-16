package com.cachexic.apple.common.mybatis.tool.generation.tmplate;

/**
 * mysql建表模板
 * @author tangmin
 * @date 2016年2月26日
 */
public class MysqlDDLGenerator extends TemplateCodeGenerator {

	
	@Override
	public String getTemplateFile() {
		/**
		 * 模板文件
		 */
		return "mysql_ddl.ftl";
	}
	
}
