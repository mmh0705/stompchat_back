package com.example.sample.demo.service;

import com.example.sample.demo.domain.address.Address;
import com.example.sample.demo.domain.enums.OrderStatus;
import com.example.sample.demo.domain.item.Item;
import com.example.sample.demo.domain.item.samples.Book;
import com.example.sample.demo.domain.member.Member;
import com.example.sample.demo.domain.order.Order;
import com.example.sample.demo.domain.order.repository.OrderRepository;
import com.example.sample.demo.exception.NotEnoughStockException;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = new Member();
        member.setName("테스트1");
        Address address = new Address("서울", "와우산로","75501");
        member.setAddress(address);
        entityManager.persist(member);

        Book book = new Book();
        book.setName("샘플");
        book.setPrice(10000);
        book.setStockQuantity(10);
        entityManager.persist(book);

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findById(orderId);
        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문 시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(),"주문 가격은 가격 * 수량이다.");
        assertEquals(8, book.getStockQuantity(), getOrder.getTotalPrice(),"주문 수량만큼 재고가 줄어야한다.");

    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook("책이름",10000, 10);

        int orderCount = 11;
        //then
        assertThrows(NotEnoughStockException.class, ()->{
            //when
            orderService.order(member.getId(), book.getId(), orderCount);
        }, "재고 수량 부족 예외가 발생해야 한다.");
        //removeStock의 단위 테스트가 존재할 필요가 있다.

    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Book item = createBook("테스트",10000, 10);

        int orderQuantity = 5;
        Long orderId = orderService.order(member.getId(), item.getId(), orderQuantity);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findById(orderId);

        assertEquals(getOrder.getStatus(), OrderStatus.CANCEL, "주문 취소 시 상태는 Cancel이다.");
        assertEquals(10,item.getStockQuantity(),"주문이 취소한 상품은 그만큼 재고가 증가해야한다.");
    }

    private Member createMember(){
        Member member = new Member();
        member.setName("테스트1");
        Address address = new Address("서울", "와우산로","75501");
        member.setAddress(address);
        entityManager.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity){
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        entityManager.persist(book);
        return book;
    }
}