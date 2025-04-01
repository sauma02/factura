/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.servicios;

import com.example.facturacion.entidades.Producto;
import com.example.facturacion.repositorios.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepository productoRepositorio;
    
    public List<Producto> listarProductos(){
        return productoRepositorio.findAll();
    }
    
    public Producto guardarProducto(Producto producto){
        return productoRepositorio.save(producto);
    }
}
