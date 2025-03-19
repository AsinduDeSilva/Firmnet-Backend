package com.example.firmnet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CrudResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;

    public CrudResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
