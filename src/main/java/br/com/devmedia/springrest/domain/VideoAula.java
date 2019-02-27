package br.com.devmedia.springrest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="videoaulas")
public class Videoaula {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=60)
	private String titulo;
	
	@Column(nullable=false)
	private String descricao;
	
	@Column(nullable=false)
	private int numero;
	
	@ManyToOne
	@JoinColumn(name="id_curso_fk")
	@JsonIgnoreProperties({"aulas", "cargaHoraria", "dataInicio"})
	@JsonInclude(value=Include.NON_NULL)
	private Curso curso;

	public Videoaula() {
		super();
	}

	public Videoaula(Long id, String titulo, String descricao, int numero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.numero = numero;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "VideoAula [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", numero=" + numero
				+ ", curso=" + curso + "]";
	}
	
}
