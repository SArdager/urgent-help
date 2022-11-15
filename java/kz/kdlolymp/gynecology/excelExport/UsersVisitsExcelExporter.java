package kz.kdlolymp.gynecology.excelExport;

import kz.kdlolymp.gynecology.entity.UserVisits;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersVisitsExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<UserVisits> usersVisitsList;
    private String startDate;
    private String endDate;

    public UsersVisitsExcelExporter(List<UserVisits> usersVisitsList, String startDate, String endDate) {
        this.usersVisitsList = usersVisitsList;
        this.startDate = startDate;
        this.endDate = endDate;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet = workbook.createSheet("UsersVisits");
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        Row row = sheet.createRow(0);
        createCell(row, 1, "Отчет по посещению портала пользователями по регионам", style);
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
        createCell(row, 1, "Пользователь", style);
        createCell(row, 2, "Организация", style);
        createCell(row, 3, "Должность", style);
        createCell(row, 4, "Количество", style);
    }

    private void writeDataLines() {
        sheet.setDefaultColumnWidth(18);
        int rowNumber = 4;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
        for (UserVisits userVisits : usersVisitsList) {
            Row row = sheet.createRow(rowNumber);
            createCell(row, 0, userVisits.getUser().getRegionName(), style);
            createCell(row, 1, (userVisits.getUser().getUserSurname() + " " + userVisits.getUser().getUserFirstname()), style);
            createCell(row, 2, userVisits.getUser().getWorkPlace(), style);
            createCell(row, 3, userVisits.getUser().getPosition(), style);
            createCell(row, 4, userVisits.getTotal(), style);
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
