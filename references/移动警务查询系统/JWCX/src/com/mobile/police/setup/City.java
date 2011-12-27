package com.mobile.police.setup;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-21
 * Time: 上午11:54
 * To change this template use File | Settings | File Templates.
 */

public class City {

    private Integer id;
    private String name;
    private Province province;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
