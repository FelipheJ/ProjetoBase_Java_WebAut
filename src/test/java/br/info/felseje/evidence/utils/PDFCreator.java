package br.info.felseje.evidence.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

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
}
