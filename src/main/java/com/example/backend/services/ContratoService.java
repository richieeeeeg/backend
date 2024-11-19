package com.example.backend.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.backend.dto.ContratoDTO;
import com.example.backend.entities.Contrato;
import com.example.backend.repositories.ContratoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final ModelMapper modelMapper;

    public ContratoService(ContratoRepository contratoRepository, ModelMapper modelMapper) {
        this.contratoRepository = contratoRepository;
        this.modelMapper = modelMapper;
    }

    public ContratoDTO createContrato(ContratoDTO contratoDTO) {
        Contrato contrato = modelMapper.map(contratoDTO, Contrato.class);
        return modelMapper.map(contratoRepository.save(contrato), ContratoDTO.class);
    }

    public ContratoDTO updateContrato(Long id, ContratoDTO contratoDTO) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato no encontrado"));
        modelMapper.map(contratoDTO, contrato);
        return modelMapper.map(contratoRepository.save(contrato), ContratoDTO.class);
    }

    public void deleteContrato(Long id) {
        contratoRepository.deleteById(id);
    }

    public List<ContratoDTO> getAllContratos() {
        return contratoRepository.findAll().stream()
                .map(contrato -> modelMapper.map(contrato, ContratoDTO.class))
                .toList();
    }

    public ContratoDTO getContratoById(Long id) {
        return contratoRepository.findById(id)
                .map(contrato -> modelMapper.map(contrato, ContratoDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Contrato no encontrado"));
    }
}
