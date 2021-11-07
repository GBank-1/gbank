package br.gbank.gbank.exception;

public class MoedaNaoSuportadaException extends RuntimeException {
    public MoedaNaoSuportadaException() {
        super("Moeda não suportada");
    }
}
