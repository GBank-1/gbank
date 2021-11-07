package br.gbank.gbank.exception;

public class ContaSemSaldoException extends RuntimeException {
    public ContaSemSaldoException() {
        super("Conta de origem informada não possui saldo suficiente");
    }
}
