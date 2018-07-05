package com.hex.base.handle;

import com.hex.base.domain.Result;
import com.hex.base.enums.ResultEnum;
import com.hex.base.exception.MyException;
import com.hex.base.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: hexuan
 * Date: 2017/8/14
 * Time: 下午3:04
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {
//    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        log.error("【系统异常】{}", e);
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ResultUtil.error(myException.getCode(), myException.getMessage());
        } else {
            //logger.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UN_KNOW_ERRO.getCode(), ResultEnum.UN_KNOW_ERRO.getMsg());
        }

    }
}
