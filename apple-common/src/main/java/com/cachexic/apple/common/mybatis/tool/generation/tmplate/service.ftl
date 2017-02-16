package ${CONFIG.packagePath}.service;

import com.cachexic.apple.common.core.service.BaseService;
import ${entity.fullClassName};
import ${entity.fullClassName}Query;

/**
 * ${CONFIG.modelName}管理
 * @author tangmin
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${entity.className}Service extends BaseService<${entity.className}, ${entity.className}Query>{
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
	Boolean isNameExit(${entity.className} entity);
	
	/**
	 * 保存或添加
	 * @return
	 */
	Long saveOrUpdate(${entity.className} entity);

}
