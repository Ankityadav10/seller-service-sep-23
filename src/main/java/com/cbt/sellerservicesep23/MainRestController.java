package com.cbt.sellerservicesep23;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class MainRestController {

    Logger logger = LoggerFactory.getLogger(MainRestController.class);

    private final ProductofferRepository productofferRepository;

    private final SellerService sellerService;

    private final OrderService orderService;

    @GetMapping("get/all/orders/sellerwise/{sellername}")
    public List<List<FullOrder>> getOrdersSellerwise(@PathVariable("sellername") String sellername )
    {

        logger.info("fetching orders for SELLER: "+sellername);

        return productofferRepository.findAllBySellername(sellername).stream().
                map(productoffer ->   sellerService.getOrdersOfferwise(productoffer.getId())).
                map(orders -> orders.stream().map(order -> orderService.composeFullOrder(order.getOrderid())).collect(Collectors.toList()) ).
                collect(Collectors.toList());
    }


}
