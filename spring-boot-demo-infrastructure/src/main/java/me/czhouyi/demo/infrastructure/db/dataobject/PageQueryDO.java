package me.czhouyi.demo.infrastructure.db.dataobject;

import me.czhouyi.demo.domain.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页及排序参数
 *
 * @author czhouyi@gmail.com
 */
@Data
public class PageQueryDO implements Serializable {

    private Integer pageNo = 1;             // 页码
    private Integer pageSize = 10;          // 页面数量
    private String sortField;               // 排序字段
    private String sortType = "ASC";        // 排序类型

    public String getSortType() {
        this.sortType = StringUtils.trim(StringUtils.sBlank(this.sortType));
        if (!StringUtils.equalsIgnoreCase("asc", this.sortType) && !StringUtils.equalsIgnoreCase("desc", this.sortType)) {
            return "ASC";
        }
        return this.sortType;
    }

    public String getSortField() {
        this.sortField = StringUtils.trim(StringUtils.sBlank(this.sortField));
        if (this.sortField.contains("(") || this.sortField.contains(")") || this.sortField.contains("=")
                || this.sortField.contains("'") || this.sortField.contains(" ")) {
            return "";
        }
        return this.sortField;
    }

    public Integer getStart() {
        return (pageNo - 1) * pageSize;
    }
}