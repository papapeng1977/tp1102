package com.example.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utility {
    public static String convertDateToString(LocalDate date){
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yy");
        return date.format(formatters);
    }
    public static String generateDollarStringFormat(BigDecimal charge) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(charge);
    }
}
