package me.czhouyi.demo.application.service;

import me.czhouyi.demo.domain.entity.ConfigEntity;
import me.czhouyi.demo.domain.repository.ConfigRepository;
import me.czhouyi.demo.domain.utils.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import me.czhouyi.demo.application.co.config.ConfigCO;
import me.czhouyi.demo.application.command.config.SaveConfigCmd;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ConfigService
 *
 * @author czhouyi@gmail.com
 */
@Service
@Slf4j
public class ConfigService {

    @Resource
    private ConfigRepository configRepository;


    public void saveConfig(SaveConfigCmd cmd) {
        configRepository.save(cmd.getConfigKey(), cmd.getConfigVal());
    }

    public void batchSaveConfig(List<SaveConfigCmd> configList) {
        configList.forEach(this::saveConfig);
    }

    public List<ConfigCO> configList() {
        List<ConfigEntity> configList = configRepository.getAll();
        return BeanMapper.mapList(configList, ConfigEntity.class, ConfigCO.class);
    }

    public Map<String, String> configMap() {
        List<ConfigEntity> configList = configRepository.getAll();
        Map<String, String> configMap = new HashMap<>();
        configList.forEach(configDO -> configMap.put(configDO.getConfigKey(), configDO.getConfigVal()));
        return configMap;
    }

    public Integer getInteger(String key, Integer defaultVal) {
        return configRepository.getInt(key, defaultVal);
    }

}
