package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public Page<DadosListagemMedico> listar(
            @PageableDefault(size = 10, sort = { "nome" })
            Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @PostMapping
    @Transactional
    public void cadastrar(
            @Valid
            @RequestBody DadosCadastroMedico dados) {
        medicoRepository.save(new Medico(dados));
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(
            @Valid
            @RequestBody DadosAtualizacaoMedico dados, @PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        medicoRepository.deleteById(id);
    }
}
