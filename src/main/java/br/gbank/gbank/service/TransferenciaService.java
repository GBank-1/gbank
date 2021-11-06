package br.gbank.gbank.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gbank.gbank.dto.TransferenciaDTO;
import br.gbank.gbank.exception.ContaSemSaldoException;
import br.gbank.gbank.exception.ContaTranferenciaIguaisException;
import br.gbank.gbank.exception.ContaTransferenciaNaoEncontradaException;
import br.gbank.gbank.model.TaxaEspecialTransferencia;
import br.gbank.gbank.model.TaxaNormalTransferencia;
import br.gbank.gbank.model.TaxaTransferencia;
import br.gbank.gbank.model.Transferencia;
import br.gbank.gbank.model.entity.Conta;
import br.gbank.gbank.repository.ContaRepository;

@Service
public class TransferenciaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private HistoricoService historicoService;

    public void transferir(TransferenciaDTO transferenciaDTO) throws ContaSemSaldoException {
        if (transferenciaDTO.getContaCreditoId() == transferenciaDTO.getContaDebitoId()) {
            throw new ContaTranferenciaIguaisException();

        }
        Conta origem = Optional.ofNullable(contaRepository.getById(transferenciaDTO.getContaDebitoId())).orElseThrow(ContaTransferenciaNaoEncontradaException::new);
        Conta destino = Optional.ofNullable(contaRepository.getById(transferenciaDTO.getContaCreditoId())).orElseThrow(ContaTransferenciaNaoEncontradaException::new);
       
        Transferencia transferencia = new Transferencia.Builder().from(origem).to(destino).tax(escolherTaxaTransferrencia(origem))
                .valor(transferenciaDTO.getValor().toMonetaryAmount()).build();
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
