package br.gbank.gbank.util;

public final class HttpStatusCode {

    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int NO_CONTENT = 204;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int REDIRECT_PERMANENT = 308;
    public static final int INTERNAL_ERROR = 500;
    public static final int FORBIDDEN = 300;
    public static final int UNAUTHORIZED = 401;

    public static final String DSC_OK = "Sucesso";
    public static final String DSC_CREATED = "Recurso criando com sucesso";
    public static final String DSC_NO_CONTENT = "Sem conteúdo";
    public static final String DSC_BAD_REQUEST = "Requisição inválida";
    public static final String DSC_NOT_FOUND = "Recurso não encontrado";
    public static final String DSC_REDIRECT_PERMANENT = "Permanentemente Redirecionado ";
    public static final String DSC_INTERNAL_ERROR = "Erro interno do Servidor";
    public static final String DSC_FORBIDDEN = "FORBIDDEN";
    public static final String DSC_UNAUTHORIZED = "Acesso não autorizado";  


    private HttpStatusCode() {
        //empty
    }

}
