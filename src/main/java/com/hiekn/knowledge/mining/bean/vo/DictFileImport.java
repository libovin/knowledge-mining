package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Data
@ApiModel
public class DictFileImport extends FileImport {

    @ApiModelProperty(example = "会议名称字典", value = "表类型")
    @FormDataParam("name")
    private String name;
}
