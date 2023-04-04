package br.edu.unoesc.funcionarios.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.unoesc.funcionarios.model.Funcionario;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class FuncionarioDTO implements Serializable {
	
	Long id;
	
	@NotBlank(message = "É obrigatório informar um nome")
	@Size(min = 1, max = 55, message = "O nome do funcionário deve ter entre {min} e {max} caracteres")
	String nome;
	
	@NotNull(message = "É obrigatório informar um endereço")
	@Size(min = 1, max = 150, message = "O endereço deve ter entre {min} e {max} caracteres")
	String endereco;
	
	@Min(value = 0, message = "Nº Dependentes não pode ser menor que {value}")
	@Max(value = 100, message = "Nº Dependentes não pode ser maior que {value}")
	Integer numDep;
	
	@Min(value = 0, message = "Salário não pode ser menor que {value}")
	@Max(value = 99999, message = "Salário não pode ser maior que {value}")
	BigDecimal salario;
	
	LocalDate nascimento;
	
	public FuncionarioDTO(Funcionario funcionario) {
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.endereco = funcionario.getEndereco();
		this.numDep = funcionario.getNumDep();
		this.salario = funcionario.getSalario();
		this.nascimento = funcionario.getNascimento();
	}
}