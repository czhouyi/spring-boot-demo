package me.czhouyi.demo.domain.exception;

/**
 * BusinessException
 *
 * @author czhouyi@gmail.com
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
