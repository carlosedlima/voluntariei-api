package com.cel.voluntariei.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private Ong ong;
}