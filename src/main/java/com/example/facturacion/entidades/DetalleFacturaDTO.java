/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facturacion.entidades;

import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
public class DetalleFacturaDTO {
    private Long productoId;
    private Integer cantidad;
}
