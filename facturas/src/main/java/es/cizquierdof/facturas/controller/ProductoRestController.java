package es.cizquierdof.facturas.controller;

import org.springframework.web.bind.annotation.RestController;

import es.cizquierdof.facturas.model.Producto;
import es.cizquierdof.facturas.repositorio.ProductoRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ProductoRestController No debería haber vistas
 */

@RestController // anotamos el controlador como rest controller
@RequestMapping(value = "/api/productos") // endpoint base para operaciones rest
public class ProductoRestController {

    @Autowired
    ProductoRepository pr; // activamos el repositorio de productos

    /**************
     * 
     * Operacines GET
     */

    // Get todos los productos
    @GetMapping("/") // endpoint parra listado de productos en bruto
    public Iterable<Producto> getAll() {

        return pr.findAll(); // pide al repositorio que devuelva todos los elementos

    }

    // Get producto por id
    @GetMapping("/{id}/") // endpoint con una variable de path indicando el id del producto
    public Producto getPorId(

            @PathVariable("id") Long id // asignamos la variable de path a id

    ) {
        Optional<Producto> p = pr.findById(id);

        if (p.isEmpty()) {

            return null;
        }
        return p.get();

    }

    /***************
     * 
     * Operaciones POST
     * 
     */
    @PostMapping("/")
    public Producto crearProducto(@RequestBody Producto producto

    ) {

        return pr.save(producto);

    }

    /***********************************************
     * 
     * Operaciones DELETE
     */

    // DELETE un producto por id
    @DeleteMapping("/{id}/")
    public ResponseEntity deleteProducto(

            @PathVariable("id") Long id

    ) {
        try {

            pr.deleteById(id);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*********************************************
     * 
     * Operaciones PUT
     */

    @PutMapping("/{id}/")
    public Producto replaceProducto(@RequestBody Producto newProduct, @PathVariable("id") Long id) {

        Producto prod = pr.findById(id).get();

        prod.setDescripcion(newProduct.getDescripcion());
        prod.setFabricante(newProduct.getFabricante());
        prod.setPrecio(newProduct.getPrecio());

        return pr.save(prod);
    }
    /*
     * @PutMapping("/{id}/") Producto replaceProducto(
     * 
     * @RequestBody Producto newProduct,
     * 
     * @PathVariable Long id) {
     * 
     * return pr.findById(id).map(prod -> {
     * prod.setDescripcion(newProduct.getDescripcion());
     * prod.setFabricante(newProduct.getFabricante());
     * prod.setPrecio(newProduct.getPrecio());
     * 
     * return pr.save(prod); }) .orElseGet(() -> { newProduct.setId(id); return
     * pr.save(newProduct); }); }
     */

}