package com.springcontrolcomedor.app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	@Id
	@Column(name = "id_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;

	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;
	// relacionar
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_producto")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoProducto tipoProducto;

	@Column(name = "presentacion")
	private String presentacion;

	@Column(name = "precio_compra", columnDefinition = "Decimal(10,2) default '0.00'")
	private Double precioCompra;

	@Column(name = "precio_venta", columnDefinition = "Decimal(10,2) default '0.00'")
	private Double precioVenta;

	@Column(name = "stock_minimo", columnDefinition = "int default '1'")
	private int stockMinimo;

	@Column(name = "stock_actual", columnDefinition = "int default '0'")
	private int stockActual;

	@Column(name = "alerta")
	private int alerta;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaRegistro;

	@Column(name = "eliminado")
	private int eliminado;

	public Producto() {

		tipoProducto = new TipoProducto();
		fechaRegistro = new Date();
		eliminado = 0;

	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public int getAlerta() {
		return alerta;
	}

	public void setAlerta(int alerta) {
		this.alerta = alerta;
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

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", descripcion=" + descripcion + ", tipoProducto=" + tipoProducto
				+ ", presentacion=" + presentacion + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta
				+ ", stockMinimo=" + stockMinimo + ", stockActual=" + stockActual + ", alerta=" + alerta
				+ ", fechaRegistro=" + fechaRegistro + ", eliminado=" + eliminado + "]";
	}

	private static final long serialVersionUID = 1L;

}
