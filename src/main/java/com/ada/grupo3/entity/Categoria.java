package com.ada.grupo3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descricao;

    @ManyToMany(mappedBy = "categorias")
    private List<Livro> livros;
}
