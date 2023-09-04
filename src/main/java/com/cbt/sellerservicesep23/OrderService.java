package com.cbt.sellerservicesep23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    Logger logger;

    OrderRepository orderRepository;
    ProductofferRepository productofferRepository;

    ProductOfferService productOfferService;

    OrderService(OrderRepository orderRepository,
                 ProductofferRepository productofferRepository,
                 ProductOfferService productOfferService)
    {
        this.orderRepository = orderRepository;
        this.productofferRepository = productofferRepository;
        this.productOfferService = productOfferService;
        logger = LoggerFactory.getLogger(OrderService.class);

        logger.info("OrderService Bean Initialized");
    }


    public FullOrder composeFullOrder(String orderid)
    {

        FullOrder fullOrder = new FullOrder(); logger.info("Instantiating a New FullOrder Object");
        fullOrder.setOrder(orderRepository.findById(orderid).get()); logger.info("Initializing the field 'Order' of the FullOrder Object");
        fullOrder.setFullProductOffer(productOfferService.composeFullOffer(orderRepository.findById(orderid).get().getOfferid()));
        logger.info("Initializing the field 'FullProductOffer' of FullOrder Object ");
        return fullOrder;
    }
}