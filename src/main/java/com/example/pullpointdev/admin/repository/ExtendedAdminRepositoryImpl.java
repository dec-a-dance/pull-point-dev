package com.example.pullpointdev.admin.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@AllArgsConstructor
@Repository
public class ExtendedAdminRepositoryImpl implements ExtendedAdminRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void clean(){
        StringBuilder query = new StringBuilder();
        query.append("delete from wallet_history;");
        query.append("delete from currency_transaction;");
        query.append("update wallet set owner_id=null where id>0;");
        query.append("update user_data set wallet_id=null where id>0;");
        query.append("delete from wallet;");
        query.append("delete from pull_point_artists;");
        query.append("delete from pull_point_subcategories;");
        query.append("delete from pull_point;");
        query.append("delete from artist_subcategories;");
        query.append("update artist set category_id=null where id>0;");
        query.append("update pull_point set category_id=null where id>0;");
        query.append("delete from user_data_artists;");
        query.append("delete from artist;");
        query.append("delete from user_data_favourites;");
        query.append("delete from user_data;");
        entityManager.createNativeQuery(query.toString()).executeUpdate();
    }
}
