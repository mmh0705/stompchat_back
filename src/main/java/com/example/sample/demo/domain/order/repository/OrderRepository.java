package com.example.sample.demo.domain.order.repository;
import com.example.sample.demo.domain.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    /**
     * 주문 저장
     * @param order
     */
    public void save(Order order){
        em.persist(order);
    }

    /**
     * id로 단건 조회
     */
    public Order findById(Long id){
        return em.find(Order.class,id);
    }

    /**
     * 전부 조회
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> o = cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        //주문 상태 검색
        if(orderSearch.getOrderStatus() != null){
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        //회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())){
            Predicate name = cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();

//        String jpql =
//                "select o"+
//                "from Order o"+
//                "join o.member m";
//
//        boolean isFirstCondition =tru
//
//        return em.createQuery(
//                "select o" +
//                        "from Order o" +
//                        "join o.member m" +
//                        "where o.status = :status" +
//                        "and m.name like :name",
//                        Order.class)
//                .setParameter("status", orderSearch.getOrderStatus())
//                .setParameter("name", orderSearch.getMemberName())
//                //.setFirstResult(100)
//                .setMaxResults(1000)
//                .getResultList();
    }
}
