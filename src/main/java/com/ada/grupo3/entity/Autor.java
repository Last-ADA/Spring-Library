package com.ada.grupo3.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Autor {

    @Setter
    @Getter
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;

    @OneToMany
    private List<Livro> livros = new ArrayList<>(); // testar no futuro se a inserção de novos livros não reinicializa a lista

}
