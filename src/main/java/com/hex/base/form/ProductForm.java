package com.hex.base.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * User: hexuan
 * Date: 2018/7/13
 * Time: 下午4:44
 */
@Data
public class ProductForm {

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片文件
     */
    private MultipartFile iconFile;
}
