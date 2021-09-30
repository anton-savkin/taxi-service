package ru.digitalleague.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.core.mapper.TaxiInfoMapper;
import ru.digitalleague.core.model.TaxiDriverInfoModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaxiInfoServiceImpl implements TaxiInfoService {

    private final TaxiInfoMapper taxiInfoMapper;

    @Override
    public int insert(TaxiDriverInfoModel record) {
        return taxiInfoMapper.insert(record);
    }

    @Override
    public TaxiDriverInfoModel selectByPrimaryKey(Long driverId) {
        return taxiInfoMapper.selectByPrimaryKey(driverId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int updateByPrimaryKey(TaxiDriverInfoModel record) {
        return taxiInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(TaxiDriverInfoModel record) {
        return taxiInfoMapper.deleteByPrimaryKey(record);
    }
}
