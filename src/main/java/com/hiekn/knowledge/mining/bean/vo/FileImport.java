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

    @ApiParam(value = "filename")
    @FormDataParam("filename")
    FormDataContentDisposition fileInfo;

    @ApiParam(value = "filename")
    @FormDataParam("filename")
    FormDataBodyPart formDataBodyPart;

    @ApiParam(value = "filename")
    @FormDataParam("filename")
    InputStream fileIn;
}
