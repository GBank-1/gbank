package br.gbank.gbank.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.gbank.gbank.dto.TransferenciaDTO;
import br.gbank.gbank.exception.ContaSemSaldoException;
import br.gbank.gbank.model.Transferencia;
import br.gbank.gbank.model.entity.Cliente;
import br.gbank.gbank.model.entity.Conta;
import br.gbank.gbank.repository.ContaRepository;
import br.gbank.gbank.util.MonetaryUtil;

@ExtendWith(SpringExtension.class)
public class TransferenciaContaServiceTest {

    private TransferenciaDTO dto;

    private Conta contaOrigem;
    private Conta contaDestino;

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private HistoricoService historicoService;

    @Mock
    private Cliente clienteOrigem;

    @BeforeEach
    public void before() {
        contaOrigem = new Conta(231l);
        contaOrigem.setCliente(clienteOrigem);
        contaDestino = new Conta(232l);
        contaDestino.setCliente(new Cliente());

        dto = new TransferenciaDTO();
        dto.setNumeroContaOrigem(231l);
        dto.setNumeroContaDestino(232l);
        dto.setValor(BigDecimal.valueOf(10000l));

        doReturn(contaOrigem).when(contaRepository).getByNumero(231l);
        doReturn(contaDestino).when(contaRepository).getByNumero(232l);

    }

    @Test
    public void testTranferirEntreDuasContasRecemCadastradaComSucesso() throws ContaSemSaldoException {
        when(clienteOrigem.getDataCadastro()).thenReturn(LocalDate.now());
        transferenciaService.transferir(dto);
        Assertions.assertEquals(FastMoney.of(90000, MonetaryUtil.BRL), contaOrigem.getSaldo());
        Assertions.assertEquals(FastMoney.of(110000, MonetaryUtil.BRL), contaDestino.getSaldo());
        verify(contaRepository, times(2)).save(any(Conta.class));
        verify(historicoService, times(1)).registrar(any(Transferencia.class));
    }

    @Test
    public void testTranferirEntreDuasContasComContaOrigemHaMaisDe6MesesCadastradaComSucesso()
            throws ContaSemSaldoException {
        LocalDate dataAtras = LocalDate.now().minusMonths(7);
        when(clienteOrigem.getDataCadastro()).thenReturn(dataAtras);
        transferenciaService.transferir(dto);
        Assertions.assertEquals(FastMoney.of(89500, MonetaryUtil.BRL), contaOrigem.getSaldo());
        Assertions.assertEquals(FastMoney.of(110000, MonetaryUtil.BRL), contaDestino.getSaldo());
        verify(contaRepository, times(2)).save(any(Conta.class));
        verify(historicoService, times(1)).registrar(any(Transferencia.class));
    }

}
