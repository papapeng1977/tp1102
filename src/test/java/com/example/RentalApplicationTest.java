package com.example;

import com.example.exception.ValidationException;
import com.example.generator.RentalAgreementGenerator;
import com.example.model.RentalAgreement;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RentalApplicationTest {

    @Test
    public void testToolCodeJAKR_withInvalidDiscount_throwException(){

        String toolCode = "JAKR";
        int rentalDays = 5;
        int discountPercent = 101;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate =LocalDate.parse("09/03/15", formatter);

        Exception exception = assertThrows(ValidationException.class, () -> {
            RentalAgreementGenerator.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        });

        String expectedMessage = "discount percent must be in the range of 0-100";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testToolCodeLADW(){

        String toolCode = "LADW";
        int rentalDays = 3;
        int discountPercent = 10;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate =LocalDate.parse("07/02/20", formatter);

        String expectedToolType = "Ladder";
        LocalDate expectedDueDate =LocalDate.parse("07/04/20", formatter);
        BigDecimal expectedDailyCharge = new BigDecimal("1.99");
        int expectedChargeDays = 2;
        BigDecimal expectedPreDiscountCharge = new BigDecimal("3.98");
        BigDecimal expectedDiscountAmount = new BigDecimal("0.40");
        BigDecimal expectedFinalCharge= new BigDecimal("3.58");

        RentalAgreement rentalAgreement = RentalAgreementGenerator.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        assertEquals(toolCode, rentalAgreement.getToolCode());
        assertEquals(expectedToolType, rentalAgreement.getToolType());
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());
        assertEquals(expectedDailyCharge, rentalAgreement.getDailyCharge());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedPreDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(expectedDiscountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(expectedFinalCharge, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testToolCodeCHNS(){

        String toolCode = "CHNS";
        int rentalDays = 5;
        int discountPercent = 25;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate =LocalDate.parse("07/02/15", formatter);

        String expectedToolType = "Chainsaw";
        LocalDate expectedDueDate =LocalDate.parse("07/06/15", formatter);
        BigDecimal expectedDailyCharge = new BigDecimal("1.49");
        int expectedChargeDays = 3;
        BigDecimal expectedPreDiscountCharge = new BigDecimal("4.47");
        BigDecimal expectedDiscountAmount = new BigDecimal("1.12");
        BigDecimal expectedFinalCharge= new BigDecimal("3.35");

        RentalAgreement rentalAgreement = RentalAgreementGenerator.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        assertEquals(toolCode, rentalAgreement.getToolCode());
        assertEquals(expectedToolType, rentalAgreement.getToolType());
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());
        assertEquals(expectedDailyCharge, rentalAgreement.getDailyCharge());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedPreDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(expectedDiscountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(expectedFinalCharge, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testToolCodeJAKD_withZeroDiscount(){

        String toolCode = "JAKD";
        int rentalDays = 6;
        int discountPercent = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate =LocalDate.parse("09/03/15", formatter);

        String expectedToolType = "Jackhammer";
        LocalDate expectedDueDate =LocalDate.parse("09/08/15", formatter);
        BigDecimal expectedDailyCharge = new BigDecimal("2.99");
        int expectedChargeDays = 3;
        BigDecimal expectedPreDiscountCharge = new BigDecimal("8.97");
        BigDecimal expectedDiscountAmount = new BigDecimal("0.00");
        BigDecimal expectedFinalCharge= new BigDecimal("8.97");

        RentalAgreement rentalAgreement = RentalAgreementGenerator.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        assertEquals(toolCode, rentalAgreement.getToolCode());
        assertEquals(expectedToolType, rentalAgreement.getToolType());
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());
        assertEquals(expectedDailyCharge, rentalAgreement.getDailyCharge());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedPreDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(expectedDiscountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(expectedFinalCharge, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testToolCodeJAKR_withZeroDiscount(){

        String toolCode = "JAKR";
        int rentalDays = 9;
        int discountPercent = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate =LocalDate.parse("07/02/15", formatter);

        String expectedToolType = "Jackhammer";
        LocalDate expectedDueDate =LocalDate.parse("07/10/15", formatter);
        BigDecimal expectedDailyCharge = new BigDecimal("2.99");
        int expectedChargeDays = 6;
        BigDecimal expectedPreDiscountCharge = new BigDecimal("17.94");
        BigDecimal expectedDiscountAmount = new BigDecimal("0.00");
        BigDecimal expectedFinalCharge= new BigDecimal("17.94");

        RentalAgreement rentalAgreement = RentalAgreementGenerator.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        assertEquals(toolCode, rentalAgreement.getToolCode());
        assertEquals(expectedToolType, rentalAgreement.getToolType());
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());
        assertEquals(expectedDailyCharge, rentalAgreement.getDailyCharge());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedPreDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(expectedDiscountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(expectedFinalCharge, rentalAgreement.getFinalCharge());
    }

    @Test
    public void testToolCodeJAKR(){

        String toolCode = "JAKR";
        int rentalDays = 4;
        int discountPercent = 50;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate =LocalDate.parse("07/02/20", formatter);

        String expectedToolType = "Jackhammer";
        LocalDate expectedDueDate =LocalDate.parse("07/05/20", formatter);
        BigDecimal expectedDailyCharge = new BigDecimal("2.99");
        int expectedChargeDays = 1;
        BigDecimal expectedPreDiscountCharge = new BigDecimal("2.99");
        BigDecimal expectedDiscountAmount = new BigDecimal("1.50");
        BigDecimal expectedFinalCharge= new BigDecimal("1.49");

        RentalAgreement rentalAgreement = RentalAgreementGenerator.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        assertEquals(toolCode, rentalAgreement.getToolCode());
        assertEquals(expectedToolType, rentalAgreement.getToolType());
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());
        assertEquals(expectedDailyCharge, rentalAgreement.getDailyCharge());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedChargeDays, rentalAgreement.getChargeDays());
        assertEquals(expectedPreDiscountCharge, rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        assertEquals(expectedDiscountAmount, rentalAgreement.getDiscountAmount());
        assertEquals(expectedFinalCharge, rentalAgreement.getFinalCharge());
    }
}
