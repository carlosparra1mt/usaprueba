/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ejemplo13.dao;

import com.example.ejemplo13.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Owner
 */
@Repository
public interface ProductoDao extends CrudRepository<Producto,Integer>{
    @Query(value="Select * from productos where precio>=? and precio<=?",nativeQuery=true)
    public List<Producto> buscaporPrecio(Double precio1,Double precio2);
    @Query(value="Select precio,count(*) as cantidad from productos group by precio order by cantidad desc",nativeQuery=true)
    public List<Object[]> contarProductosPorPrecio();
}
