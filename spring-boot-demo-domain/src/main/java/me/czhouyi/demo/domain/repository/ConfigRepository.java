package me.czhouyi.demo.domain.repository;

import me.czhouyi.demo.domain.entity.ConfigEntity;

import java.util.List;

public interface ConfigRepository {

    int getInt(String key, int defaultVal);

    String getString(String key, String defaultVal);

    boolean getBoolean(String key, boolean defaultVal);

    void save(String key, String value);

    List<ConfigEntity> getAll();

    List<ConfigEntity> getByKeys(List<String> keys);

}
