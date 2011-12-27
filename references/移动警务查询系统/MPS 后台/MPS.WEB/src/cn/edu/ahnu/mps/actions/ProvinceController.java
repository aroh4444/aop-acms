package cn.edu.ahnu.mps.actions;

import cn.edu.ahnu.mps.domain.Province;
import cn.edu.ahnu.mps.service.ProvinceService;
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
 * Time: 下午12:00
 * To change this template use File | Settings | File Templates.
 */
@Results({@Result(name = "success",type = "redirectAction",location = "province")})
public class ProvinceController extends BaseController<Province>{

    private Province province;

    private int id;

    private List<Province> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Object getModel(){

        return list != null ? list : province;
    }

    @Autowired
    private ProvinceService provinceService;

    @Override
    public HttpHeaders index() {

        list = provinceService.getProvinces();
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public HttpHeaders show() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
