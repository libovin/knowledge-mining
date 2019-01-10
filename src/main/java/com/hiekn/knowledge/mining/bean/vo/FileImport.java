package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

@Data
@ApiModel
public class FileImport {

    @ApiParam(value = "file")
    @FormDataParam("file")
    FormDataContentDisposition fileInfo;

    @ApiParam(value = "file")
    @FormDataParam("file")
    FormDataBodyPart formDataBodyPart;

    @ApiParam(value = "file")
    @FormDataParam("file")
    InputStream fileIn;
}
