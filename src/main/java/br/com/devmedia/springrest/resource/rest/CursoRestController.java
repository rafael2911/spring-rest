package br.com.devmedia.springrest.resource.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.devmedia.springrest.domain.Curso;
import br.com.devmedia.springrest.service.CursoService;

@RestController
@RequestMapping(
		value="/cursos",
		produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes=MediaType.APPLICATION_JSON_UTF8_VALUE
		)
public class CursoRestController {
	
	@Autowired
	private CursoService service;
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Curso curso) {
		service.save(curso);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(curso.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Curso editar(@PathVariable("id") Long id, @RequestBody Curso curso) {
		service.update(curso, id);
		return curso;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public Curso editaDataInicio(@PathVariable("id") Long id, @RequestBody Curso curso) {
		
		return service.updateDataInicio(id, curso.getDataInicio());
		
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Curso getCurso(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Curso> listar(@RequestParam(name="fields", required=false, defaultValue="") String fields){
		return (fields.equals("aulas")) ? service.findAll() : service.findAllSemAulas();
	}
	
}
