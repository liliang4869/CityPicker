package com.liliang4869.citypicker.entity;



import com.liliang4869.citypicker.CommonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "street",createInDb = false)
public class Street implements CommonUtil.ObjectNameCallback {
    @Id
    @Property(nameInDb = "code")
    private Long code;
    @Property(nameInDb = "name")
    private String name;
    @Property(nameInDb = "areaCode")
    private Long districtCode;
    @Property(nameInDb = "provinceCode")
    private Long provinceCode;
    @Property(nameInDb = "cityCode")
    private Long cityCode;

    @Generated(hash = 946912322)
    public Street(Long code, String name, Long districtCode, Long provinceCode,
            Long cityCode) {
        this.code = code;
        this.name = name;
        this.districtCode = districtCode;
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
    }
    @Generated(hash = 1146976529)
    public Street() {
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
    public Long getDistrictCode() {
        return this.districtCode;
    }
    public void setDistrictCode(Long districtCode) {
        this.districtCode = districtCode;
    }
    public Long getProvinceCode() {
        return this.provinceCode;
    }
    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }
    public Long getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "Street{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", districtCode=" + districtCode +
                ", provinceCode=" + provinceCode +
                ", cityCode=" + cityCode +
                '}';
    }

    @Override
    public String getObjectName() {
        return name;
    }
}
