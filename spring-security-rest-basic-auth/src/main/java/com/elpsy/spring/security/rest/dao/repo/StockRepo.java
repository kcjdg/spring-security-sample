package com.elpsy.spring.security.rest.dao.repo;

import com.elpsy.spring.security.rest.dao.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends CrudRepository<Stock, String>, JpaSpecificationExecutor<Stock>, JpaRepository<Stock,String> {
}
