/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.servicios;

import com.example.facturacion.entidades.Factura;
import com.example.facturacion.repositorios.FacturaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class FacturaServicio {
    
    @Autowired
    private FacturaRepository facturaRepositorio;
    
    
    public List<Factura> listaFactura(){
        return facturaRepositorio.findAll();
    }
    
    public Factura crearFactura(Factura factura) {
            return facturaRepositorio.save(factura);
    
    }
    public Factura listarPorId(Long id){
        Optional<Factura> res = facturaRepositorio.findById(id);
        if(res.isPresent()){
            Factura far = res.get();
            return far;
            
        }else{
            return null;
        }
    }
    
}
