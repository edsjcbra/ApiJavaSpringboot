package com.example.apijavaspringboot.services;

import com.example.apijavaspringboot.entities.AccountStock;
import com.example.apijavaspringboot.entities.AccountStockId;
import com.example.apijavaspringboot.entities.Stock;
import com.example.apijavaspringboot.entities.dtos.AccountResponseDto;
import com.example.apijavaspringboot.entities.dtos.AccountStockResponseDto;
import com.example.apijavaspringboot.entities.dtos.AssociateAccountStockDto;
import com.example.apijavaspringboot.entities.dtos.CreateStockDto;
import com.example.apijavaspringboot.repositories.AccountRepository;
import com.example.apijavaspringboot.repositories.AccountStockRepository;
import com.example.apijavaspringboot.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private AccountStockRepository accountStockRepository;


    public void associateStock(UUID accountid, AssociateAccountStockDto associateAccountStockDto) {
        var account = accountRepository.findById(accountid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(associateAccountStockDto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //DTO -> ENTITY
        var id = new AccountStockId(account.getId(), stock.getId());
        var accountStock = new AccountStock(
                id,
                account,
                stock,
                associateAccountStockDto.quantity()
        );
        accountStockRepository.save(accountStock);
    }

    public List<AccountStockResponseDto> listAccountsStocks(UUID accountid) {

        var account = accountRepository.findById(accountid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return  account.getAccountStockList()
                .stream()
                .map(ac -> new AccountStockResponseDto(ac.getStock().getId(), ac.getStock().getDescription(), ac.getQuantity(), 0.0)).toList();
    }
}

