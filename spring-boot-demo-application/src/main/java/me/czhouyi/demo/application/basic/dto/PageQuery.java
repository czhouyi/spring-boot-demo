package me.czhouyi.demo.application.basic.dto;

import me.czhouyi.demo.domain.utils.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import java.io.Serializable;

/**
 * 分页及排序参数
 *
 * @author czhouyi@gmail.com
 */
@Data
public class PageQuery implements Serializable {

    @Schema(title = "页码", example = "1")
    private Integer pageNo = 1;
    @Schema(title = "每页数量", example = "10")
    @Max(value = 1000, message = "每页数量不能大于1000")
    private Integer pageSize = 10;
    private String sortField;
    private String sortType = "ASC";

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

    @Schema(hidden = true)
    public Integer getStart() {
        return (pageNo - 1) * pageSize;
    }

}