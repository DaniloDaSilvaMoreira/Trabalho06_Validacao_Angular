package br.unitins.ecommerce.service.fabricante;

import java.util.List;
import jakarta.validation.Valid;
import br.unitins.ecommerce.dto.fabricante.FabricanteDTO;
import br.unitins.ecommerce.dto.fabricante.FabricanteResponseDTO;

public interface FabricanteService {
    
    List<FabricanteResponseDTO> getAll(int page, int pageSize);
    
    FabricanteResponseDTO getById(Long id);

    FabricanteResponseDTO insert(@Valid FabricanteDTO fabricanteDTO);

    FabricanteResponseDTO update(Long id, @Valid FabricanteDTO fabricanteDTO);

    void delete(Long id);

    List<FabricanteResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
    
}
