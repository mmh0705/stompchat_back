package com.example.sample.demo.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.sample.demo.domain.address.Address;
import com.example.sample.demo.domain.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();



//    @Column(name="created_at")
//    private LocalDateTime createdAt;
}
