package ru.digitalleague.core.mapper;

import ru.digitalleague.core.model.CarModel;

public interface CarMapper {

    int insert(CarModel record);

    CarModel selectByPrimaryKey(Long id);

    int updateByPrimaryKey(CarModel record);

    int deleteByPrimaryKey(CarModel record);
}
