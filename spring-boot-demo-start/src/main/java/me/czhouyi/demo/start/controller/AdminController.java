package me.czhouyi.demo.start.controller;

import me.czhouyi.demo.application.basic.dto.ResultData;
import me.czhouyi.demo.application.co.config.ConfigCO;
import me.czhouyi.demo.application.command.config.SaveConfigCmd;
import me.czhouyi.demo.application.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * AdminController
 *
 * @author czhouyi@gmail.com
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Resource
    private ConfigService configService;


    @GetMapping("/getConfigList")
    public ResultData<List<ConfigCO>> getConfigList() {
        return ResultData.success(configService.configList());
    }

    @GetMapping("/getConfigMap")
    public ResultData<Map<String, String>> getConfigMap() {
        return ResultData.success(configService.configMap());
    }

    @PostMapping("/saveConfig")
    public ResultData<Void> saveConfig(@RequestBody @Valid SaveConfigCmd cmd) {
        configService.saveConfig(cmd);
        return ResultData.success();
    }

    @PostMapping("/batchSaveConfig")
    public ResultData<Void> batchSaveConfig(@RequestBody @Valid List<SaveConfigCmd> configList) {
        configService.batchSaveConfig(configList);
        return ResultData.success();
    }

}
