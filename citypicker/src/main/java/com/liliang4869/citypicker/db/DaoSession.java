package com.liliang4869.citypicker.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.liliang4869.citypicker.entity.City;
import com.liliang4869.citypicker.entity.District;
import com.liliang4869.citypicker.entity.Province;
import com.liliang4869.citypicker.entity.Street;

import com.liliang4869.citypicker.db.CityDao;
import com.liliang4869.citypicker.db.DistrictDao;
import com.liliang4869.citypicker.db.ProvinceDao;
import com.liliang4869.citypicker.db.StreetDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cityDaoConfig;
    private final DaoConfig districtDaoConfig;
    private final DaoConfig provinceDaoConfig;
    private final DaoConfig streetDaoConfig;

    private final CityDao cityDao;
    private final DistrictDao districtDao;
    private final ProvinceDao provinceDao;
    private final StreetDao streetDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cityDaoConfig = daoConfigMap.get(CityDao.class).clone();
        cityDaoConfig.initIdentityScope(type);

        districtDaoConfig = daoConfigMap.get(DistrictDao.class).clone();
        districtDaoConfig.initIdentityScope(type);

        provinceDaoConfig = daoConfigMap.get(ProvinceDao.class).clone();
        provinceDaoConfig.initIdentityScope(type);

        streetDaoConfig = daoConfigMap.get(StreetDao.class).clone();
        streetDaoConfig.initIdentityScope(type);

        cityDao = new CityDao(cityDaoConfig, this);
        districtDao = new DistrictDao(districtDaoConfig, this);
        provinceDao = new ProvinceDao(provinceDaoConfig, this);
        streetDao = new StreetDao(streetDaoConfig, this);

        registerDao(City.class, cityDao);
        registerDao(District.class, districtDao);
        registerDao(Province.class, provinceDao);
        registerDao(Street.class, streetDao);
    }
    
    public void clear() {
        cityDaoConfig.clearIdentityScope();
        districtDaoConfig.clearIdentityScope();
        provinceDaoConfig.clearIdentityScope();
        streetDaoConfig.clearIdentityScope();
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public DistrictDao getDistrictDao() {
        return districtDao;
    }

    public ProvinceDao getProvinceDao() {
        return provinceDao;
    }

    public StreetDao getStreetDao() {
        return streetDao;
    }

}
