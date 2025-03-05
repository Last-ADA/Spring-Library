package com.ada.grupo3.repository;

import com.ada.grupo3.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, String> {

    List<Livro> findByNomeIgnoreCaseContaining(String nome);

    List<Livro> findByAutorNomeIgnoreCaseContaining(String autorNome);

    List<Livro> findByEditoraNomeIgnoreCaseContaining(String editoraNome);

    List<Livro> findByDisponivel(Boolean disponivel);

}
