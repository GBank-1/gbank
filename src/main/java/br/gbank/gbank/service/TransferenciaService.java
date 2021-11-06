package br.gbank.gbank.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gbank.gbank.dto.TransferenciaDTO;
import br.gbank.gbank.exception.ContaSemSaldoException;
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
		Conta origem = contaRepository.getByNumero(transferenciaDTO.getNumeroContaOrigem());
        Conta destino = contaRepository.getByNumero(transferenciaDTO.getNumeroContaDestino());

        LocalDate dtCadastro = origem.getDataCadastrada();
        TaxaTransferencia taxa = new TaxaNormalTransferencia();
        if(verificarSeClienteEstaNaFaixa6mesesTaxaEspecial(dtCadastro)) {
            taxa = new TaxaEspecialTransferencia();
        }
        Transferencia transferencia = new Transferencia.Builder()
        .from(origem).to(destino)
        .tax(taxa).valor(transferenciaDTO.getValor()).build();
        transferencia.processar();
        contaRepository.save(origem);
        contaRepository.save(destino);
        historicoService.registrar(transferencia);

	}


    private boolean verificarSeClienteEstaNaFaixa6mesesTaxaEspecial(LocalDate dtCadastro) {
        return LocalDate.now().equals(dtCadastro)|| (LocalDate.now().isAfter(dtCadastro) && LocalDate.now().isBefore(dtCadastro.plusMonths(6)));
    }
    
}
