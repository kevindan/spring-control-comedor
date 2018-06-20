package com.springcontrolcomedor.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(name = "nombres")
	@NotEmpty
	private String nombres;

	@Column(name = "apellido_paterno")
	@NotEmpty
	private String apellidoPaterno;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "usuario", unique = true)
	@NotEmpty
	private String usuario;

	@Column(name = "contrasena")
	@NotEmpty
	@NotNull
	private String contrasena;

	@Column(name = "email")
	@Email
	private String email;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	private List<Rol> roles;

	@Column(name = "fecha_registro")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date fechaRegistro;

	@Column(name = "habilitado")
	private int habilitado;

	@Column(name = "eliminado")
	private int eliminado;

	private static final long serialVersionUID = 1L;

}
