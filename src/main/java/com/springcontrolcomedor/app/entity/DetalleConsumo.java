package com.springcontrolcomedor.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_consumo")
public class DetalleConsumo implements Serializable {

	@Id
	@Column(name = "id_detalle_consumo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalleConsumo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto")
	private Producto producto;

	@Column(name = "cantidad")
	private Integer cantidad;

	@Column(name = "precio")
	private Double precio;

	@Column(name = "sub_total")
	private Double subTotal;

	public Long getIdDetalleConsumo() {
		return idDetalleConsumo;
	}

	public void setIdDetalleConsumo(Long idDetalleConsumo) {
		this.idDetalleConsumo = idDetalleConsumo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public void calculaSubTotal() {
		this.setSubTotal(producto.getPrecioVenta() * cantidad.doubleValue());
	}

	private static final long serialVersionUID = 1L;

}
