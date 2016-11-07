package com.cawlapp.cawlapp.data.entity.daoconverter;

import org.greenrobot.greendao.converter.PropertyConverter;

public class DealTypeConverter implements PropertyConverter<DealType, String>{
    @Override
    public DealType convertToEntityProperty(String databaseValue) {
        return DealType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(DealType entityProperty) {
        return entityProperty.name();
    }
}
