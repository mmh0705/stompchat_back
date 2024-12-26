package com.example.sample.demo.domain.item.repository;

import com.example.sample.demo.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemClassicRepository {
    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ //신규라면 id가 null이므로 신규등록
            em.persist(item);
        }
        else{
            em.merge(item); //id가 있다면
        }
    }

    public Item findById(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery(
                "select i" +
                        "from Item i",Item.class).getResultList();
    }
}
