package ${CONFIG.packagePath}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cachexic.apple.common.core.dao.BaseDao;
import com.cachexic.apple.common.core.service.impl.BaseServiceImpl;
import com.cachexic.apple.common.exception.ValidateOtherException;
import ${CONFIG.packagePath}.dao.${entity.className}Dao;
import ${entity.fullClassName};
import ${entity.fullClassName}Query;
import ${CONFIG.packagePath}.service.${entity.className}Service;

/**
 * ${CONFIG.modelName}管理
 * @author tangmin
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service("${entity.firstLowName}Service")
public class ${entity.className}ServiceImpl extends BaseServiceImpl<${entity.className}, ${entity.className}Query> implements ${entity.className}Service{
	@Autowired
	private ${entity.className}Dao dao;

	@Override
	protected BaseDao<${entity.className}, ${entity.className}Query> dao() {
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
	public Boolean isNameExit(${entity.className} entity) {
		Long count = this.dao.selectCheckNameExit(entity.getName(), entity.getId());
		if(count>0){
			return true;
		}
		return false;
	}

	/**
	 * 重写insert方法，判断是否可以插入
	 * @param entity
	 * @return
	 */
	@Override
	public Long insert(${entity.className} entity) {
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
	public Long update(${entity.className} entity) {
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
	public Long saveOrUpdate(${entity.className} entity) {
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