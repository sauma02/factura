/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.controladores;

import com.example.facturacion.entidades.DetalleFactura;
import com.example.facturacion.entidades.DetalleFacturaDTO;
import com.example.facturacion.entidades.Factura;
import com.example.facturacion.entidades.Producto;
import com.example.facturacion.servicios.ExcepExportService;
import com.example.facturacion.servicios.FacturaServicio;
import com.example.facturacion.servicios.PdfGenerationService;
import com.example.facturacion.servicios.ProductoServicio;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/factura")
@CrossOrigin(origins = {"http://localhost", "http://localhost:3306"})
public class FacturaController {

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private FacturaServicio facturaServicio;
    @Autowired
    private PdfGenerationService pdfGeneratorService;
    @Autowired
    private ExcepExportService excelExporterService;

    @GetMapping("/factura/pdf/{id}")
    public void exportarFacturaPdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerValue = "attachment; filename=factura_" + id + ".pdf";
        response.setHeader("Content-Disposition", headerValue);

        Factura factura = facturaServicio.listarPorId(id);
        pdfGeneratorService.exportFactura(response, factura);
    }

    @GetMapping("/facturas/excel")
    public void exportFacturasExcel(HttpServletResponse response) throws IOException {
        List<Factura> facturas = facturaServicio.listaFactura();
        excelExporterService.exportFacturasToExcel(response, facturas);
    }

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody List<DetalleFacturaDTO> detalles) {
        Factura factura = new Factura();
        factura.setFecha(LocalDate.now());
        Double total = 0.0;

        for (DetalleFacturaDTO dto : detalles) {
            Optional<Producto> optProducto = productoServicio.buscarPrimeroPorId(dto.getProductoId());
            if (optProducto.isEmpty()) {
                continue;
            }

            Producto producto = optProducto.get();
            if (producto.getStock() < dto.getCantidad()) {
                continue;
            }
            DetalleFactura detalle = new DetalleFactura();
            detalle.setProducto(producto);
            detalle.setCantidad(dto.getCantidad());
            double subtotal = producto.getPrecio() * dto.getCantidad();
            detalle.setSubtotal(subtotal);
            detalle.setFactura(factura);

            producto.setStock(producto.getStock() - dto.getCantidad());
            factura.getDetalles().add(detalle);
            total += subtotal;

        }
        factura.setTotal(total);
        facturaServicio.crearFactura(factura);
        return ResponseEntity.ok(factura);
    }
}
