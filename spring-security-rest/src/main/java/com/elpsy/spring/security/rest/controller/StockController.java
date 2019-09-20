package com.elpsy.spring.security.rest.controller;


import com.elpsy.spring.security.rest.dao.model.Stock;
import com.elpsy.spring.security.rest.dao.repo.StockRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StockController {

    private StockRepo stockRepo;

    @GetMapping("/stocks")
    public List<Stock> getStockList(){
        return stockRepo.findAll();
    }


    @GetMapping("/stocks/{stockId}")
    public ResponseEntity<Stock> getStockId(@PathVariable String stockId){
        Optional<Stock> stockOpt = stockRepo.findById(stockId);
        return stockOpt.isPresent() ? new ResponseEntity<>(stockOpt.get(), HttpStatus.OK) :  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("stocks/{stockId}")
    public ResponseEntity<String> deleteStock(@PathVariable String stockId){
        Optional<Stock> stockOpt = stockRepo.findById(stockId);
        if(stockOpt.isPresent()){
           stockRepo.delete(stockOpt.get());
           return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
