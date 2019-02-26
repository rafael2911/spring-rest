package br.com.devmedia.springrest.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(
		name="curso",
		indexes= { @Index(
				columnList = "titulo, data_inicio",
				unique = true,
				name = "unique_titulo_dataInicio")
		}
)
public class Curso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String titulo;
	
	@Column(name="carga_horaria", nullable=false)
	@Enumerated(EnumType.STRING)
	private CargaHoraria cargaHoraria;
	
	@Column(name="data_inicio")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@OneToMany(mappedBy="curso", cascade=CascadeType.ALL)
	@JsonIgnoreProperties({"curso"})
	private List<VideoAula> aulas;
		
	public Curso() {
		super();
	}
	public Curso(Long id, String titulo, CargaHoraria cargaHoraria, Date dataInicio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.cargaHoraria = cargaHoraria;
		this.dataInicio = dataInicio;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public CargaHoraria getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(CargaHoraria cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public List<VideoAula> getAulas() {
		return aulas;
	}
	public void setAulas(List<VideoAula> aulas) {
		this.aulas = aulas;
	}
	public void addVideoAula(VideoAula videoAula) {
		
		if(this.aulas == null) {
			this.aulas = new ArrayList<>();
		}
		
		videoAula.setCurso(this);
		
	}
	@Override
	public String toString() {
		return "Curso [id=" + id + ", titulo=" + titulo + ", cargaHoraria=" + cargaHoraria + ", dataInicio="
				+ dataInicio + "]";
	}
	
}
