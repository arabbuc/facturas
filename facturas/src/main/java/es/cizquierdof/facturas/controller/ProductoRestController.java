package es.cizquierdof.facturas.controller;

import org.springframework.web.bind.annotation.RestController;

import es.cizquierdof.facturas.model.Producto;
import es.cizquierdof.facturas.repositorio.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * ProductoRestController
 */

@RestController
@RequestMapping(value="/productos")
public class ProductoRestController {

    @Autowired
    ProductoRepository pr;

    @GetMapping("/")
    public Iterable<Producto> getAllProductos(){
        return pr.findAll();
    }

    
}