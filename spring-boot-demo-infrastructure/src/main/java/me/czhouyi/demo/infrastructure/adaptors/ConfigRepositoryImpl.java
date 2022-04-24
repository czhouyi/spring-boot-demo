package me.czhouyi.demo.infrastructure.adaptors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import me.czhouyi.demo.domain.entity.ConfigEntity;
import me.czhouyi.demo.domain.repository.ConfigRepository;
import me.czhouyi.demo.domain.utils.BeanMapper;
import me.czhouyi.demo.infrastructure.db.ConfigMapper;
import me.czhouyi.demo.infrastructure.db.dataobject.ConfigDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class ConfigRepositoryImpl implements ConfigRepository {

    @Resource
    private ConfigMapper configMapper;


    @Override
    public int getInt(String key, int defaultVal) {
        QueryWrapper<ConfigDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("config_key", key);
        ConfigDO configDO = configMapper.selectOne(queryWrapper);
        String val = configDO == null ? String.valueOf(defaultVal) : configDO.getConfigVal();
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return defaultVal;
    }

    @Override
    public String getString(String key, String defaultVal) {
        ConfigDO configDO = getConfig(key);
        return configDO == null ? defaultVal : configDO.getConfigVal();
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        ConfigDO configDO = getConfig(key);
        return configDO == null ? defaultVal : Boolean.parseBoolean(configDO.getConfigVal());
    }

    @Override
    public void save(String key, String value) {
        QueryWrapper<ConfigDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("config_key", key);
        ConfigDO configDO = configMapper.selectOne(queryWrapper);
        if (configDO == null) {
            return;
        }
        configDO.setConfigVal(value);
        configMapper.updateById(configDO);
    }

    @Override
    public List<ConfigEntity> getAll() {
        QueryWrapper<ConfigDO> queryWrapper = new QueryWrapper<>();
        List<ConfigDO> configDOList = configMapper.selectList(queryWrapper);
        return BeanMapper.mapList(configDOList, ConfigDO.class, ConfigEntity.class);
    }

    @Override
    public List<ConfigEntity> getByKeys(List<String> keys) {
        QueryWrapper<ConfigDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("config_key", keys);
        List<ConfigDO> configDOList = configMapper.selectList(queryWrapper);
        return BeanMapper.mapList(configDOList, ConfigDO.class, ConfigEntity.class);
    }

    private ConfigDO getConfig(String key) {
        QueryWrapper<ConfigDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("config_key", key);
        return configMapper.selectOne(queryWrapper);
    }

}
