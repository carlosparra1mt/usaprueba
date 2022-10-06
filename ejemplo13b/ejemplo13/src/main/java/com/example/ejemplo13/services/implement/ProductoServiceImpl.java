/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.ejemplo13.services.implement;

import com.example.ejemplo13.dao.ProductoDao;
import com.example.ejemplo13.model.Producto;
import com.example.ejemplo13.services.ProductoService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Owner
 */
@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoDao productodao;
    //Transactional impide actualización o borrado de llave primaria
    //de tabla productos que aparezcan en otras tablas.
    @Override
    @Transactional
    public Producto save(Producto producto){
        return productodao.save(producto);
    }
    @Override
    @Transactional
    public void delete(Integer id){
        productodao.deleteById(id);
    }
    @Override
    public Producto findById(Integer id){
        return productodao.findById(id).orElse(null);
    }
    @Override
    public List<Producto> findAll(){
        return (List<Producto>) productodao.findAll();
    }
    @Override
    public List<Producto> getIntervaloPrecio(Double precio1,Double precio2) {
        return (List<Producto>) productodao.buscaporPrecio(precio1, precio2);
    }
    @Override
    public List<Object[]> getTopPrecio() {
        return (List<Object[]>) productodao.contarProductosPorPrecio();
    }
}
