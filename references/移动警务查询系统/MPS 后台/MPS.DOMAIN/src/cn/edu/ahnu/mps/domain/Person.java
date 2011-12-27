package cn.edu.ahnu.mps.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-9
 * Time: 上午11:32
 * To change this template use File | Settings | File Templates.
 */

/**
 * 人口信息实体
 */
@Entity
//@NamedQueries({@NamedQuery(name = "person.selectAll",query = "from Person")})
public class Person implements Serializable{

    @Id
    private Integer id;

    private String idCard;
    private String name;
    private String sex;
    private String race;
    private Integer age;
    private String birthTime;
    private String country;
    private String province;
    private String city;
    private String detailAddr;
    private String work;

    private String remark;



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
