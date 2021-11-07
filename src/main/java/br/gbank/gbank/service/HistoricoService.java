package br.gbank.gbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.gbank.gbank.model.Transferencia;
import br.gbank.gbank.model.entity.HistoricoTransferencia;
import br.gbank.gbank.repository.HistoricoTranferenciaRepository;

@Service
public class HistoricoService {   

    @Autowired
    private HistoricoTranferenciaRepository historicoTranferenciaRepositorio;

    public void registrar(Transferencia transferencia) {
        HistoricoTransferencia entity = new HistoricoTransferencia(transferencia);
        historicoTranferenciaRepositorio.save(entity);
    }
    
}
