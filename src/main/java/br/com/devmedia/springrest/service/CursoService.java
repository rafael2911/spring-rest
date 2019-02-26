package br.com.devmedia.springrest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.springrest.dao.CursoDao;
import br.com.devmedia.springrest.domain.Curso;
import br.com.devmedia.springrest.exceprion.IdNaoValidoServiceException;

@Service
@Transactional
public class CursoService {
	
	@Autowired
	private CursoDao dao;
	
	public void save(Curso curso) {
		dao.save(curso);
		
		if(curso.getAulas() != null) {
			curso.getAulas()
				.parallelStream()
				.forEach(curso::addVideoAula);
		}
		
		curso.getAulas().forEach(aula -> System.out.println(aula));
	}
	
	public void update(Curso curso, Long id) {
		dao.findById(idValido(id));
		curso.setId(idValido(id));
		dao.update(curso);
	}
	
	public void delete(Long id) {
		dao.delete(idValido(id));
	}
	
	public Curso findById(Long id) {
		return dao.findById(idValido(id));
	}
	
	public List<Curso> findAll(){
		return dao.findAll();
	}
	
	public List<Curso> findAllSemAulas(){
		return dao.findAllSemAulas();
	}
	
	public Curso updateDataInicio(Long id, Date dataInicio) {
		Curso curso = dao.findById(idValido(id));
		curso.setDataInicio(dataInicio);
		return curso;
	}
	
	private Long idValido(Long id) {
		if(id<=0) {
			throw new IdNaoValidoServiceException("Valor do campo id está invalido. Deve ser uma valor inteiro maior que zero!");
		}
		
		return id;
	}
	
}
