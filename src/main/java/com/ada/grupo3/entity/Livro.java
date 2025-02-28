package com.ada.grupo3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Livro {

    @Id
    private String isbn13;
    private String nome;
}
