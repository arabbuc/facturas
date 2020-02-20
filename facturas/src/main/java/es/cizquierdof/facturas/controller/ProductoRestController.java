package es.cizquierdof.facturas.controller;

import org.springframework.web.bind.annotation.RestController;

import es.cizquierdof.facturas.model.Producto;
import es.cizquierdof.facturas.repositorio.ProductoRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ProductoRestController
 * No debería haber vistas
 */

@RestController // anotamos el controlador como rest controller
@RequestMapping(value = "/api/productos") // endpoint base
public class ProductoRestController {

    @Autowired
    ProductoRepository pr; // activamos el repositorio de productos

    /**************
     * 
     * listado de productos en bruto los devuelve como JSON
     */
    @GetMapping("/") // endpoint parra listado de productos en bruto
    public Iterable<Producto> getAllProductos() {
        return pr.findAll(); // pide al repositorio que devuelva todos los elementos
    }

    @GetMapping("/{id}/") // endpoint con una variable de path indicando el id del producto
    public Optional<Producto> getProducto(@PathVariable("id") Long id // asignamos la variable de path a id
    ) {

        try {
            return pr.findById(id); // devolvemos un solo producto con id igual a la variable de path
        } catch (NoSuchElementException e) {

            return null;
        }

    }

    @PostMapping("/")
    public Producto crearProducto(

            @RequestBody Producto producto
            
            ) {

        Producto p = pr.save(producto);
        return p;

    }

}