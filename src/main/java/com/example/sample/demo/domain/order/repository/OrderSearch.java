package com.example.sample.demo.domain.order.repository;

import com.example.sample.demo.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName; //회원이름
    private OrderStatus orderStatus; //주문 상태 (ORDER, CANCEL)
}
