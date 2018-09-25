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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "consumo")
public class Consumo implements Serializable {

	@Id
	@Column(name = "id_consumo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConsumo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comensal")
	private Comensal comensal;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_local")
	private Local local;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(name = "fecha_consumo")
	private Date fechaConsumo;

	@Column(name = "monto_sin_igv", columnDefinition="Decimal(10,2) default '0.00'")
	private Double montoSinIgv;

	@Column(name = "monto_igv", columnDefinition="Decimal(10,2) default '0.00'")
	private Double montoIgv;

	@Column(name = "monto_total", columnDefinition="Decimal(10,2) default '0.00'")
	private Double montoTotal;

	@Column(name = "monto_cancelado", columnDefinition="Decimal(10,2) default '0.00'")
	private Double montoCancelado;

	@Column(name = "saldo", columnDefinition="Decimal(10,2) default '0.00'")
	private Double saldo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_consumo")
	private List<DetalleConsumo> detalleConsumo;

	@Column(name = "estado_saldo")
	private int estadoSaldo;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	@Column(name = "eliminado")
	private int eliminado;

	public Consumo() {
		detalleConsumo = new ArrayList<DetalleConsumo>();
	}

	@PrePersist
	public void prePersist() {
		fechaRegistro = new Date();
	}

	public Long getIdConsumo() {
		return idConsumo;
	}

	public void setIdConsumo(Long idConsumo) {
		this.idConsumo = idConsumo;
	}

	public Comensal getComensal() {
		return comensal;
	}

	public void setComensal(Comensal comensal) {
		this.comensal = comensal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Date getFechaConsumo() {
		return fechaConsumo;
	}

	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}

	public Double getMontoSinIgv() {
		return montoSinIgv;
	}

	public void setMontoSinIgv(double montoSinIgv) {
		this.montoSinIgv = montoSinIgv;
	}

	public Double getMontoIgv() {
		return montoIgv;
	}

	public void setMontoIgv(double montoIgv) {
		this.montoIgv = montoIgv;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Double getMontoCancelado() {
		return montoCancelado;
	}

	public void setMontoCancelado(double montoCancelado) {
		this.montoCancelado = montoCancelado;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public List<DetalleConsumo> getDetalleConsumo() {
		return detalleConsumo;
	}

	public void setDetalleConsumo(List<DetalleConsumo> detalleConsumo) {
		this.detalleConsumo = detalleConsumo;
	}

	public void addDetalleConsumo(DetalleConsumo detalleConsumo) {
		this.detalleConsumo.add(detalleConsumo);
	}

	public int getEstadoSaldo() {
		return estadoSaldo;
	}

	public void setEstadoSaldo(int estadoSaldo) {
		this.estadoSaldo = estadoSaldo;
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

	public void calculaMontos() {

		Double total = 0.0;

		for (DetalleConsumo itemConsumo : detalleConsumo) {
			total += itemConsumo.getSubTotal();
		}

		this.montoTotal = total;
		this.montoIgv = total * 0.18;
		this.montoSinIgv = total - (total * 0.18);

	}

	private static final long serialVersionUID = 1L;

}
