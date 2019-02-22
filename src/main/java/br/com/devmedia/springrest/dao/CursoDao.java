package br.com.devmedia.springrest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.devmedia.springrest.domain.Curso;

@Repository
public class CursoDao {
	
	@PersistenceContext
	EntityManager manager;
	
	public void save(Curso curso) {
		manager.persist(curso);
	}
	
	public void update(Curso curso) {
		manager.merge(curso);
	}
	
	public void delete(Long id) {
		manager.remove(manager.getReference(Curso.class, id));
	}
	
	public Curso findById(Long id) {
		return manager.find(Curso.class, id);
	}
	
	public List<Curso> findAll(){
		return manager.createQuery("from Curso c", Curso.class).getResultList();
	}
	
}
