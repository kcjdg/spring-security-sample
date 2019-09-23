package com.elpsy.spring.security.rest.controller;


import com.elpsy.spring.security.rest.dao.model.Stock;
import com.elpsy.spring.security.rest.dao.repo.StockRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StockController {

    private StockRepo stockRepo;

    @GetMapping("/stocks")
    public List<Stock> getStockList() {
        return stockRepo.findAll();
    }

    @PostMapping("/stocks")
    public ResponseEntity<Stock> saveStock(@Valid @RequestBody Stock stock) {
        return new ResponseEntity<>(stockRepo.save(stock), HttpStatus.CREATED);
    }

    @GetMapping("/stocks/{stockId}")
    public ResponseEntity<Stock> getStockId(@PathVariable String stockId) {
        Optional<Stock> stockOpt = stockRepo.findById(stockId);
        return stockOpt.isPresent() ? new ResponseEntity<>(stockOpt.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("stocks/{stockId}")
    public ResponseEntity<Void> deleteStock(@PathVariable String stockId) {
        Optional<Stock> stockOpt = stockRepo.findById(stockId);
        if (stockOpt.isPresent()) {
            stockRepo.delete(stockOpt.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("stocks/{stockId}")
    public ResponseEntity<Stock> saveOrUpdate(@RequestBody Stock stock, @PathVariable String stockId) {
        return stockRepo.findById(stockId)
                .map(stc -> {
                    stc.setSector(stock.getSector());
                    stc.setStockName(stock.getStockName());
                    stc.setPrice(stock.getPrice());
                    return new ResponseEntity<>(stockRepo.save(stc), HttpStatus.OK);
                })
                .orElseGet(() -> {
                    stock.setStockCode(stockId);
                    return new ResponseEntity<>(stockRepo.save(stock), HttpStatus.OK);
                });
    }

    @PatchMapping("stocks/{stockId}")
    public ResponseEntity<Stock> pathPrice(@RequestBody Stock stock, @PathVariable String stockId){
        return stockRepo.findById(stockId).map(f->{
            Double price = stock.getPrice();
            if(price !=null) {
                f.setPrice(stock.getPrice());
                return new ResponseEntity<>(stockRepo.save(f), HttpStatus.OK);
            }
            return new ResponseEntity<>(f,HttpStatus.BAD_REQUEST);
        }).orElseGet(()->{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }


}
