@Service
public class VagaService {

    private final VagaRepository repository;

    public VagaService(VagaRepository repository) {
        this.repository = repository;
    }

    public List<Vaga> listar() {
        return repository.todas();
    }

    // Nenhum ResponseEntity por aqui: o service não conhece HTTP.
    public Optional<Vaga> buscarPorId(String id) {
        return repository.porId(id);
    }

    // O id nasce AQUI. Se viesse do cliente, dois clientes poderiam
    // escolher o mesmo e um sobrescreveria o outro em silêncio.
    public Vaga criar(Vaga nova) {
        Vaga comId = new Vaga(
            UUID.randomUUID().toString(),
            nova.titulo(), nova.descricao(), nova.area(),
            nova.senioridade(), nova.local(),
            nova.aceitaIniciante(), nova.empresaSlug());
        return repository.salvar(comId);
    }

    // A ARMADILHA: o id vem da URL, não do corpo. Usar nova.id() aqui
    // gravaria null no lugar do id e você perderia o registro.
    public Optional<Vaga> trocar(String id, Vaga nova) {
        Vaga comId = new Vaga(
            id,
            nova.titulo(), nova.descricao(), nova.area(),
            nova.senioridade(), nova.local(),
            nova.aceitaIniciante(), nova.empresaSlug());
        return repository.trocar(id, comId);
    }

    public boolean apagar(String id) {
        return repository.remover(id);
    }
}
