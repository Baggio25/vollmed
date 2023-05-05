package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public Page<DadosListagemPaciente> listar(
            @PageableDefault(size = 10, sort = { "nome" })
            Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@Valid @RequestBody DadosCadastroPaciente dados) {
        pacienteRepository.save(new Paciente(dados));
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(
            @Valid
            @RequestBody DadosAtualizacaoPaciente dados,
            @PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.atualizarInformacoes(dados);
    }

    @PutMapping("/inativar/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
    }


}
