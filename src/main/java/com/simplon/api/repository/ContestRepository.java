package com.simplon.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.simplon.api.model.Contest;

public interface ContestRepository extends CrudRepository<Contest, Long>{
    
}