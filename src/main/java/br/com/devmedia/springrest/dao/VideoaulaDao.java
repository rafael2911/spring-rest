package br.com.devmedia.springrest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.devmedia.springrest.domain.Videoaula;
import br.com.devmedia.springrest.exceprion.NaoExisteDaoException;

@Repository
public class VideoaulaDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salve(Videoaula videoaula) {
		manager.persist(videoaula);
	}
	
	public void update(Videoaula videoaula) {
		manager.merge(videoaula);
	}
	
	public void delete(Videoaula videoaula) {
		manager.remove(videoaula);
	}
	
	public Videoaula findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso) {
		String query = "from Videoaula v where v.id = ?1 and v.curso.id = ?2";
		try {
			return manager.createQuery(query, Videoaula.class)
				.setParameter(1, idVideoaula)
				.setParameter(2, idCurso)
				.getSingleResult();
		} catch (NoResultException ex) {
			throw new NaoExisteDaoException("Videoaula id = "+ idVideoaula +
                    " não encontrada para Curso id = " + idCurso + ".");
		}
		
	}
	
	public List<Videoaula> findAllByCurso(Long idCurso, String fields){
		String query = (fields.equals("curso") ? "select v" : "select new Videoaula(v.id, v.titulo, v.descricao, v.numero)");
		
		return manager.createQuery(query + " from Videoaula v where v.curso.id = ?1", Videoaula.class)
				.setParameter(1, idCurso)
				.getResultList();
	}
}
