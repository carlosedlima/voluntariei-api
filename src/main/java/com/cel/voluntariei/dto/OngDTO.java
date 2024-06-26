package com.cel.voluntariei.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OngDTO {
    private Long id;
    private String nome;
    private String email;

    private String senha;
}