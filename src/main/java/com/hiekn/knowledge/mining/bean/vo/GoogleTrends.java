package com.hiekn.knowledge.mining.bean.vo;

import com.hiekn.knowledge.mining.bean.dao.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ApiModel(value = "Google趋势")
public class GoogleTrends extends Base {

    @ApiModelProperty(value = "热度随时间变化的趋势")
    private String timeSeries;

    @ApiModelProperty(value = "搜索热度（按子区域）")
    private String geoMap;

    @ApiModelProperty(value = "相关主题")
    private String relatedTopics;

    @ApiModelProperty(value = "相关查询")
    private String relatedQueries;
}
