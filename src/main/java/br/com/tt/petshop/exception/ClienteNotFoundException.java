package br.com.tt.petshop.exception;

//@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ClienteNotFoundException extends BusinessException {

    private final Long clientId;

    public ClienteNotFoundException(Long clientId) {
        super("Cliente não existe");
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }
}
