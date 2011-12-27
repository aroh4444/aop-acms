package cn.edu.ahnu.mps.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.rest.HttpHeaders;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-9
 * Time: 上午11:55
 * To change this template use File | Settings | File Templates.
 */

/**
 * 所有的Controller类的父类
 * @param <T>
 */
public abstract class BaseController<T> implements ModelDriven<Object>,Serializable{

    /**
     * 什么时候需要返回HttpHeaders？
     * 当返回的给用户的响应需要 在http头部自定义一些内容 ，需要返回HttpHeaders的实现类
     * @return
     */
    public abstract HttpHeaders index();

    public HttpHeaders create(){

        return null;
    }

    public abstract HttpHeaders show();

    public String update(){

        return null;
    }

    public String destroy(){

        return null;
    }

    public String edit(){

        return null;
    }

    public String editNew(){

        return null;
    }

    public Object getModel(){

        return null;
    }

}
