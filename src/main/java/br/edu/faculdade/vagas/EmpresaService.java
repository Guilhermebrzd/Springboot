package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * O mesmo formato do VagaService, com uma diferença que é da frente 2: empresa
 * se busca por slug, não por id — é o slug que vai na URL.
 */
@Service
public class EmpresaService {

    private final EmpresaRepository repositorio;

    public EmpresaService(EmpresaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Empresa> listar() {
        return repositorio.todas();
    }

    public Optional<Empresa> buscarPorSlug(String slug) {
        return repositorio.porSlug(slug);
    }

    public Empresa criar(EmpresaEntrada entrada) {
    // Checagem de REGRA DE NEGÓCIO (slug repetido)
        if (repositorio.porSlug(entrada.slug()).isPresent()) {
            throw new RecursoJaExiste("Empresa " + entrada.slug() + " já existe.");
        }

        Empresa nova = new Empresa(
            UUID.randomUUID().toString(),
            entrada.nome(),
            entrada.slug(),
            entrada.site(),
            entrada.descricao()
        );

        return repositorio.salvar(nova);
    }

    public boolean apagar(String slug) {
        return repositorio.remover(slug);
    }
}
