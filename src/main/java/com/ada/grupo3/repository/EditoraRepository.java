package com.ada.grupo3.repository;

import com.ada.grupo3.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, String> {
}
