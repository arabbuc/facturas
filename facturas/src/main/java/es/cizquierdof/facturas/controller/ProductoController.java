package es.cizquierdof.facturas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.cizquierdof.facturas.model.Producto;
import es.cizquierdof.facturas.repositorio.ProductoRepository;

/**
 * ProductoController
 * 
 * solo admite GET y POST
 */
@Controller
@RequestMapping("/")
public class ProductoController {

    @Autowired
    ProductoRepository productoRepository;



    @GetMapping("/producto")
    @ResponseBody
    public ModelAndView creaProducto() {

        ModelAndView modelAndView=new ModelAndView("new_producto");
        modelAndView.addObject("productos", productoRepository.findAll());

        Long total=productoRepository.count();
        modelAndView.addObject("mensaje", "Total artículos: "+String.valueOf(total));

        
        return modelAndView;
    }




    @PostMapping("/producto")
    public ModelAndView creaProductoPost(
        @RequestParam("descripcion") String descripcion,
        @RequestParam("fabricante") String fabricante,
        @RequestParam("precio") Float precio
    ) {
        ModelAndView mdl=new ModelAndView();
        try{
            
        mdl.setViewName("new_producto");
        Producto producto=new Producto(descripcion, fabricante, precio);
        productoRepository.save(producto);
        mdl.addObject("productos", productoRepository.findAll());

        //añadimos el contador de elementos
        Long total=productoRepository.count();
        mdl.addObject("mensaje", "Total artículos: "+String.valueOf(total));
        
        return mdl;
        }
        catch(Exception e){
            mdl.setViewName("404");
            mdl.addObject("errormsg", e.getMessage());
            return mdl;
        }
    }



}