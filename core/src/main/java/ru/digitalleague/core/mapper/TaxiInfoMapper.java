package ru.digitalleague.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.TaxiDriverInfoModel;

import java.util.List;

@Repository
@Mapper
public interface TaxiInfoMapper {

    @Select("select count(1) from taxi_drive_info")
    int getCount();


    @Results(id = "drivers", value = {
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "middleName", column = "middle_name"),
            @Result(property = "level", column = "level"),
            @Result(property = "carModel", column = "car_model"),
            @Result(property = "createDttm", column = "create_dttm")
    })
    @Select("SELECT driver_id, last_name, first_name, middle_name, level, car_model, create_dttm FROM taxi_drive_info")
    List<TaxiDriverInfoModel> getAllDrivers();

    int insert(TaxiDriverInfoModel record);

    TaxiDriverInfoModel selectByPrimaryKey(Long driverId);

    int updateByPrimaryKey(TaxiDriverInfoModel record);

    int deleteByPrimaryKey(TaxiDriverInfoModel record);
}