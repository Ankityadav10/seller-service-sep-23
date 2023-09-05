package com.cbt.sellerservicesep23;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    private final WebClient.Builder webClientBuilder;

    @GetMapping("get/all/orders/sellerwise/{sellername}")
    public List<List<FullOrder>> getOrdersSellerwise(@PathVariable("sellername") String sellername )
    {

        logger.info("fetching orders for SELLER: "+sellername);

        return productofferRepository.findAllBySellername(sellername).stream().
                map(productoffer ->   sellerService.getOrdersOfferwise(productoffer.getId())).
                map(orders -> orders.stream().map(order -> orderService.composeFullOrder(order.getOrderid())).collect(Collectors.toList()) ).
                collect(Collectors.toList());
    }

    @PostMapping("accept/order/{orderid}")
    public ResponseEntity<String> acceptOrder(@PathVariable("orderid") String orderid)
    {
        Mono<String> response =  webClientBuilder.build().post().uri("http://localhost:8072/order-service/api/v1/accept/order/".concat(orderid)).
                body(Mono.just(orderid),String.class).retrieve().bodyToMono(String.class);

        return ResponseEntity.ok().body(" Transaction Complete | "+response.block());

    }


}
