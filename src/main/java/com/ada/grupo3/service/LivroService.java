package com.ada.grupo3.service;

import com.ada.grupo3.dto.request.LivroRequestDTO;
import com.ada.grupo3.dto.response.LivroDetailsResponseDTO;
import com.ada.grupo3.entity.Livro;
import com.ada.grupo3.mapper.LivroMapper;
import com.ada.grupo3.repository.CategoriaRepository;
import com.ada.grupo3.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;
    private final CategoriaRepository categoriaRepository;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper, CategoriaService categoriaService, CategoriaRepository categoriaRepository) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
        this.categoriaRepository = categoriaRepository;
    }

    public List<LivroDetailsResponseDTO> listAll() {
        var livros = livroRepository.findAll();
        return livroMapper.entityToResponseList(livros);
    }

    public LivroDetailsResponseDTO findByIsbn13(String isbn13) {
        var livro = livroRepository.findById(isbn13)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return livroMapper.entityToResponse(livro);
    }

    public List<LivroDetailsResponseDTO> findByTitulo(String titulo) {
        var livros = livroRepository.findByNomeIgnoreCaseContaining(titulo);
        return livroMapper.entityToResponseList(livros);
    }

    public List<LivroDetailsResponseDTO> findByAutorNome(String autorNome) {
        var livros = livroRepository.findByAutorNomeIgnoreCaseContaining(autorNome);
        return livroMapper.entityToResponseList(livros);
    }

    public List<LivroDetailsResponseDTO> findByEditoraNome(String editoraNome) {
        var livros = livroRepository.findByEditoraNomeIgnoreCaseContaining(editoraNome);
        return livroMapper.entityToResponseList(livros);
    }

    public List<LivroDetailsResponseDTO> findByDisponivel(Boolean disponivel) {
        var livros = livroRepository.findByDisponivel(disponivel);
        return livroMapper.entityToResponseList(livros);
    }

    public List<LivroDetailsResponseDTO> findByCategoria(String categoria) {
        var livros = livroRepository.findByCategoriasNomeIgnoreCase(categoria);
        return livroMapper.entityToResponseList(livros);
    }

    public Livro create(LivroRequestDTO livroRequestDTO) {
        var livro = livroMapper.requestToEntity(livroRequestDTO, categoriaRepository);
        livroRepository.save(livro);
        return livro;
    }

    public void updateAll(String isbn13, LivroRequestDTO livroRequestDTO) {
        livroRepository.findById(isbn13)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        var livroUpdate = livroMapper.requestToEntity(livroRequestDTO, categoriaRepository);
        livroUpdate.setIsbn13(isbn13);
        livroRepository.save(livroUpdate);
    }

    public void deleteByIsbn13(String isbn13) { livroRepository.deleteById(isbn13); }

}
