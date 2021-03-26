package br.info.felseje.evidence.enums;

import com.itextpdf.text.pdf.PdfPTable;
import br.info.felseje.evidence.interfaces.TableWidth;

public enum TableWidthImpl implements TableWidth {

    FIELD_AND_VALUE {
        @Override
        public PdfPTable getWidth() {
            return new PdfPTable(new float[] {15f, 85f});
        }
    },
    TWO_FIELDS_TWO_VALUES {
        @Override
        public PdfPTable getWidth() {
            return new PdfPTable(new float[] {15f, 35f, 15f, 35f});
        }
    }
}
