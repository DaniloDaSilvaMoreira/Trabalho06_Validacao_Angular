package br.unitins.ecommerce.dto.genero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GeneroDTO (
    
    @NotBlank(message = "Campo nome não pode estar vazio")
    @Size(max = 60, message = "O campo nome deve possuir no máximo 60 caracteres.")
    String nome
) {
    
}
