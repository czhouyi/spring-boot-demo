package me.czhouyi.demo.infrastructure.db;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.czhouyi.demo.infrastructure.db.dataobject.ConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ConfigMapper
 *
 * @author czhouyi@gmail.com
 */
@Mapper
public interface ConfigMapper extends BaseMapper<ConfigDO> {

}
