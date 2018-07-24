package com.hex.base.controller;

import com.hex.base.converter.Product2ProductVOConverter;
import com.hex.base.domain.Product;
import com.hex.base.dto.ProductCondition;
import com.hex.base.enums.DefaultImgNameEnum;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.form.ProductForm;
import com.hex.base.service.ProductService;
import com.hex.base.util.FileUtil;
import com.hex.base.util.HexUtil;
import com.hex.base.util.ResultUtil;
import com.hex.base.vo.ProductVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * User: hexuan
 * Date: 2018/7/13
 * Time: 下午4:41
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${web.upload-path}")
    private String path;

    @Value("${web.zip-file-limit}")
    private Long zipFileLimit;

    @PostMapping("/saveProduct")
    public Object saveProduct(@Valid ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Product product = new Product();
        if (null != productForm.getId()) {
            product = productService.findProductById(productForm.getId());
            if (null == product) {
                return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "商品id " + ResultEnum.ERROR_PARAM.getMsg());
            }
        }
        BeanUtils.copyProperties(productForm, product);

        if (null != productForm.getIconFile()) {
            try {
                product.setIcon(FileUtil.uploadImgFileNew(productForm.getIconFile(), product.getIcon(), DefaultImgNameEnum.PRODUCT_ICON.getImgName(), path, zipFileLimit));
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error(ResultEnum.UPLOAD_FAIL.getCode(), ResultEnum.UPLOAD_FAIL.getMsg());
            }
        }

        return ResultUtil.success(productService.saveProduct(product).getId());
    }

    @PostMapping("/findProductListByCondition")
    public Object findProductListByCondition(ProductCondition productCondition,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "50") Integer size,
                                             @RequestParam(defaultValue = "createTime") String sortStr,
                                             @RequestParam(defaultValue = "desc") String asc) {
        Pageable pageable = HexUtil.getPageRequest(page, size, sortStr, asc);
        Page<Product> productPage = productService.findProductListByCondition(productCondition, pageable);
        List<ProductVO> productVOList = Product2ProductVOConverter.converter(productPage.getContent());
        return ResultUtil.success(new PageImpl<>(productVOList, pageable, productPage.getTotalElements()));
    }

    @PostMapping("/deleteProduct")
    public Object deleteProduct(Integer productId) {
        Product product = productService.findProductById(productId);
        if (null != product) {
            if (StringUtils.isNotBlank(product.getIcon()) && !product.getIcon().equals(DefaultImgNameEnum.PRODUCT_ICON.getImgName())) {
                FileUtil.deleteFile(path, product.getIcon());
            }
            productService.deleteProductById(productId);
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), "商品id " + ResultEnum.ERROR_PARAM.getMsg());
        }
    }

    @PostMapping("/updateProductState")
    public Object updateProductState(Integer productId) {
        Product product = productService.updateProductStateById(productId);
        if (null != product) {
            return ResultUtil.success(Product2ProductVOConverter.converter(product));
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
    }

}
