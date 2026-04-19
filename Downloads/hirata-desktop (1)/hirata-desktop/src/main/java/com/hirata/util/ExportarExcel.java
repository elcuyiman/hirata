package com.hirata.util;

import com.hirata.model.Mantenimiento;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class ExportarExcel {

    public static void exportarMantenimientos(List<Mantenimiento> lista) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Mantenimientos");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Camión");
            header.createCell(2).setCellValue("Tipo");
            header.createCell(3).setCellValue("Descripción");
            header.createCell(4).setCellValue("Estado");

            int rowNum = 1;

            for (Mantenimiento m : lista) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(m.getId());
                row.createCell(1).setCellValue(m.getCamionId());
                row.createCell(2).setCellValue(m.getTipo());
                row.createCell(3).setCellValue(m.getDescripcion());
                row.createCell(4).setCellValue(m.getEstado());
            }

            FileOutputStream fileOut = new FileOutputStream("mantenimientos.xlsx");
            workbook.write(fileOut);
            fileOut.close();

            System.out.println("Excel generado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}