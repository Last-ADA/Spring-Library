package com.ada.grupo3.service;

import com.ada.grupo3.dto.request.CategoriaRequestDTO;
import com.ada.grupo3.dto.response.CategoriaDetailsResponseDTO;
import com.ada.grupo3.entity.Categoria;
import com.ada.grupo3.mapper.CategoriaMapper;
import com.ada.grupo3.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public List<CategoriaDetailsResponseDTO> listAll() {
        var categorias = categoriaRepository.findAll();
        return categoriaMapper.entityToResponseList(categorias);
    }

    public CategoriaDetailsResponseDTO findById(Long id) {
        var categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Categoria não encontrada")
        );
        return categoriaMapper.entityToResponse(categoria);
    }

    public Categoria create(CategoriaRequestDTO categoriaRequestDTO) {
        var categoria = categoriaMapper.requestToEntity(categoriaRequestDTO);
        categoriaRepository.save(categoria);
        return categoria;

    }

    public void updateAll(Long id, CategoriaRequestDTO categoriaRequestDTO) {
        categoriaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Categoria não encontrada"));
        var categoriaUpdate = categoriaMapper.requestToEntity(categoriaRequestDTO);
        categoriaUpdate.setId(id);
        categoriaRepository.save(categoriaUpdate);
    }

    public void deleteById(Long id) { categoriaRepository.deleteById(id);}
}
