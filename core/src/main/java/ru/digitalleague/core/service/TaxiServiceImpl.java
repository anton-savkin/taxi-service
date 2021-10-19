package ru.digitalleague.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.digitalleague.core.api.TaxiService;
import ru.digitalleague.core.mapper.TaxiInfoMapper;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.model.TaxiDriverInfoModel;

@Service
public class TaxiServiceImpl implements TaxiService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private TaxiInfoMapper mapper;

    /**
     * Метод для информоривания такси о поступлении заказа.
     * @param orderDetails - детали заказа
     * @return - ответ от сервиса
     */
    @Override
    public String notifyTaxi(OrderDetails orderDetails) {

        String message = null;
        try {
            message = objectMapper.writeValueAsString(orderDetails);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String queueByCity = mapper.getQueueByCity(orderDetails.getCity());

        if (ObjectUtils.isEmpty(queueByCity)) return "Заказ не принят, город не известен";

        amqpTemplate.convertAndSend(queueByCity, message);

        return "Заказ принят";
    }

    /**
     * Метод для выставления рейтинга водителю.
     * @param driverId - идентификатор водителя
     * @param rate - рейтинг за поездку
     */
    @Override
    public void rateDriver(Long driverId, double rate) {

        TaxiDriverInfoModel driverInfoModel = mapper.selectByPrimaryKey(driverId);
        int ordersCnt = driverInfoModel.getOrdersCnt();
        double oldRate = driverInfoModel.getRate();

        double newRate = estimateNewRate(oldRate, rate, ordersCnt);

        driverInfoModel.setOrdersCnt(++ordersCnt);
        driverInfoModel.setRate(newRate);

        mapper.updateOrdersCntAndRateByDriverId(driverId, ordersCnt, newRate);
    }

    /**
     * Метод для рассчета нового рейтинга.
     * @param oldRate - старый рейтинг
     * @param rate - рейтинг за поездку
     * @param ordersCnt - количество поездок у данного водителя
     * @return - новый рейтинг
     */
    private double estimateNewRate(double oldRate, double rate, int ordersCnt) {

        double oldSumOfRates = oldRate * ordersCnt;

        return (oldSumOfRates+rate)/++ordersCnt;

    }
}
