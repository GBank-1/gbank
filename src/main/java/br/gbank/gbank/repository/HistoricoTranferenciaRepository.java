package br.gbank.gbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gbank.gbank.model.entity.HistoricoTransferencia;

public interface HistoricoTranferenciaRepository extends JpaRepository<HistoricoTransferencia, Long> {

}
