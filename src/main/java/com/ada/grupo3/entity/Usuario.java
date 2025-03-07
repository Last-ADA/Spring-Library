package com.ada.grupo3.entity;


import com.ada.grupo3.entity.enums.TipoUserEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String senha;
    private TipoUserEnum tipo;
}
