package br.info.felseje.evidence.utils;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import br.info.felseje.evidence.TableBuilder;
import br.info.felseje.evidence.interfaces.TableWidth;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.info.felseje.evidence.interfaces.Table;

/**
 * Class that contains methods for creating the document.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class PDFUtils {

    public static Document getDocument(String path) throws DocumentException, FileNotFoundException {
        return getDocument(PageSize.A4, 20f, 20f, 0f, 0f, path);
    }

    public static Document getDocument(Rectangle pageSize, float marginLeft, float marginRight, float marginTop, float marginBottom, String path) throws DocumentException, FileNotFoundException {
        Document doc = new Document(pageSize, marginLeft, marginRight, marginTop, marginBottom);
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        return doc;
    }

    public static PdfPTable getTable(TableWidth tableWidth, Map<String, String> tableContent) {
        return new TableBuilder(tableContent).getTable(tableWidth);
    }

    public static PdfPCell getCell(String text) {
        return new PdfPCell(new Paragraph(text));
    }

    public static PdfPCell getCell(String text, Paragraph paragraph) {
        paragraph.add(text);
        return new PdfPCell(paragraph);
    }

    public static Paragraph getParagraph(String text) {
        return new Paragraph(text);
    }

    public static Paragraph getParagraph(String text, float size) {
        Font aux = new Paragraph().getFont();
        return getParagraph(text, size, aux.getStyle(), aux.getColor());
    }

    public static Paragraph getParagraph(String text, int style) {
        Font aux = new Paragraph().getFont();
        return getParagraph(text, aux.getSize(), style, aux.getColor());
    }

    public static Paragraph getParagraph(String text, BaseColor color) {
        Font aux = new Paragraph().getFont();
        return getParagraph(text, aux.getSize(), aux.getStyle(), color);
    }

    public static Paragraph getParagraph(String text, float size, BaseColor color) {
        return getParagraph(text, size, new Paragraph().getFont().getStyle(), color);
    }

    public static Paragraph getParagraph(String text, int style, BaseColor color) {
        return getParagraph(text, new Paragraph().getFont().getSize(), style, color);
    }

    public static Paragraph getParagraph(String text, float size, int style) {
        return getParagraph(text, new Paragraph().getFont().getSize(), style, new Paragraph().getFont().getColor());
    }

    public static Paragraph getParagraph(String text, float size, int style, BaseColor color) {
        return new Paragraph(text, new Font(new Paragraph().getFont().getFamily(), size, style, color));
    }

}
