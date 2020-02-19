package es.cizquierdof.facturas.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.cizquierdof.facturas.model.Producto;

/**
 * ProductoRepository
 */
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    List<Producto> findByDescripcion(String descripcion);
  
}