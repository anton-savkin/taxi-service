package ru.digitalleague.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.core.mapper.CarMapper;
import ru.digitalleague.core.model.CarModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;

    @Override
    public int insert(CarModel record) {
        return carMapper.insert(record);
    }

    @Override
    public CarModel selectByPrimaryKey(Long id) {
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int updateByPrimaryKey(CarModel record) {
        return carMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(CarModel record) {
        return carMapper.deleteByPrimaryKey(record);
    }
}
