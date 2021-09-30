package ru.digitalleague.core.service;

import ru.digitalleague.core.model.CarModel;

public interface CarService {

    int insert(CarModel record);

    CarModel selectByPrimaryKey(Long id);

    int updateByPrimaryKey(CarModel record);

    int deleteByPrimaryKey(CarModel record);
}
