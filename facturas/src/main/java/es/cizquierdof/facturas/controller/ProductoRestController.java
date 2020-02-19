package es.cizquierdof.facturas.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.cizquierdof.facturas.model.Producto;
import es.cizquierdof.facturas.repositorio.ProductoRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ProductoRestController
 */

@RestController     //anotamos el controlador como rest controller
@RequestMapping(value = "/productos")   //endpoint base
public class ProductoRestController {

    @Autowired
    ProductoRepository pr;      //activamos el repositorio de productos

    /**************
     * 
     * listado de productos en bruto
     * los devuelve como JSON
     */
    @GetMapping("/")        //endpoint parra listado de productos en bruto
    public Iterable<Producto> getAllProductos() {
        return pr.findAll();    //pide al repositorio que devuelva todos los elementos
    }

    /*******************
     * 
     * listado de productos como tabla
     * llena una tabla html con los productos del reposistorio
     */
    @GetMapping("/all")     //endpoint para mostrar todos los productos en una tabla html
    public ModelAndView getAllProductosPage() {

        ModelAndView modelAndView=new ModelAndView("new_producto"); //página que contiene la tabla
        modelAndView.addObject("productos", pr.findAll());      //añadimos todos los productos a la página

        return modelAndView;    //devolvemois la página al servidor tomcat
    }

    /***
     * 
     * encuentra un solo producto basandose en el id
     * el id viene como variable de path
     */
    @GetMapping("/{id}")        //endpoint con una variable de path indicando el id del producto
    public Optional<Producto> getProducto(
        @PathVariable("id") Long id     //asignamos la variable de path a id
    ){

            return pr.findById(id); //devolvemos un solo producto con id igual a la variable de path

    }

    
}