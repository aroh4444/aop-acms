package com.mobile.police.gps;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-23
 * Time: 上午10:39
 * To change this template use File | Settings | File Templates.
 */
public class Location {
	

    private Integer id;

    private Double latitude;

    private Double longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }	
}
