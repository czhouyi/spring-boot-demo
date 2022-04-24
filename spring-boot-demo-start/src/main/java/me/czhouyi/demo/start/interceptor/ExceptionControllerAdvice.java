package me.czhouyi.demo.start.interceptor;

import me.czhouyi.demo.application.basic.dto.ResultData;
import me.czhouyi.demo.domain.utils.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Exception ControllerAdvice
 *
 * @author czhouyi@gmail.com
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultData<Void> bindExceptionHandler(BindException e) {
        String errorMsg = e.getAllErrors().stream().map(objectError -> {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                return errorMessage(fieldError);
            }
            return objectError.getDefaultMessage();
        }).reduce((a, b) -> a + "; " + b).orElse("");
        return ResultData.fail(errorMsg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultData<Void> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getAllErrors().stream().map(objectError -> {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                return errorMessage(fieldError);
            }
            return objectError.getDefaultMessage();
        }).reduce((a, b) -> a + "; " + b).orElse("");
        return ResultData.fail(errorMsg);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultData<Void> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return ResultData.fail("表单数据填写有误：" + e.getMessage());
    }

    private String errorMessage(FieldError fieldError) {
        String message = fieldError.getDefaultMessage();
        if (StringUtils.equals(message, "不能为空")) {
            message = fieldError.getField() + message;
        }
        return message;
    }
}