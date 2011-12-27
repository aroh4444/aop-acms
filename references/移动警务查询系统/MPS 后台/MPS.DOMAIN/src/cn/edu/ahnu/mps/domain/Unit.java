package cn.edu.ahnu.mps.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-10
 * Time: 上午10:29
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 受害单位信息实体
 */

@Entity
public class Unit {

    @Id
    private Integer id;
    private String name;
    private String category;
    private String owner;
    private String telphone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "CASE_ID")
    private LegalCase legalCase;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LegalCase getLegalCase() {
        return legalCase;
    }

    public void setLegalCase(LegalCase legalCase) {
        this.legalCase = legalCase;
    }
}
