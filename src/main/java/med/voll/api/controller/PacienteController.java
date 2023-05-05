package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        return pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);
    }

    @PostMapping
    public void cadastrar(@Valid @RequestBody DadosCadastroPaciente dados) {
        pacienteRepository.save(new Paciente(dados));
    }

}
