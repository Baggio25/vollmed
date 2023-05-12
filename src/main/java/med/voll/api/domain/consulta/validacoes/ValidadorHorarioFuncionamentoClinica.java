package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY); //valida se o dia é domingo
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7; // valida se a hora é anterior a abertura da clinica
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18; // valida se a hora é posterior ao fechamento da clinica

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }

}
