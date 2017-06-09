package com.ic.analisaSusy.commons;

/**
 *
 * @author Marcelo C. Araújo
 */
public enum ApplicationError {
    NF_INPUT("O arquivo de entrada %s não foi encontrado."),
    NF_CODE("O arquivo de código %s não foi encontrado."),
    NF_CCSM("A ferramenta CCSM não foi encontrada no PATH do ambiente de execução."),
    NF_FILE_ARG("O argumento -f (arquivo de entrada) não foi informado."),
    INVALID_EXT("O arquivo de código %s não pertence a linguagem C."),
    NO_VALID_FILES("Não foram informados arquivos da linguagem C"),
    NOT_FOUND_FILES("Não foram encontrados arquivos de código");

    private final String errorMessage;

    private ApplicationError(final String anErrorMessage) {
        this.errorMessage = anErrorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
