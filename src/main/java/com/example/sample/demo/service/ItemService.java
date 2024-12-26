package com.example.sample.demo.service;

import com.example.sample.demo.domain.item.Item;
import com.example.sample.demo.domain.item.repository.ItemClassicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemClassicRepository itemClassicRepository;

    /**
     * 아이템 저장
     * @param item
     */
    @Transactional
    public Long saveItem(Item item){
        itemClassicRepository.save(item);
        return item.getId();
    }

    /**
     * 모든 아이템 가져오기
     * @return
     */
    public List<Item> findAll(){
        return itemClassicRepository.findAll();
    }

    /**
     * 아이디로 아이템 가져오기
     * @param id
     * @return
     */
    public Item findById(Long id){
        return itemClassicRepository.findById(id);
    }
}
