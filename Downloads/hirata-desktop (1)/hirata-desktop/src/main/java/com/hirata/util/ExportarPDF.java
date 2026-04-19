package com.hirata.util;

import com.hirata.model.Mantenimiento;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.util.List;

public class ExportarPDF {

    public static void exportarMantenimientos(List<Mantenimiento> lista) {
        try {
            String ruta = "mantenimientos.pdf";

            PdfWriter writer = new PdfWriter(ruta);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("REPORTE DE MANTENIMIENTOS\n\n"));

            for (Mantenimiento m : lista) {
                document.add(new Paragraph(
                        "ID: " + m.getId() +
                        " | Camión: " + m.getCamionId() +
                        " | Tipo: " + m.getTipo() +
                        " | Estado: " + m.getEstado()
                ));
            }

            document.close();

            System.out.println("PDF generado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
