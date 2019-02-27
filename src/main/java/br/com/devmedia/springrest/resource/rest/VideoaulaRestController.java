package br.com.devmedia.springrest.resource.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.devmedia.springrest.domain.Videoaula;
import br.com.devmedia.springrest.service.VideoaulaService;

@RestController
@RequestMapping(value="/cursos/{idCurso}/videoaulas",
				produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
				consumes=MediaType.APPLICATION_JSON_UTF8_VALUE
				)
public class VideoaulaRestController {
	
	@Autowired
	private VideoaulaService service;
	
	@GetMapping("/{idVideoaula}")
	@ResponseStatus(HttpStatus.OK)
	public Videoaula getVideoaula(@PathVariable("idCurso") Long idCurso, @PathVariable("idVideoaula") Long idVideoaula) {
		return service.findByIdVideoaulaAndIdCurso(idVideoaula, idCurso);
	}
	
	@GetMapping
	@ResponseStatus(value=HttpStatus.OK)
	public List<Videoaula> listar(@PathVariable("idCurso") Long idCurso,@RequestParam(value="fields", required=false, defaultValue="") String fields){
		return service.findAllByCurso(idCurso, fields);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> salvar(@PathVariable("idCurso") Long idCurso, @RequestBody Videoaula videoaula){
		service.save(idCurso, videoaula);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(videoaula.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{idVideoaula}")
	@ResponseStatus(HttpStatus.OK)
	public Videoaula editar(@PathVariable("idCurso") Long idCurso, @PathVariable("idVideoaula") Long idVideoaula, @RequestBody Videoaula videoaula) {
		service.update(idVideoaula, idCurso, videoaula);
		return videoaula;
	}
	
	@DeleteMapping("{idVideoaula}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("idCurso") Long idCurso, @PathVariable("idVideoaula") Long idVideoaula) {
		service.delete(idVideoaula, idCurso);
	}
	
}
