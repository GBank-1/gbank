package br.gbank.gbank.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Boolean fGenero, String table, String column, String value) {
        super(fGenero ? "Nenhuma " + table + " encontrado com o " + column + " igual a " + value
                : "Nenhum " + table + " encontrado com o " + column + " igual a " + value);
    }
}
