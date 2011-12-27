package cn.edu.ahnu.mps.actions;

import cn.edu.ahnu.mps.domain.City;
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
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
@Results({@Result(name = "success",type = "redirectAction",location = "city")})
public class CityController extends BaseController<City> {

    private City city;

    private int id;

    private List<City> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getModel(){

        return list != null ? list : city;
    }

    @Autowired
    private ProvinceService provinceService;

    @Override
    public HttpHeaders index() {
        list = provinceService.getCities();

        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public HttpHeaders show() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
