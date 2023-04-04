package br.edu.unoesc.funcionarios;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.unoesc.funcionarios.services.FuncionarioService;

@SpringBootApplication
public class SistemaFuncionariosApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SistemaFuncionariosApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

	
	@Bean
	CommandLineRunner commandLineRunner(FuncionarioService servico) {
		return args -> {
		
			servico.popularTabelaInicial();
//
//			// Exemplo de tratamento de exceções
//			try {
//				//System.out.println(10/0);
//				servico.excluir(2L);			
//			} catch (EmptyResultDataAccessException e) {
//				System.out.println("\n>>> Erro! Registro não encontrado! <<<\n");
//				
//			} catch (RuntimeException e) {
//				System.out.println("\n>>> Erro de execução! <<<\n" + e.getMessage());
//				
//			}
//			
//			// Exemplo utilização da classe Optional
//			Optional<Funcionario> p = servico.porId(10L);
//			if (p.isEmpty()) {
//				System.out.println("\n>>> Registro não encontrado! <<<\n");
//				
//			} else {
//				System.out.println(p);				
//				System.out.println(p.get());				
//				System.out.println(p.get().getNome());	
//				
//			}
//			
//			Funcionario a = servico.buscarPorId(50L);
//			a.setNome("Rodrigo");
//			a.setEndereco("Rua R.");
//			a.setNumDep(1);
//			a.setSalario(new BigDecimal("1400.00"));
//			a.setNascimento(LocalDate.of(2004, 5, 03));
//			
//			if (a.getId() == null) {
//				servico.incluir(a);
//				
//			} else {
//				servico.alterar(a.getId(), a);
//				
//			}
//			
//			// Recupera todos os registros
//			System.out.println(servico.listar());
//			
//			// Exemplos dos métodos de busca
//			for (var funcionario: servico.buscarPorNome("ano")) {
//				System.out.println(funcionario);
//				
//			}
//			
//			for (var funcionario: servico.buscarPorFaixaSalarial(new BigDecimal("500"),  new BigDecimal("1500"))) {
//				System.out.println(funcionario);
//				
//			}
//			
//			for (var funcionario: servico.buscarPossuiDependentes()) {
//				System.out.println(funcionario);
//				
//			}
//			
		};
	}
}
