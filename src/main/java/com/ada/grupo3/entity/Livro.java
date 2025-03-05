package com.ada.grupo3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Livro {

    @Id
    private String isbn13;
    private String nome;
    private Integer numPaginas;
    private String descricao;
    private Boolean disponivel;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Editora editora;

//    @OneToOne
//    private Emprestimo emprestimo;
}
