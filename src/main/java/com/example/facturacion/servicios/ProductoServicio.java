/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.servicios;

import com.example.facturacion.entidades.Producto;
import com.example.facturacion.repositorios.ProductoRepository;
import java.util.List;
import java.util.Optional;
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
    public void eliminarProducto(Producto producto){
        productoRepositorio.delete(producto);
    }
    public Producto editarProducto(Producto producto){
        Optional<Producto> res = productoRepositorio.findFirstById(producto.getId());
        if(res.isPresent()){
            Producto proViejo = res.get();
            proViejo.setCategoria(producto.getCategoria());
            proViejo.setDescripcion(producto.getDescripcion());
            proViejo.setNombre(producto.getNombre());
            proViejo.setPrecio(producto.getPrecio());
            proViejo.setStock(producto.getStock());
            productoRepositorio.save(proViejo);
            return proViejo;
        }else{
            return null;
        }
    }
    
    public Producto listarPorNombre(String nombre){
        Optional<Producto> res = productoRepositorio.findFirstByNombre(nombre);
        if(res.isPresent()){
            Producto pro = res.get();
            return pro;
        }else{
            return null;
    }
    }
    public List<Producto> listarProductos(){
        return productoRepositorio.findAll();
    }
    public Producto listarPorId(Long id){
        Optional<Producto> res = productoRepositorio.findById(id);
        if(res.isPresent()){
            Producto pro = res.get();
            return pro;
        }else{
            return null;
        }
    }
    public Optional<Producto> buscarPrimeroPorId(Long id){
        return productoRepositorio.findFirstById(id);
    }
    public Producto guardarProducto(Producto producto){
        return productoRepositorio.save(producto);
    }
    public Optional<Producto> listarPorBarras(String codigo){
        return productoRepositorio.findFirstByCodigoBarras(codigo);
    }
}
