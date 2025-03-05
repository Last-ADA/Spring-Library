package com.ada.grupo3.service;

import com.ada.grupo3.dto.request.AutorRequestDTO;
import com.ada.grupo3.dto.response.AutorDetailsResponseDTO;
import com.ada.grupo3.entity.Autor;
import com.ada.grupo3.mapper.AutorMapper;
import com.ada.grupo3.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;


    public AutorService(AutorRepository autorRepository, AutorMapper autorMapper) {
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
    }

    public List<AutorDetailsResponseDTO> listAll() {
        var autores = autorRepository.findAll();
        return autorMapper.entityToResponseList(autores);
    }

    public AutorDetailsResponseDTO findById(Long id) {
        var autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        return autorMapper.entityToResponse(autor);
    }

    public Autor create(AutorRequestDTO autorRequestDTO) {
        var autor = autorMapper.requestToEntity(autorRequestDTO);
        autorRepository.save(autor);
        return autor;
    }

    public void updateAll(Long id , AutorRequestDTO autorRequestDTO) {
        autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        var autorUpdate = autorMapper.requestToEntity(autorRequestDTO);
        autorUpdate.setId(id);
        autorRepository.save(autorUpdate);
    }

    public void deleteById(Long id) {
        autorRepository.deleteById(id);
    }

    public List<AutorDetailsResponseDTO> listByNome(String nome) {
        var autores = autorRepository.findByNomeIgnoreCaseContaining(nome);
        return autorMapper.entityToResponseList(autores);
    }
}
