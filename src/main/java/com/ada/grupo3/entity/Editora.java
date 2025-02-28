package com.ada.grupo3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Editora {

    @Id
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;

    @OneToMany
    private List<Livro> livros;
}
