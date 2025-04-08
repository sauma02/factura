/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.controladores;

import ch.qos.logback.core.model.Model;
import com.example.facturacion.entidades.Producto;
import com.example.facturacion.servicios.ProductoServicio;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = {"http://localhost", "http://localhost:3306"})
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoServicio.listarProductos();
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> listarProducts(){
        List<Producto> lista = productoServicio.listarProductos();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/editar/{id}")
    public ResponseEntity<Producto> edProducto(@PathVariable Long id){
        Producto pro = productoServicio.listarPorId(id);
        return ResponseEntity.ok(pro);
    }
    
    @PostMapping("/editar")
    public ResponseEntity<Map<String, String>> editarProducto(@RequestBody Producto producto){
        Map<String, String> response = new HashMap<>();
        try {
            productoServicio.editarProducto(producto);
            response.put("clase", "success");
            response.put("mensaje", "Éxito al registrar");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("clase", "danger");
            response.put("mensaje", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, String>> registrarProducto(@RequestBody Producto producto) {
        Map<String, String> response = new HashMap<>();
        try {
            Producto pro = productoServicio.listarPorNombre(producto.getNombre());
            if (pro != null) {
                response.put("clase", "danger");
                response.put("mensaje", "Este producto ya se encuentra registrado");
                return ResponseEntity.badRequest().body(response);
            }
            productoServicio.guardarProducto(producto);
            response.put("clase", "success");
            response.put("mensaje", "Éxito al registrar");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("clase", "danger");
            response.put("mensaje", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto) {
        Producto productoGuardado = productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(productoGuardado);
    }

    @GetMapping("/eliminarProducto/{id}")
    public ResponseEntity<Map<String, String>> eliminarProducto(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Producto pro = productoServicio.listarPorId(id);
            productoServicio.eliminarProducto(pro);
            response.put("clase", "success");
            response.put("mensaje", "Producto eliminado con éxito");
        } catch (Exception e) {
            response.put("clase", "danger");
            response.put("mensaje", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Producto> listarPorCodigoDeBarras(@PathVariable String codigo) {
        return productoServicio.listarPorBarras(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
