/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.servicios;

import com.example.facturacion.entidades.DetalleFactura;
import com.example.facturacion.entidades.Factura;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PdfGenerationService {
    
    public void exportFactura(HttpServletResponse response, Factura factura) throws IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph title = new Paragraph("Factura #" + factura.getId(), fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph("Total: "+ factura.getPrecio()));
        document.add(new Paragraph("Fecha: " + factura.getFecha().toString()));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 1.5f, 1.5f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, factura.getDetalles());

        document.add(table);
        document.close();
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cell.setPhrase(new Phrase("Producto", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Precio", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Subtotal", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, List<DetalleFactura> productos) {
        for (DetalleFactura p : productos) {
            table.addCell(p.getProducto().getNombre());
            table.addCell(String.valueOf(p.getCantidad()));
            table.addCell(String.valueOf(p.getSubtotal()));
            table.addCell(String.valueOf(p.getCantidad() * p.getSubtotal()));
        }
    }
    
}
