package br.gbank.gbank.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.gbank.gbank.model.entity.Cliente;
import br.gbank.gbank.model.entity.Conta;
import br.gbank.gbank.model.entity.DadosPessoais;
import br.gbank.gbank.repository.ClienteRepository;
import br.gbank.gbank.repository.ContaRepository;
import lombok.Builder;

/**
 * 
 * Classe para população inicial do banco de dados para teste
 * 
 * @author pedro.neri
 *
 */
@Configuration
@Profile("test")
@Builder
public class LoadDataBaseTest implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public void run(String... args) throws Exception {

		Cliente vader = new Cliente();
		DadosPessoais dadosPessoaisDoVader = new DadosPessoais("Darth Vader", "11190887653", "vader@email.com",
				"+123 24 11111111");
		Cliente mario = new Cliente();
		DadosPessoais dadosPessoaisDoMario = new DadosPessoais("Mario Bros", "11190882353", "mario@email.com",
				"+123 24 222222222");
		Cliente ada = new Cliente();
		DadosPessoais dadosPessoaisDaAda = new DadosPessoais("Ada Lovelace", "11190887d53", "ada@email.com",
				"+123 24 11111111");

		vader.setDadosPessoais(dadosPessoaisDoVader);
		mario.setDadosPessoais(dadosPessoaisDoMario);
		ada.setDadosPessoais(dadosPessoaisDaAda);


		clienteRepository.saveAll(Arrays.asList(vader, mario, ada));

		Conta cvader = new Conta(vader);
		Conta cmario = new Conta(mario);
		Conta cada = new Conta(ada);

		contaRepository.saveAll(Arrays.asList(cvader, cmario, cada));


	}

}
