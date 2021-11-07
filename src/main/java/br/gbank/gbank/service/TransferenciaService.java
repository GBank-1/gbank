package br.gbank.gbank.service;

import br.gbank.gbank.dto.TransferenciaDTO;
import br.gbank.gbank.exception.ContaSemSaldoException;
import br.gbank.gbank.exception.NotFoundException;
import br.gbank.gbank.model.TaxaEspecialTransferencia;
import br.gbank.gbank.model.TaxaNormalTransferencia;
import br.gbank.gbank.model.TaxaTransferencia;
import br.gbank.gbank.model.Transferencia;
import br.gbank.gbank.model.entity.Conta;
import br.gbank.gbank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class TransferenciaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private HistoricoService historicoService;

    @Transactional
    public void transferir(TransferenciaDTO transferenciaDTO) {
        Conta origem = contaRepository.getContaById(transferenciaDTO.getContaDebitoId())
                .orElseThrow(() -> new NotFoundException(true, "conta", "id", Long.toString(transferenciaDTO.getContaDebitoId())));
        Conta destino = contaRepository.getContaById(transferenciaDTO.getContaCreditoId())
                .orElseThrow(() -> new NotFoundException(true, "conta", "id", Long.toString(transferenciaDTO.getContaCreditoId())));

        Transferencia transferencia = new Transferencia(destino, origem, transferenciaDTO.getValor().toMonetaryAmount(), escolherTaxaTransferrencia(origem));

        transferencia.processar();
        contaRepository.save(origem);
        contaRepository.save(destino);
        historicoService.registrar(transferencia);
    }

    private TaxaTransferencia escolherTaxaTransferrencia(Conta origem) {
        TaxaTransferencia taxa = new TaxaNormalTransferencia();
        if (verificarSeClienteEstaNaFaixa6mesesTaxaEspecial(origem.getDataCadastrada())) {
            taxa = new TaxaEspecialTransferencia();
        }
        return taxa;
    }

    private boolean verificarSeClienteEstaNaFaixa6mesesTaxaEspecial(LocalDate dtCadastro) {
        return LocalDate.now().equals(dtCadastro)
                || (LocalDate.now().isAfter(dtCadastro) && LocalDate.now().isBefore(dtCadastro.plusMonths(6)));
    }

}
