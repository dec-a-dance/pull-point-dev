package com.example.pullpointdev.util;

import com.example.pullpointdev.category.model.Category;
import com.example.pullpointdev.artist.repository.ArtistRepository;
import com.example.pullpointdev.category.repository.CategoryRepository;
import com.example.pullpointdev.pullpoint.repository.PullPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class DatabaseTemplateCreator{
    private final PullPointRepository pullPointRepository;
    private final ArtistRepository artistRepository;
    private final CategoryRepository categoryRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void run(){
        if(categoryRepository.findAll().size()!=0){
            log.info("database template has already been initialized");
            return;
        }
        log.info("creating template database... ");
        Category testCategory1 = new Category();
        Category testCategory2 = new Category();
        Category testCategory3 = new Category();
        testCategory1.setName("Музыка");
        testCategory2.setName("Фаер-шоу");
        testCategory3.setName("Художники");
        testCategory1.setPic("somepic");
        testCategory2.setPic("somepic");
        testCategory3.setPic("somepic");
        categoryRepository.saveAll((List.of(testCategory1, testCategory2, testCategory3)));
        Category testSubCategory1 = new Category();
        Category testSubCategory2 = new Category();
        Category testSubCategory3 = new Category();
        Category testSubCategory4 = new Category();
        Category testSubCategory5 = new Category();
        Category testSubCategory6 = new Category();
        Category testSubCategory7 = new Category();
        Category testSubCategory8 = new Category();
        Category testSubCategory9 = new Category();
        testSubCategory1.setName("Акустика");
        testSubCategory1.setParent(categoryRepository.findByName("Музыка"));
        testSubCategory2.setName("Поп");
        testSubCategory2.setParent(categoryRepository.findByName("Музыка"));
        testSubCategory3.setName("Рэп");
        testSubCategory3.setParent(categoryRepository.findByName("Музыка"));
        testSubCategory4.setName("Жонглирование факелами");
        testSubCategory4.setParent(categoryRepository.findByName("Фаер-шоу"));
        testSubCategory5.setName("Глотание огня");
        testSubCategory5.setParent(categoryRepository.findByName("Фаер-шоу"));
        testSubCategory6.setName("Графика");
        testSubCategory6.setParent(categoryRepository.findByName("Художники"));
        testSubCategory7.setName("Графитти");
        testSubCategory7.setParent(categoryRepository.findByName("Художники"));
        testSubCategory8.setName("Рисунки космоса");
        testSubCategory8.setParent(categoryRepository.findByName("Художники"));
        testSubCategory9.setName("Роспись тарелок");
        testSubCategory9.setParent(categoryRepository.findByName("Художники"));
        categoryRepository.saveAll(List.of(testSubCategory1,testSubCategory2, testSubCategory3, testSubCategory4, testSubCategory5, testSubCategory6, testSubCategory7, testSubCategory8, testSubCategory9));
      /*  Artist testArtist1 = new Artist();
        Artist testArtist2 = new Artist();
        Artist testArtist3 = new Artist();
        testArtist1.setName("Дешёвые драмы");
        testArtist2.setName("dec-a-dance");
        testArtist3.setName("Рисуем грибы за 10 минут");
        testArtist1.setCategories(List.of(testCategory1));
        testArtist2.setCategories(List.of(testCategory2));
        testArtist3.setCategories(List.of(testCategory3));
        testArtist1.setDescription("Лучший уличный коллектив города Санкт-Петербурга.");
        testArtist2.setDescription("Какой-то странный чел снова устраивает фаер-шоу");
        testArtist3.setDescription("Учимся рисовать грибы всего за 10 минут у профессиональных художников.");
        artistRepository.saveAll(List.of(testArtist1, testArtist2, testArtist3));
        PullPoint testPullPoint1 = new PullPoint();
        PullPoint testPullPoint2 = new PullPoint();
        PullPoint testPullPoint3 = new PullPoint();
        testPullPoint1.setName("Стрит на Грибе");
        testPullPoint2.setName("Худшее фаер-шоу на свете");
        testPullPoint3.setName("Рисуем грибы");
        testPullPoint1.setArtists(List.of(testArtist1));
        testPullPoint2.setArtists(List.of(testArtist2));
        testPullPoint3.setArtists(List.of(testArtist3));
        testPullPoint1.setDescription("Традиционный стрит на Канале Грибоедова");
        testPullPoint2.setDescription("Абсолютно точно не проплаченная реклама артиста dec-a-dance (подписывайтесь на паблик)");
        testPullPoint3.setDescription("Мастер-класс по рисованию грибов любой сложности");
        pullPointRepository.saveAll(List.of(testPullPoint1, testPullPoint2, testPullPoint3)); */
        //todo geo
    }
}
