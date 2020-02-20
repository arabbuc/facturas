package es.cizquierdof.facturas.controller;

/**
 * ProductNotFoundException
 */
public class ProductNotFoundException extends RuntimeException{

    ProductNotFoundException(Long id){
        super("No se encuentra el producto "+id);
    }
}