package cn.edu.ahnu.mps.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-21
 * Time: 上午10:21
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Work {

    @Id
    private Integer id;

    private String name;

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
}
