package br.com.devmedia.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.springrest.dao.VideoaulaDao;
import br.com.devmedia.springrest.domain.Videoaula;

@Service
@Transactional
public class VideoaulaService {
	
	@Autowired
	private VideoaulaDao aulaDao;
	@Autowired
	private CursoService cursoService;
	
	public void save(Long idCurso, Videoaula videoaula) {
		videoaula.setCurso(cursoService.findById(idCurso));
		aulaDao.salve(videoaula);
	}
	
	public void update(Long idVideoaula, Long idCurso, Videoaula videoaula) {
		videoaula.setId(idVideoaula);
		videoaula.setCurso(cursoService.findById(idCurso));
		aulaDao.update(videoaula);
	}
	
	public void delete(Long idVideoaula, Long idCurso) {
		aulaDao.delete(findByIdVideoaulaAndIdCurso(idVideoaula, idCurso));
	}
	
	@Transactional(readOnly=true)
	public Videoaula findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso) {
		return aulaDao.findByIdVideoaulaAndIdCurso(idVideoaula, idCurso);
	}
	
	@Transactional(readOnly=true)
	public List<Videoaula> findAllByCurso(Long idCurso, String fields){
		return aulaDao.findAllByCurso(idCurso, fields);
	}
	
}
