package cn.edu.ahnu.mps.exception;

import java.security.Provider;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-9
 * Time: 下午4:43
 * To change this template use File | Settings | File Templates.
 */
public class ServiceException extends RuntimeException{

    public ServiceException(){}

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceException(String message){
        super(message);
    }
}
