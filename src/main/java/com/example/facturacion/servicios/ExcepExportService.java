/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.servicios;

import com.example.facturacion.entidades.Factura;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ExcepExportService {
     public void exportFacturasToExcel(HttpServletResponse response, List<Factura> facturas) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Facturas");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        
        header.createCell(2).setCellValue("Fecha");
        header.createCell(3).setCellValue("Total");

        int rowCount = 1;

        for (Factura f : facturas) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(f.getId());
            
            row.createCell(2).setCellValue(f.getFecha().toString());
            row.createCell(3).setCellValue(f.getTotal());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=facturas.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
