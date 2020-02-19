package es.cizquierdof.facturas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Producto
 */
@Entity 
public class Producto {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String descripcion;

    @NotNull
    private String fabricante;

    private Float precio;

    /***************
     * Getters, Setters, Constructor y toString
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Producto(@NotNull String descripcion, @NotNull String fabricante, Float precio) {
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.precio = precio;
    }

    public Producto() {
    }



    
}