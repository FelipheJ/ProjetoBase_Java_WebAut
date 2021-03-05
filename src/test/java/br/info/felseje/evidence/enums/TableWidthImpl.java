package br.info.felseje.evidence.enums;

import com.itextpdf.text.pdf.PdfPTable;
import br.info.felseje.evidence.interfaces.TableWidth;

/**
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public enum TableWidthImpl implements TableWidth {

    ONE_WIDTH {
        @Override
        public PdfPTable getWidth() {
            return new PdfPTable(new float[] { 70f });
        }
    },

    TWO_WIDTH {
        @Override
        public PdfPTable getWidth() {
            return new PdfPTable(new float[] { 10f, 60f });
        }
    },

    FOUR_WIDTH {
        @Override
        public PdfPTable getWidth() {
            return new PdfPTable(new float[] { 10f, 25f, 10f, 25f });
        }
    },

    ERROR_TABLE_WIDTH {
        @Override
        public PdfPTable getWidth() {
            return new PdfPTable(new float[] { 20f, 50f });
        }
    }
}
