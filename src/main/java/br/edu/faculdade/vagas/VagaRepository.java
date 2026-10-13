@Repository
public class VagaRepository {

    // List.of(...) é IMUTÁVEL — não aceita add nem set. Envolver num
    // ArrayList é o que torna o repository escrevível.
    private final List<Vaga> vagas = new ArrayList<>(List.of(
        new Vaga("1", "Pessoa Desenvolvedora Front-end Júnior", …),
        // … as 12 vagas da aula 02
    ));

    // copyOf protege a lista interna: quem recebe não consegue
    // alterá-la por fora, e a única porta de escrita continua aqui.
    public List<Vaga> todas() {
        return List.copyOf(vagas);
    }

    public Optional<Vaga> porId(String id) {
        return vagas.stream().filter(v -> v.id().equals(id)).findFirst();
    }

    public Vaga salvar(Vaga nova) {
        vagas.add(nova);
        return nova;
    }

    // Devolve Optional: vazio quer dizer "esse id não existe aqui",
    // que é o que o controller precisa para escolher o 404.
    public Optional<Vaga> trocar(String id, Vaga nova) {
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).id().equals(id)) {
                vagas.set(i, nova);
                return Optional.of(nova);
            }
        }
        return Optional.empty();
    }

    // removeIf já devolve true/false: apagou ou não achou.
    public boolean remover(String id) {
        return vagas.removeIf(v -> v.id().equals(id));
    }
}
