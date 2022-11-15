package kz.kdlolymp.gynecology.excelExport;

import kz.kdlolymp.gynecology.entity.RegionVisits;
import kz.kdlolymp.gynecology.entity.Visit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegionsExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<RegionVisits> rvList;
    private String startDate;
    private String endDate;

    public RegionsExcelExporter(List<RegionVisits> rvList, String startDate, String endDate) {
        this.rvList = rvList;
        this.startDate = startDate;
        this.endDate = endDate;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet = workbook.createSheet("RegionVisits");
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        Row row = sheet.createRow(0);
        createCell(row, 1, "Отчет по посещению портала по регионам", style);
    }
    private void writeTitleLine(){
        sheet.setDefaultColumnWidth(18);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        Row row = sheet.createRow(1);
        createCell(row, 1, "С даты:", style);
        createCell(row, 2, startDate, style);
        row = sheet.createRow(2);
        createCell(row, 1, "По дату:", style);
        createCell(row, 2, endDate, style);
        row = sheet.createRow(3);
        createCell(row, 0, "Регион", style);
        createCell(row, 1, "Количество", style);
    }

    private void writeDataLines() {
        sheet.setDefaultColumnWidth(18);
        int rowNumber = 4;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
        for (RegionVisits rv : rvList) {
            Row row = sheet.createRow(rowNumber);
            createCell(row, 0, rv.getRegionName(), style);
            createCell(row, 1, rv.getVisitsCount(), style);
            rowNumber++;
        }
    }
    private void createCell(Row row, int columnNumber, Object value, CellStyle style) {
        Cell cell = row.createCell(columnNumber);
        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        } else if(value instanceof Long){
            cell.setCellValue((Long) value);
        } else if(value instanceof Boolean){
            cell.setCellValue((Boolean)value);
        } else{
            cell.setCellValue((String)value);
        }
        cell.setCellStyle(style);
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeTitleLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
