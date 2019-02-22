package br.com.devmedia.springrest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.springrest.dao.CursoDao;
import br.com.devmedia.springrest.domain.Curso;

@Service
@Transactional
public class CursoService {
	
	@Autowired
	private CursoDao dao;
	
	public void save(Curso curso) {
		dao.save(curso);
	}
	
	public void update(Curso curso, Long id) {
		curso.setId(id);
		dao.update(curso);
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}
	
	public Curso findById(Long id) {
		return dao.findById(id);
	}
	
	public List<Curso> findAll(){
		return dao.findAll();
	}
	
	public Curso updateDataInicio(Long id, Date dataInicio) {
		Curso curso = dao.findById(id);
		curso.setDataInicio(dataInicio);
		return curso;
	}
	
}
