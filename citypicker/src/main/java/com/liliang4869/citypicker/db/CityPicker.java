package com.liliang4869.citypicker.db;

import android.content.Context;


import com.liliang4869.citypicker.CityPickerDialog;
import com.liliang4869.citypicker.entity.District;
import com.liliang4869.citypicker.entity.City;
import com.liliang4869.citypicker.entity.Province;
import com.liliang4869.citypicker.entity.Street;

import org.greenrobot.greendao.database.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CityPicker {
    public static String DB_NAME = "data.sqlite";
    Context context;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ProvinceDao provinceDao;
    private CityDao cityDao;
    private DistrictDao districtDao;
    private StreetDao streetDao;
    private static CityPicker instance;
    public static CityPicker getInstance() {
        if (instance == null) instance = new CityPicker();
        return instance;
    }


public CityPickerDialog buildCityPickerDialog(Context context){
        return new CityPickerDialog(context);
}

    public void init(Context context) {
        this.context = context;
        try {
            copyDataBase(context,DB_NAME);
            Database database =new DaoMaster.DevOpenHelper(context,DB_NAME,null).getWritableDb();
            daoMaster = new DaoMaster(database);
            daoSession = daoMaster.newSession();
            cityDao=daoSession.getCityDao();
            provinceDao=daoSession.getProvinceDao();
            districtDao=daoSession.getDistrictDao();
            streetDao=daoSession.getStreetDao();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public ProvinceDao getProvinceDao() {
        return provinceDao;
    }

    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public DistrictDao getDistrictDao() {
        return districtDao;
    }

    public void setDistrictDao(DistrictDao districtDao) {
        this.districtDao = districtDao;
    }

    public StreetDao getStreetDao() {
        return streetDao;
    }

    public void setStreetDao(StreetDao streetDao) {
        this.streetDao = streetDao;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     * */
    private void copyDataBase(Context context, String dbname) throws IOException {
        // Open your local db as the input stream
        InputStream myInput = context.getAssets().open(dbname);
        // Path to the just created empty db
        File outFileName = context.getDatabasePath(dbname);

        if (!outFileName.exists()) {
            outFileName.getParentFile().mkdirs();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
    }
public Province queryProvinceByCode(String code){
    try {
        Long c= Long.parseLong(code);
        List<Province> list=provinceDao.queryBuilder().where(ProvinceDao.Properties.Code.eq(c)).list();
        if(list.size()>=1)return list.get(0);
        else return null;
    } catch (NumberFormatException e) {
        e.printStackTrace();
        return null;
    }
}
    public City queryCityByCode(String code){
        try {
            Long c= Long.parseLong(code);
            List<City> list=cityDao.queryBuilder().where(CityDao.Properties.Code.eq(c)).list();
            if(list.size()>=1)return list.get(0);
            else return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public District queryDistrictByCode(String code){
        try {
            Long c= Long.parseLong(code);
            List<District> list=districtDao.queryBuilder().where(DistrictDao.Properties.Code.eq(c)).list();
            if(list.size()>=1)return list.get(0);
            else return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Street queryStreetByCode(String code){
        try {
            Long c= Long.parseLong(code);
            List<Street> list=streetDao.queryBuilder().where(StreetDao.Properties.Code.eq(c)).list();
            if(list.size()>=1)return list.get(0);
            else return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<City> queryCityByProvinceCode(Long code){
        return cityDao.queryBuilder().where(CityDao.Properties.ProvinceCode.eq(code)).list();

    }
    public List<District> queryDistrictByCityCode(Long code){
        return districtDao.queryBuilder().where(DistrictDao.Properties.CityCode.eq(code)).list();
    }
    public List<Street> queryStreetByDistrictCodeCode(Long code){
        return streetDao.queryBuilder().where(StreetDao.Properties.DistrictCode.eq(code)).list();
    }
}
