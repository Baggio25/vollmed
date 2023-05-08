package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CRM é obrigatório")
        @Email(message = "O e-mail está no formato inválido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}", message = "O CPF está no formato inválido")
        String cpf,

        @NotNull(message = "Os dados do endereço são obrigatórios")
        @Valid
        DadosEndereco endereco
) { }
