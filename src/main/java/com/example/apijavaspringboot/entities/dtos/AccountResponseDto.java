package com.example.apijavaspringboot.entities.dtos;

import java.util.UUID;

public record AccountResponseDto(UUID accountId, String description) {
}
