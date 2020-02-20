package es.cizquierdof.facturas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Producto
 */
@Entity         //anotamos la clase como entidad lo que la conecta con una tabla del mismo nombre
public class Producto {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=1)    //no se admiten cadena vacía
    private String descripcion;

    @NotNull
    @Size(min=1)
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

    @Override
    public String toString() {
        return "Producto [descripcion=" + descripcion + ", fabricante=" + fabricante + ", id=" + id + ", precio="
                + precio + "]";
    }



    
}