package br.unitins.ecommerce.service.plataforma;

import java.util.List;
import jakarta.validation.Valid;
import br.unitins.ecommerce.dto.plataforma.PlataformaDTO;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponseDTO;

public interface PlataformaService {
    
    List<PlataformaResponseDTO> getAll(int page, int pageSize);

    PlataformaResponseDTO getById(Long id);

    PlataformaResponseDTO insert(@Valid PlataformaDTO plataformaDTO);

    PlataformaResponseDTO update(Long id, @Valid PlataformaDTO plataformaDTO);

    void delete(Long id);

    List<PlataformaResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}
