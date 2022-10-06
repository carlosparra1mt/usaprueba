/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ejemplo13.controller;

import com.example.ejemplo13.model.Producto;
import com.example.ejemplo13.services.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Owner
 */
//Rest: implementa en web operaciones GET, PUT, POST y DELETE
//Cross Origin -- permitir acceso cors
//Request mapping, dirección que aparece después de la url
@RestController
@CrossOrigin("*")
@RequestMapping("/producto")
public class ProductoController {
    //conectar con el service
    @Autowired
    private ProductoService productoservice;
    //Valor del mapping se denomina endpoint
    //ej de acceso: http://localhost/ejemplo13/producto/list
    //ej de acceso: http://localhost/ejemplo13/producto/list/444
    //Peticiones GET
    @GetMapping(value="/list")
    public List<Producto> consultartodo(){
        return productoservice.findAll();
    }
    @GetMapping(value="/list/{id}")
    public Producto consultaPorId(@PathVariable Integer id){
        return productoservice.findById(id);
    }
    //Peticiones POST para insertar
    @PostMapping(value="/")
    public ResponseEntity<Producto> agregar(@RequestBody Producto producto) {
        Producto prod;
        prod = productoservice.save(producto); //se guarda el producto
        return new ResponseEntity<> (prod,HttpStatus.OK); //se retorna resultado confirmación
    }
    //Peticiones PUT para actualizar
    @PutMapping(value="/")
    public ResponseEntity<Producto> actualizar(@RequestBody Producto producto){
        //Se busca el producto por el id
        Producto prod = productoservice.findById(producto.getId());
        //antes de actualizar, averiguar si lo encontró
        if (prod!=null) {
            //lo encontró, se asignan valores al objeto prod
            prod.setNombre(producto.getNombre());
            prod.setPrecio(producto.getPrecio());
            productoservice.save(prod);
        } else {
            //no lo encontró, indicar que hubo error
            return new ResponseEntity<> (prod,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<> (prod,HttpStatus.OK); //finalizado correctamente 
    }
    //Petición DELETE
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Producto> borrar(@PathVariable Integer id){
        //Buscamos producto por id
        Producto prod = productoservice.findById(id);
        //antes de borrar, averiguar si lo encontró
        if (prod!=null) {
            //Por si lo encontró
            productoservice.delete(id);
        } else {
            //No lo encontró
            return new ResponseEntity<> (prod,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<> (prod,HttpStatus.OK); //finalizado correctamente         
    }
    @GetMapping("/consultaprecio/{precio1}/{precio2}")
    public List<Producto> getProductoPrecios(@PathVariable("precio1") Double precio1,@PathVariable("precio2") Double precio2) {
        return productoservice.getIntervaloPrecio(precio1, precio2);
    }
    @GetMapping("/reporte")
    public List<Object[]> reporte() {
        return productoservice.getTopPrecio();
    }
}
