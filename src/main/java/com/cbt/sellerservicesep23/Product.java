package com.cbt.sellerservicesep23;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@RedisHash
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(name = "hscode", nullable = false, length = 8)
    private String hscode;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "unit", length = 5)
    private String unit;

}