package com.example.apijavaspringboot.controllers;

import com.example.apijavaspringboot.entities.User;
import com.example.apijavaspringboot.entities.dtos.*;
import com.example.apijavaspringboot.services.StockService;
import com.example.apijavaspringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto) {

        stockService.createStock(createStockDto);

        return ResponseEntity.ok().build();
    }
}
