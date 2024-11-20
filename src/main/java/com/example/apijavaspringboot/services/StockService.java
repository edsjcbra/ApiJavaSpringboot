package com.example.apijavaspringboot.services;

import com.example.apijavaspringboot.entities.Stock;
import com.example.apijavaspringboot.entities.dtos.CreateStockDto;
import com.example.apijavaspringboot.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;


    public void createStock(CreateStockDto createStockDto) {
        //DTO -> ENTITY
        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );
        stockRepository.save(stock);
    }
}
