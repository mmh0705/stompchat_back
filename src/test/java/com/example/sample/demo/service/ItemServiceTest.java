package com.example.sample.demo.service;

import com.example.sample.demo.domain.item.Item;
import com.example.sample.demo.domain.item.repository.ItemClassicRepository;
import com.example.sample.demo.domain.item.samples.Album;
import com.example.sample.demo.domain.item.samples.Book;
import com.example.sample.demo.domain.item.samples.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemClassicRepository itemClassicRepository;
    @Autowired
    ItemService itemService;

    @Test
    public void 아이템_저장() throws Exception{
        //given
        Item itemAlbum = new Album();
        Item itemBook = new Book();
        Item itemMovie = new Movie();

        itemMovie.setName("테스트");
        itemBook.setName("테스트");
        itemAlbum.setName("테스트");
        //when
        Long savedId1 = itemService.saveItem(itemAlbum);
        Long savedId2 = itemService.saveItem(itemBook);
        Long savedId3 = itemService.saveItem(itemMovie);

        //then
        assertEquals(itemAlbum, itemClassicRepository.findById(savedId1));
        assertEquals(itemBook, itemClassicRepository.findById(savedId2));
        assertEquals(itemMovie, itemClassicRepository.findById(savedId3));
    }

    @Test
    public void 모든_아이템_가져오기() throws Exception{
        //given


        //when


        //then


    }

    @Test
    public void 아이디로_아이템_가져오기() throws Exception{
        //given
        //when
        //then
    }

}