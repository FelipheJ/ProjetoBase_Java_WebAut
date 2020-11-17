package br.com.projeto.commons;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Utils {

    public static final String HORA = "HHmmss";
    public static final String DATA = "ddMMyyyy";
    public static final String DATAHORA = "ddMMyyyyHHmmss";

    public static Date obterDataAtual() {
        return Calendar.getInstance().getTime();
    }

    public static String obterDataAtual(String formato) {
        return new SimpleDateFormat(formato).format(obterDataAtual());
    }
}
