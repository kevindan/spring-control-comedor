package com.springcontrolcomedor.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comensales")
public class Comensal implements Serializable {

	@Id
	@Column(name = "id_comensal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idComensal;

	@Column(name = "dni")
	@NotEmpty
	@NotNull
	private String dni;

	@Column(name = "nombres")
	@NotEmpty
	@NotNull
	private String nombres;

	@Column(name = "apellido_paterno")
	@NotNull
	@NotEmpty
	private String apellidoPaterno;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "email")
	@Email
	private String email;

	@OneToMany(mappedBy = "comensal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Consumo> consumos;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro")
	@NotNull
	private Date fechaRegistro;

	@Column(name = "eliminado")
	private int eliminado;

	public Comensal() {
		consumos = new ArrayList<Consumo>();
	}

	@PrePersist
	public void prePersist() {
		fechaRegistro = new Date();
	}

	public Long getIdComensal() {
		return idComensal;
	}

	public void setIdComensal(Long idComensal) {
		this.idComensal = idComensal;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Consumo> getConsumo() {
		return consumos;
	}

	public void setConsumo(List<Consumo> consumos) {
		this.consumos = consumos;
	}

	public void addConsumo(Consumo consumo) {
		consumos.add(consumo);
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getEliminado() {
		return eliminado;
	}

	public void setEliminado(int eliminado) {
		this.eliminado = eliminado;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	private static final long serialVersionUID = 1L;

}
