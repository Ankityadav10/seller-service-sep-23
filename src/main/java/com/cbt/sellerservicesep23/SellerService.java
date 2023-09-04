package com.cbt.sellerservicesep23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService
{

    Logger logger;

    OrderRepository orderRepository;
    OrderstatusRepository orderstatusRepository;

    SellerService(OrderRepository orderRepository,
                  OrderstatusRepository orderstatusRepository)
    {
        this.orderRepository = orderRepository;
        this.orderstatusRepository =  orderstatusRepository;
        logger = LoggerFactory.getLogger(SellerService.class);

        logger.info("SellerService Bean Initialized");

    }

    public List<Order> getOrdersOfferwise(String offerid)
    {
        logger.info("fetching all ORDERS against the OFFER with ID: "+offerid);

        return orderRepository.findByOfferid(offerid).stream().
                filter(order -> orderstatusRepository.findAllByOrderid(order.getOrderid()).
                        stream().filter(orderstatus -> orderstatus.getStatus().equals("OPEN")).findAny().isPresent()).
                collect(Collectors.toList());

    }


}
