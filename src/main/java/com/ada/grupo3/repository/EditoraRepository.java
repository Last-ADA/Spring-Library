package com.ada.grupo3.repository;

import com.ada.grupo3.entity.Autor;
import com.ada.grupo3.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, String> {
    List<Editora> findByNomeIgnoreCaseContaining(String nome);
}
