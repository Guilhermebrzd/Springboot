@RestController
@RequestMapping("/vagas")   // mesmo caminho base do VagaController
public class BuscaController {

    private final BuscaService service;

    public BuscaController(BuscaService service) {
        this.service = service;
    }

    // Busca sem resultado devolve 200 com lista vazia — e isso está
    // CERTO. Você procurou, o resultado é zero. Diferente de pedir
    // um id que não existe, que é 404.
    @GetMapping("/busca")
    public List<Vaga> buscar(
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String senioridade,
            @RequestParam(required = false) Boolean aceitaIniciante) {
        return service.buscar(area, senioridade, aceitaIniciante);
    }
}
