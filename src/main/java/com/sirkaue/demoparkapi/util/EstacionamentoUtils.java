package com.sirkaue.demoparkapi.util;

import java.time.LocalDateTime;

public class EstacionamentoUtils {

    private EstacionamentoUtils() {
    }

    public static String gerarRecibo() {
        LocalDateTime date = LocalDateTime.now();
        String recibo = date.toString().substring(0, 19);
        return recibo
                .replace("-", "")
                .replace(":", "")
                .replace("T", "-");
    }
}
