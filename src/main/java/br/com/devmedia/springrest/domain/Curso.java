package br.com.devmedia.springrest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@Override
	public String toString() {
		return "Curso [id=" + id + ", titulo=" + titulo + ", cargaHoraria=" + cargaHoraria + ", dataInicio="
				+ dataInicio + "]";
	}
	
}
