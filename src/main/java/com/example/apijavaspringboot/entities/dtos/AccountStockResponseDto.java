package com.example.apijavaspringboot.entities.dtos;

import java.util.UUID;

public record AccountStockResponseDto(String stockId, String description, int quantity, double total) {
}
