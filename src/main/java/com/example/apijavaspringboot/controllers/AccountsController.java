package com.example.apijavaspringboot.controllers;

import com.example.apijavaspringboot.entities.dtos.*;
import com.example.apijavaspringboot.services.AccountService;
import com.example.apijavaspringboot.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/accounts")
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountid}/stocks")
    public ResponseEntity<Void> associateStocks(@PathVariable("accountid") UUID accountid, @RequestBody AssociateAccountStockDto associateAccountStockDto) {

        accountService.associateStock(accountid, associateAccountStockDto);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/{accountid}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> getAccountStocks(@PathVariable("accountid") UUID accountid) {

        var accountStocks = accountService.listAccountsStocks(accountid);

        return ResponseEntity.ok(accountStocks);
    }
}
