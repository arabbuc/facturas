package es.cizquierdof.facturas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cizquierdof.facturas.model.Producto;

/**
 * ProductoRepository
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByDescripcion(String descripcion);
  
}