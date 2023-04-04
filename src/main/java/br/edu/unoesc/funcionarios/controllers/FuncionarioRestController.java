package br.edu.unoesc.funcionarios.controllers;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.unoesc.funcionarios.dto.FuncionarioDTO;
import br.edu.unoesc.funcionarios.model.Funcionario;
import br.edu.unoesc.funcionarios.services.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "API REST de Funcionários")
@RequestMapping("/api/funcionarios")
@ApiResponses(value =  {
		@ApiResponse(code = 200, message = "Operação realizada com sucesso"),
		@ApiResponse(code = 401, message = "Não Autorizado"),
		@ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
		@ApiResponse(code = 404, message = "Funcionário não encontrado"),
		@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
})
public class FuncionarioRestController {
	
	@Autowired
	private FuncionarioService servico;
	
	@ApiOperation(value = "Retorna todos os funcionários")
	@GetMapping
	public ResponseEntity<Iterable<Funcionario>> listar() {
		List<Funcionario> funcionarios = servico.listar();
		
		if (funcionarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(funcionarios);
	}

	@ApiOperation(value = "Retorna todo o conteúdo das páginas")
	@GetMapping("/listar-paginas")
	public ResponseEntity<Page<FuncionarioDTO>> listarPaginado(Pageable pagina) {
		return ResponseEntity.ok(servico.listarPaginado(pagina));
	}
	
	@ApiOperation(value = "Retorna todo o conteúdo de uma página, com base nos parâmetros")
	@GetMapping("/paginas")
	public ResponseEntity<Page<Funcionario>> listarPaginas(
			@RequestParam(value="nome", defaultValue = "")String nome,
			@RequestParam(value="pagina", defaultValue = "0")Integer pagina,
			@RequestParam(value="tamPagina", defaultValue = "24")Integer tamPagina,
			@RequestParam(value="ordenacao", defaultValue = "nome")String campo,
			@RequestParam(value="direcao", defaultValue = "ASC")String direcao){
		return ResponseEntity.ok(servico.buscaPaginadaPorNome(nome, pagina, tamPagina, campo, direcao));
	}
	
	
	@ApiOperation(value = "Retorna um único funcionário através de seu 'id'")
	@RequestMapping(value = "/{id}",
				    method = RequestMethod.GET,
				    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Funcionario> porId(@PathVariable Long id) {
		Optional<Funcionario> funcionario = servico.porId(id);
		
		if (funcionario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(funcionario.get());
	}
	
//	headers = "Accept=application/xml"
	@ApiOperation(value = "Retorna em formato XML, todos os funcionários através de seu 'id'")
	@RequestMapping(value = "/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Funcionario> porIdXML(@PathVariable Long id) {
		Funcionario funcionario = servico.buscar(id);
		
		return ResponseEntity.ok(funcionario);
	}
	
	@ApiOperation(value = "Retorna uma lista de funcionários que contenham no nome, uma parte da string (case sensitive) buscada")
	@GetMapping("/buscar")
	public ResponseEntity<List<Funcionario>> porNome(@RequestParam("nome") String nome) {
		List<Funcionario> funcionarios = servico.buscarPorNome(nome);
		
		if (funcionarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(funcionarios); 
	}
	
	@ApiOperation(value = "Retorna uma lista de funcionários que estejam dentro da faixa salarial informada")
	@GetMapping(value = "/faixa-salarial")
	public ResponseEntity<List<Funcionario>> porFaixaSalarial(
			@RequestParam("minimo") Optional<BigDecimal> minimo,
			@RequestParam("maximo") Optional<BigDecimal> maximo) {
		
		List<Funcionario> funcionarios = servico.buscarPorFaixaSalarial(
													minimo.orElse(BigDecimal.ZERO),
													maximo.orElse(new BigDecimal("9999999999")));
		
		if (funcionarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(funcionarios); 
	}
	
	@ApiOperation(value = "Retorna uma lista de funcionários que possuem dependentes")
	@GetMapping(value = "/dependentes")
	public ResponseEntity<List<Funcionario>> possuiDependentes() {
		List<Funcionario> funcionarios = servico.buscarPossuiDependentes();
		
		if (funcionarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(funcionarios); 
	}
	
	@ApiOperation(value = "Inclui um funcionário")
    @PostMapping()
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> incluir(@RequestBody Funcionario funcionario) {
    	funcionario = servico.incluir(funcionario);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        									 .path("/{id}")
        									 .buildAndExpand(funcionario.getId())
        									 .toUri();
        
        System.out.println(uri.toString());
        
        return ResponseEntity.created(uri).build();
    }
    
	@ApiOperation(value = "Inclui um funcionário utilizando formato XML")
    @PostMapping(value = "/xml", 
    		//consumes = MediaType.APPLICATION_XML_VALUE,
    		produces = MediaType.APPLICATION_XML_VALUE)
    public Funcionario incluirXML(@RequestBody Funcionario funcionario) {
        return servico.incluir(funcionario);
    }
     
	@ApiOperation(value = "Atualizar os dados de um funcionário")
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(
    		@PathVariable("id") Long id,
    		@RequestBody Funcionario funcionario) {
    	
    	if (servico.porId(id).isEmpty()) {
    		return ResponseEntity.notFound().build();
    	}
    	
        return ResponseEntity.ok(servico.alterar(id, funcionario));
    }
 
	@ApiOperation(value = "Exclui um funcionário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
    	try {
    		servico.excluir(id);   		
    	} catch (ObjectNotFoundException e) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	return ResponseEntity.noContent().build();
    }
}