package com.mobile.police.fugitive;

import com.mobile.police.legalcase.LegalCase;
import com.mobile.police.suspect.Suspect;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-18
 * Time: 上午8:33
 * To change this template use File | Settings | File Templates.
 */


/**
 * 逃犯和案件关联信息实体
 *
 */
public class Fugitive {

    private Integer id;

    private Suspect suspect;

    private LegalCase legalCase;

    private String category; //在逃类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Suspect getSuspect() {
        return suspect;
    }

    public void setSuspect(Suspect suspect) {
        this.suspect = suspect;
    }

    public LegalCase getLegalCase() {
        return legalCase;
    }

    public void setLegalCase(LegalCase legalCase) {
        this.legalCase = legalCase;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
