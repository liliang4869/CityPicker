package com.liliang4869.citypicker.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.liliang4869.citypicker.entity.Street;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "street".
*/
public class StreetDao extends AbstractDao<Street, Long> {

    public static final String TABLENAME = "street";

    /**
     * Properties of entity Street.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Code = new Property(0, Long.class, "code", true, "code");
        public final static Property Name = new Property(1, String.class, "name", false, "name");
        public final static Property DistrictCode = new Property(2, Long.class, "districtCode", false, "areaCode");
        public final static Property ProvinceCode = new Property(3, Long.class, "provinceCode", false, "provinceCode");
        public final static Property CityCode = new Property(4, Long.class, "cityCode", false, "cityCode");
    }


    public StreetDao(DaoConfig config) {
        super(config);
    }
    
    public StreetDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Street entity) {
        stmt.clearBindings();
 
        Long code = entity.getCode();
        if (code != null) {
            stmt.bindLong(1, code);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Long districtCode = entity.getDistrictCode();
        if (districtCode != null) {
            stmt.bindLong(3, districtCode);
        }
 
        Long provinceCode = entity.getProvinceCode();
        if (provinceCode != null) {
            stmt.bindLong(4, provinceCode);
        }
 
        Long cityCode = entity.getCityCode();
        if (cityCode != null) {
            stmt.bindLong(5, cityCode);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Street entity) {
        stmt.clearBindings();
 
        Long code = entity.getCode();
        if (code != null) {
            stmt.bindLong(1, code);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Long districtCode = entity.getDistrictCode();
        if (districtCode != null) {
            stmt.bindLong(3, districtCode);
        }
 
        Long provinceCode = entity.getProvinceCode();
        if (provinceCode != null) {
            stmt.bindLong(4, provinceCode);
        }
 
        Long cityCode = entity.getCityCode();
        if (cityCode != null) {
            stmt.bindLong(5, cityCode);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Street readEntity(Cursor cursor, int offset) {
        Street entity = new Street( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // code
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // districtCode
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // provinceCode
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // cityCode
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Street entity, int offset) {
        entity.setCode(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDistrictCode(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setProvinceCode(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setCityCode(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Street entity, long rowId) {
        entity.setCode(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Street entity) {
        if(entity != null) {
            return entity.getCode();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Street entity) {
        return entity.getCode() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
