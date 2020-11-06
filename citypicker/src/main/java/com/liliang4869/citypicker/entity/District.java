package com.liliang4869.citypicker.entity;


import com.liliang4869.citypicker.CommonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;


@Entity(nameInDb = "district",createInDb = false)
public class District implements CommonUtil.ObjectNameCallback {
    @Id
    @Property(nameInDb = "code")
    private Long code;
    @Property(nameInDb = "name")
    private String name;
    @Property(nameInDb = "cityCode")
    private Long cityCode;
    @Property(nameInDb = "provinceCode")
    private Long provinceCode;

    @Generated(hash = 608821646)
    public District(Long code, String name, Long cityCode, Long provinceCode) {
        this.code = code;
        this.name = name;
        this.cityCode = cityCode;
        this.provinceCode = provinceCode;
    }
    @Generated(hash = 1876777828)
    public District() {
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
    public Long getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }
    public Long getProvinceCode() {
        return this.provinceCode;
    }
    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return "District{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", cityCode=" + cityCode +
                ", provinceCode=" + provinceCode +
                '}';
    }

    @Override
    public String getObjectName() {
        return name;
    }
}
