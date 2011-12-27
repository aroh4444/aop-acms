package cn.edu.ahnu.mps.actions;

import cn.edu.ahnu.mps.domain.Work;
import cn.edu.ahnu.mps.service.WorkService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-21
 * Time: 上午10:31
 * To change this template use File | Settings | File Templates.
 */
@Results({@Result(name = "success",type = "redirectAction",location = "work")})
public class WorkController extends BaseController<Work> {

    private Work work;

    private int id;

    private List<Work> list;

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getModel(){

        return list != null ? list : work;
    }


    @Autowired
    private WorkService workService;

    @Override
    public HttpHeaders index() {

        list = workService.getWorks();
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public HttpHeaders show() {

        return null;
    }
}
