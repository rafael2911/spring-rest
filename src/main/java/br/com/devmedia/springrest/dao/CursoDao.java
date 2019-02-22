package br.com.devmedia.springrest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.devmedia.springrest.domain.Curso;
import br.com.devmedia.springrest.exceprion.NaoExisteDaoException;

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
		try {
			manager.remove(manager.getReference(Curso.class, id));
		}catch (EntityNotFoundException ex) {
			throw new NaoExisteDaoException("Curso não encontrado para o id: " + id + "!");
		}
		
	}
	
	public Curso findById(Long id) {
		Curso curso = manager.find(Curso.class, id);
		
		if(curso == null) {
			throw new NaoExisteDaoException("Curso não encontrado para o id: " + id + "!");
		}
		
		return curso;
	}
	
	public List<Curso> findAll(){
		return manager.createQuery("from Curso c", Curso.class).getResultList();
	}
	
}
