package com.example.sample.demo.domain.delivary;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.sample.demo.domain.address.Address;
import com.example.sample.demo.domain.enums.DeliveryStatus;
import com.example.sample.demo.domain.order.Order;

@Entity
@Getter
@Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMPLETE
}
