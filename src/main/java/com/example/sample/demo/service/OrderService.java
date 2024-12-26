package com.example.sample.demo.service;

import com.example.sample.demo.domain.delivary.Delivery;
import com.example.sample.demo.domain.item.Item;
import com.example.sample.demo.domain.item.repository.ItemClassicRepository;
import com.example.sample.demo.domain.member.Member;
import com.example.sample.demo.domain.member.repository.MemberClassicRepository;
import com.example.sample.demo.domain.member.repository.MemberRepository;
import com.example.sample.demo.domain.order.Order;
import com.example.sample.demo.domain.order.repository.OrderRepository;
import com.example.sample.demo.domain.orderItem.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberClassicRepository memberRepository;
    private final ItemClassicRepository itemClassicRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemClassicRepository.findById(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem); //생성자를 만들지 못하도록 protected를

        //주문 저장
        orderRepository.save(order); //persist를 order에만 날릴 수 있는 이유는 casacade를 orderItems와 delivery에 걸어놨기 때문에.

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        //일단 Order를 가져온다
        Order targetOrder = orderRepository.findById(orderId);
        //주문 취소
        targetOrder.cancel();
    }

    /**
     * 주문 검색
     */
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }
    //검색
}
