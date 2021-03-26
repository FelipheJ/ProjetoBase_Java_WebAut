package br.info.felseje.evidence.interfaces;

import com.itextpdf.text.pdf.PdfPTable;

public interface Table {
    PdfPTable getTable(TableWidth tableWidth);
}
