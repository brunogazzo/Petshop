package br.com.tt.petshop.exception;

public class ClienteNotFoundException extends BusinessException {
    public ClienteNotFoundException() {
        super("Cliente não existe");
    }
}
