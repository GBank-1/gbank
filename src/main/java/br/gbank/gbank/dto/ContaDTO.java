package br.gbank.gbank.dto;

import br.gbank.gbank.model.entity.Conta;


public class ContaDTO {
    private Long id;
    private Long numero;
    private ClienteDTO clienteDTO;
    private boolean ativa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public static ContaDTO fromConta(Conta conta) {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setId(conta.getId());
        contaDTO.setNumero(conta.getNumero());

        contaDTO.setClienteDTO(ClienteDTO.fromCliente(conta.getCliente()));
        contaDTO.setAtiva(conta.isAtiva());
        return contaDTO;
    }
}
