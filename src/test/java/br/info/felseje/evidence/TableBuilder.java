package br.info.felseje.evidence;

import java.util.Map;

import br.info.felseje.evidence.interfaces.TableWidth;
import com.itextpdf.text.pdf.PdfPTable;
import br.info.felseje.evidence.interfaces.Table;

public class TableBuilder implements Table {

    public TableBuilder(Map<String, String> tableContent) {
        this.tableContent = tableContent;
    }

    private Map<String, String> tableContent;

    @Override
    public PdfPTable getTable(TableWidth tableWidth) {
        return null;
    }
}
