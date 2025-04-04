/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.controladores;

import com.example.facturacion.entidades.Producto;
import com.example.facturacion.servicios.ProductoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/productos")

public class ProductoController {
    @Autowired
    private ProductoServicio productoServicio;
    
    @GetMapping
    public List<Producto> listarProductos(){
        return productoServicio.listarProductos();
    }
    
    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto){
        Producto productoGuardado = productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(productoGuardado);
    }
}
