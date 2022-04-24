package me.czhouyi.demo.infrastructure.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * Config
 *
 * @author czhouyi@gmail.com
 */
@TableName("t_fr_config")
@Data
public class ConfigDO {

    @TableId(value = "id", type = AUTO)
    private Long id;
    private String configKey;
    private String configVal;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private Boolean deleted;

}
