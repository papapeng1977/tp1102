package com.example.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.util.Utility.convertDateToString;
import static com.example.util.Utility.generateDollarStringFormat;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BigDecimal dailyCharge;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;
    public void print() {

        System.out.println("Tool code: " + this.toolCode);
        System.out.println("Tool type: " + this.toolType);
        System.out.println("Tool brand: " + this.toolBrand);
        System.out.println("Rental days: " + this.rentalDays);
        System.out.println("Check out date: " + convertDateToString(this.checkoutDate));
        System.out.println("Due date: " + convertDateToString(this.dueDate));
        System.out.println("Daily rental charge: " + generateDollarStringFormat(this.dailyCharge));
        System.out.println("Charge days: " + this.chargeDays);
        System.out.println("Pre-discount charge: " + generateDollarStringFormat(this.preDiscountCharge));
        System.out.println("Discount percent: " + this.discountPercent + "%");
        System.out.println("Discount amount: " + generateDollarStringFormat(this.discountAmount));
        System.out.println("Final charge: " + generateDollarStringFormat(this.finalCharge));
    }

}
