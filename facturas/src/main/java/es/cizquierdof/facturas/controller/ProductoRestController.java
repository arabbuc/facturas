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

    /*******************
     * 
     * listado de productos como tabla llena una tabla html con los productos del
     * reposistorio
     */

    /*
     * @GetMapping("/all") // endpoint para mostrar todos los productos en una tabla
     * html public ModelAndView getAllProductosPage() {
     * 
     * ModelAndView modelAndView = new ModelAndView("new_producto"); // página que
     * contiene la tabla modelAndView.addObject("productos", pr.findAll()); //
     * añadimos todos los productos a la página
     * 
     * return modelAndView; // devolvemos la página al servidor tomcat }
     */
    /***
     * 
     * encuentra un solo producto basandose en el id el id viene como variable de
     * path
     */
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

     /*********************
     * 
     * versión que retorna un elemento o
     * 
     * @return
     */
/*
    @GetMapping("/p{id}/")
    public ModelAndView getProductohtml(@PathVariable("id") Long id

    ) {

        ModelAndView modelAndView = new ModelAndView();

        try {

            Producto producto = pr.findById(id).get();
            modelAndView.setViewName("single_product");
            modelAndView.addObject("producto", producto.toString());

        } catch (Exception e) {

            modelAndView.addObject("errormsg", e.getMessage());
            modelAndView.setViewName("404");

        }

        return modelAndView;

    } */

}