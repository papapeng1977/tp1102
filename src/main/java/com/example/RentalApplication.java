package com.example;

import com.example.generator.RentalAgreementGenerator;
import com.example.model.RentalAgreement;
import com.example.model.RentalTool;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class RentalApplication {
    public static Map<String, RentalTool> rentalToolMap;
    static {
        RentalTool chns = RentalTool.builder()
                .toolCode("CHNS")
                .toolType("Chainsaw")
                .brand("Stihl")
                .dailyCharge(new BigDecimal("1.49"))
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(true)
                .build();
        RentalTool ladw = RentalTool.builder()
                .toolCode("LADW")
                .toolType("Ladder")
                .brand("Werner")
                .dailyCharge(new BigDecimal("1.99"))
                .weekdayCharge(true)
                .weekendCharge(true)
                .holidayCharge(false)
                .build();
        RentalTool jakd = RentalTool.builder()
                .toolCode("JAKD")
                .toolType("Jackhammer")
                .brand("DeWalt")
                .dailyCharge(new BigDecimal("2.99"))
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(false)
                .build();
        RentalTool jakr = RentalTool.builder()
                .toolCode("JAKR")
                .toolType("Jackhammer")
                .brand("Ridgid")
                .dailyCharge(new BigDecimal("2.99"))
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(false)
                .build();
        rentalToolMap = new HashMap<>();
        rentalToolMap.put(chns.getToolCode(), chns);
        rentalToolMap.put(ladw.getToolCode(), ladw);
        rentalToolMap.put(jakd.getToolCode(), jakd);
        rentalToolMap.put(jakr.getToolCode(), jakr);
    }

    public static void main(String[] args) {
        String toolCode;
        int rentalDayCount;
        int discountPercent;
        LocalDate checkoutDate;

        try{
            toolCode = args[0];
            rentalDayCount = Integer.parseInt(args[1]);
            discountPercent = Integer.parseInt(args[2]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
            checkoutDate =LocalDate.parse(args[3], formatter);
        }catch (Exception e){
            System.out.println("Invalid format of arguments, please use the below correct order and format of 4 arguments:");
            System.out.println("toolCode rentalDayCount discountPercent checkoutDate, as an example: CHNS 4 20 05/16/24");
            throw e;
        }

        RentalAgreement rentalAgreement = RentalAgreementGenerator.checkout(toolCode,rentalDayCount, discountPercent,checkoutDate);
        rentalAgreement.print();
    }
}