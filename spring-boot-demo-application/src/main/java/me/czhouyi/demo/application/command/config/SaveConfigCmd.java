package me.czhouyi.demo.application.command.config;

import lombok.Data;

/**
 * SaveConfigCmd
 *
 * @author czhouyi@gmail.com
 */
@Data
public class SaveConfigCmd {

    private String configKey;
    private String configVal;

}
