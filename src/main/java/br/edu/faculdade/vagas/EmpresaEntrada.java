package br.edu.faculdade.vagas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EmpresaEntrada(
    @NotBlank(message = "o nome é obrigatório")
    String nome,

    @NotBlank(message = "o slug é obrigatório")
    @Pattern(regexp = "[a-z0-9-]+", message = "o slug só aceita minúsculas, números e hífen")
    String slug,

    String site,

    String descricao
) {}
