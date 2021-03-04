package br.info.felseje.evidence.utils;

import br.info.felseje.evidence.interfaces.TableWidth;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class PDFCreator {

    public static Document getDocument(String path) throws DocumentException, FileNotFoundException {
        return getDocument(PageSize.A4, 10f, 10f, 10f, 10f, path);
    }

    public static Document getDocument(Rectangle pageSize, float marginLeft, float marginRight, float marginTop, float marginBottom, String path) throws DocumentException, FileNotFoundException {
        Document doc = new Document(pageSize, marginLeft, marginRight, marginTop, marginBottom);
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        return doc;
    }

    public static PdfPTable getTable(TableWidth tableWidth) {
        return tableWidth.getWidth();
    }

    public static void insertTable(Document document, PdfPTable table) throws DocumentException {
        if (document.isOpen()) {
            document.add(table);
        } throw new DocumentException("Document needs be open to insert table.");
    }

    public static void insertTable(Document document, List<PdfPTable> tableList) throws DocumentException {
        for (PdfPTable table : tableList) {
            document.add(table);
        }
    }

    public static void insertCell(PdfPTable table, PdfPCell cell) {
        table.addCell(cell);
    }

    public static void insertCell(PdfPTable table, List<PdfPCell> cellList) {
        cellList.forEach(cell -> insertCell(table, cell));
    }

    public static void setCellHorizontalAlignment(PdfPCell cell, int alignment) {
        cell.setHorizontalAlignment(alignment);
    }

    public static void setCellHorizontalAlignment(List<PdfPCell> cellList, int alignment) {
        cellList.forEach(cell -> setCellHorizontalAlignment(cell, alignment));
    }

    public static void setCellVerticalAlignment(PdfPCell cell, int alignment) {
        cell.setVerticalAlignment(alignment);
    }

    public static void setCellVerticalAlignment(List<PdfPCell> cellList, int alignment) {
        cellList.forEach(cell -> setCellVerticalAlignment(cell, alignment));
    }

    public static void setCellBackgroundColor(PdfPCell cell, BaseColor color) {
        cell.setBackgroundColor(color);
    }

    public static void setCellBackgroundColor(List<PdfPCell> cellList, BaseColor color) {
        cellList.forEach(cell -> setCellBackgroundColor(cell, color));
    }

    public static void setParagraphAlignment(Paragraph paragraph, int alignment) {
        paragraph.setAlignment(alignment);
    }

    public static Font getFont() {
        return new Font();
    }

    public static Font getFont(float size) {
        return new Font(Font.FontFamily.UNDEFINED, size);
    }

    public static Phrase getPhrase(String text) {
        return new Phrase(text);
    }

    public static Phrase getPhrase(String text, Font font) {
        return new Phrase(text, font);
    }

    public static Paragraph getParagraph(String text) {
        return new Paragraph(text);
    }

    public static Paragraph getParagraph(String text, Font font) {
        return new Paragraph(text, font);
    }

    public static PdfPCell getCell(String text) {
        return new PdfPCell(getPhrase(text));
    }

    public static PdfPCell getCell(String text, Font font) {
        return new PdfPCell(getPhrase(text, font));
    }

    public static Image setImageAdjustment(Image image) {
        image.scaleToFit(550, 450);
        image.setAlignment(Image.ALIGN_CENTER);
        return image;
    }

    public static Paragraph getNewLine() {
        return new Paragraph(Chunk.NEWLINE);
    }
}
