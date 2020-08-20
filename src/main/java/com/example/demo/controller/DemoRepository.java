package com.example.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity, Integer> {

    DemoEntity findByName(String name);

    List<DemoEntity> findAllBy();

    @Override
    DemoEntity saveAndFlush(DemoEntity voConfig);

}

