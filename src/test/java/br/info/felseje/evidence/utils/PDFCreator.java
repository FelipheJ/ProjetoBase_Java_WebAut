package br.info.felseje.evidence.utils;

import java.util.List;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.info.felseje.evidence.interfaces.TableWidth;

/**
 * Class that contains methods for creating the document.
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
        } else throw new DocumentException("Document needs be open to insert table.");
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

    public static PdfPCell setCellHorizontalAlignment(PdfPCell cell, int alignment) {
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    public static void setCellHorizontalAlignment(List<PdfPCell> cellList, int alignment) {
        cellList.forEach(cell -> setCellHorizontalAlignment(cell, alignment));
    }

    public static PdfPCell setCellVerticalAlignment(PdfPCell cell, int alignment) {
        cell.setVerticalAlignment(alignment);
        return cell;
    }

    public static void setCellVerticalAlignment(List<PdfPCell> cellList, int alignment) {
        cellList.forEach(cell -> setCellVerticalAlignment(cell, alignment));
    }

    public static PdfPCell setCellBackgroundColor(PdfPCell cell, BaseColor color) {
        cell.setBackgroundColor(color);
        return cell;
    }

    public static void setCellBackgroundColor(List<PdfPCell> cellList, BaseColor color) {
        cellList.forEach(cell -> setCellBackgroundColor(cell, color));
    }

    public static Paragraph setParagraphAlignment(Paragraph paragraph, int alignment) {
        paragraph.setAlignment(alignment);
        return paragraph;
    }

    public static Font getFont() {
        return new Font();
    }

    public static Font getFont(float size) {
        return new Font(Font.FontFamily.UNDEFINED, size);
    }

    public static Font getFont(float size, int style) {
        return new Font(Font.FontFamily.UNDEFINED, size, style);
    }

    public static Font getFont(float size, int style, BaseColor color) {
        return new Font(Font.FontFamily.UNDEFINED, size, style, color);
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
        float x, y;
        image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        x = (PageSize.A4.getWidth() - image.getScaledWidth()) / 2;
        y = (PageSize.A4.getHeight() - image.getScaledHeight()) / 2;
        y += y / 2;
        image.setAbsolutePosition(x, y);
        return image;
    }

    public static Paragraph getNewLine() {
        return new Paragraph(Chunk.NEWLINE);
    }

    public static Paragraph getNewLine(int lineNumber) {
        Paragraph paragraph = getParagraph("");
        paragraph.setSpacingBefore(lineNumber);
        return paragraph;
    }
}
