package com.cbt.sellerservicesep23;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    public List<Order> findByOfferid(String offerid);
}