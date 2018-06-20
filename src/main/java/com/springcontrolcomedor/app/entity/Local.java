package com.springcontrolcomedor.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "locales")
public class Local implements Serializable {

	@Id
	@Column(name = "id_local")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLocal;

	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;

	@Column(name = "eliminado")
	private int eliminado;

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEliminado() {
		return eliminado;
	}

	public void setEliminado(int eliminado) {
		this.eliminado = eliminado;
	}

	private static final long serialVersionUID = 1L;

}
