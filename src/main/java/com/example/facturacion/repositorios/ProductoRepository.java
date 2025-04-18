/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.facturacion.repositorios;

import com.example.facturacion.entidades.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
   public Optional<Producto> findFirstByCodigoBarras(String codigo);
   public Optional<Producto> findFirstByNombre(String nombre);
   public Optional<Producto> findFirstById(Long id);
    
}
