package cn.edu.ahnu.mps.actions;

import cn.edu.ahnu.mps.domain.Location;
import cn.edu.ahnu.mps.service.PoliceService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-23
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class LocationController extends BaseController<Location>{

    private int id;

    private Location location;

    private List<Location> list;

    @Autowired
    private PoliceService policeService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Object getModel(){

        return list != null ? list : location;
    }

    public HttpHeaders index(){
        list = policeService.getLocations();
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public HttpHeaders show() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //public String create
}
