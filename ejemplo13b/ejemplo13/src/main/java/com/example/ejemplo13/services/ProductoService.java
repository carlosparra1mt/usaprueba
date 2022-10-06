/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ejemplo13.services;

import com.example.ejemplo13.model.Producto;
import java.util.List;

/**
 *
 * @author Owner
 */
public interface ProductoService {
    //Encabezado de métodos a implementar
    public Producto save(Producto producto);
    public void delete(Integer id);
    public Producto findById(Integer id);
    public List<Producto> findAll();
    public List<Producto> getIntervaloPrecio(Double precio1,Double precio2);
    public List<Object[]> getTopPrecio();
}
