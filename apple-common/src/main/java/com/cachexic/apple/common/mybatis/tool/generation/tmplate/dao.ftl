package ${CONFIG.packagePath}.dao;

import org.apache.ibatis.annotations.Param;

import com.cachexic.apple.common.core.dao.BaseDao;
import com.cachexic.apple.common.mybatis.annotation.MybatisDao;
import ${entity.fullClassName};
import ${entity.fullClassName}Query;

/**
 * ${CONFIG.modelName}管理
 * @author tangmin
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@MybatisDao
public interface ${entity.className}Dao extends BaseDao<${entity.className}, ${entity.className}Query>{
	
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
	public Long selectCheckNameExit(@Param("name")String name,@Param("id")Long id);
	
}
