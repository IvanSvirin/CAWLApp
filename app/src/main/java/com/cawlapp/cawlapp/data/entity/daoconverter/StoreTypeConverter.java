package com.cawlapp.cawlapp.data.entity.daoconverter;

import org.greenrobot.greendao.converter.PropertyConverter;

public class StoreTypeConverter implements PropertyConverter<StoreType, String>{
    @Override
    public StoreType convertToEntityProperty(String databaseValue) {
        return StoreType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(StoreType entityProperty) {
        return null;
    }

}
