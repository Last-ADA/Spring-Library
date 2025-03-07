package com.ada.grupo3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


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
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "editora_cnpj")
    private Editora editora;

    @ManyToMany
    @JoinTable(
            name = "categorias_table",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias;

//    @OneToOne
//    private Emprestimo emprestimo;
}
