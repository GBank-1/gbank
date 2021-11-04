package br.gbank.gbank.service;

import br.gbank.gbank.dto.ContaDTO;
import br.gbank.gbank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Page<ContaDTO> getAll(Pageable pageable) {
        return contaRepository.findAll(pageable).map(ContaDTO::toContaDTO);
    }
}
