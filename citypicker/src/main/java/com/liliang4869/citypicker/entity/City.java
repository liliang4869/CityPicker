package com.liliang4869.citypicker.entity;


import com.liliang4869.citypicker.CommonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "city",createInDb = false)
public class City implements CommonUtil.ObjectNameCallback {
    @Id
    @Property(nameInDb = "code")
    private Long code;
    @Property(nameInDb = "name")
    private String name;
    @Property(nameInDb = "provinceCode")
    private Long provinceCode;
    @Generated(hash = 254601930)
    public City(Long code, String name, Long provinceCode) {
        this.code = code;
        this.name = name;
        this.provinceCode = provinceCode;
    }
    @Generated(hash = 750791287)
    public City() {
    }
    public Long getCode() {
        return this.code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getProvinceCode() {
        return this.provinceCode;
    }
    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return "City{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", provinceCode=" + provinceCode +
                '}';
    }

    @Override
    public String getObjectName() {
        return name;
    }
}
