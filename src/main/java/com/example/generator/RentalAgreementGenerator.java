package com.example.generator;

import com.example.model.RentalAgreement;
import com.example.model.RentalTool;
import com.example.validation.Validator;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.RentalApplication.rentalToolMap;
import static java.time.DayOfWeek.MONDAY;
import static java.time.Month.JULY;
import static java.time.Month.SEPTEMBER;

public class RentalAgreementGenerator {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    @SneakyThrows
    public static RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate){

        Validator.validateInputParameters(rentalDayCount, discountPercent);

        RentalTool rentalTool = rentalToolMap.get(toolCode);
        LocalDate dueDate = calculateDueDate(checkoutDate, rentalDayCount);
        int chargeDays = calculateChargeDays(checkoutDate, dueDate, rentalDayCount, rentalTool.isWeekendCharge(), rentalTool.isHolidayCharge());
        BigDecimal preDiscountCharge = calculateTotalCharge(rentalTool.getDailyCharge(), chargeDays);
        BigDecimal discountAmount = calculateDiscountAmount(preDiscountCharge, discountPercent);
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);

        return RentalAgreement.builder()
                .toolCode(rentalTool.getToolCode())
                .toolType(rentalTool.getToolType())
                .toolBrand(rentalTool.getBrand())
                .rentalDays(rentalDayCount)
                .checkoutDate(checkoutDate)
                .dueDate(dueDate)
                .dailyCharge(rentalTool.getDailyCharge())
                .chargeDays(chargeDays)
                .preDiscountCharge(preDiscountCharge)
                .discountPercent(discountPercent)
                .discountAmount(discountAmount)
                .finalCharge(finalCharge)
                .build();
    }

    private static LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDays) {
        return checkoutDate.plusDays(rentalDays-1);
    }
    private static int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, int rentalDays, boolean weekendCharge, boolean holidayCharge){
        int chargeDays = rentalDays;

        if(!weekendCharge) {
            chargeDays = calculateNumberOfBusinessDays(checkoutDate, dueDate);
        }
        if(!holidayCharge){
            // apply independence day filtering
            List<LocalDate> allIndependenceDays = generateAllIndependenceDays(checkoutDate, dueDate);
            for(LocalDate independenceDay: allIndependenceDays){
                if(isWithinDateRange(checkoutDate, dueDate, independenceDay)) chargeDays--;
            }
            // apply labor day filtering
            List<LocalDate> allLaborDays = generateAllLaborDays(checkoutDate, dueDate);
            for(LocalDate laborDay: allLaborDays){
                if(isWithinDateRange(checkoutDate, dueDate, laborDay)) chargeDays--;
            }
        }
        return chargeDays;
    }

    private static int calculateNumberOfBusinessDays(LocalDate checkoutDate, LocalDate dueDate) {
        LocalDate endDate = dueDate.plusDays(1);
        return (int)checkoutDate.datesUntil(endDate)
                .map(LocalDate::getDayOfWeek)
                .filter(day -> !Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(day))
                .count();
    }

    private static List<LocalDate> generateAllIndependenceDays(LocalDate checkoutDate, LocalDate dueDate) {
        List<LocalDate> allIndependenceDays = new ArrayList<>();
        int startYear = checkoutDate.getYear();
        int endYear = dueDate.getYear();
        for(int i=startYear; i<=endYear; i++){
            LocalDate independenceDay = LocalDate.of(i, JULY, 4);
            independenceDay = adjustForWeekends(independenceDay);
            allIndependenceDays.add(independenceDay);
        }
        return allIndependenceDays;
    }

    private static List<LocalDate> generateAllLaborDays(LocalDate checkoutDate, LocalDate dueDate) {
        List<LocalDate> allLaborDays = new ArrayList<>();
        int startYear = checkoutDate.getYear();
        int endYear = dueDate.getYear();
        for(int i=startYear; i<=endYear; i++){
            LocalDate laborDay = LocalDate.of(i, SEPTEMBER, 1).with(TemporalAdjusters.dayOfWeekInMonth(1, MONDAY));
            allLaborDays.add(laborDay);
        }
        return allLaborDays;
    }

    private static LocalDate adjustForWeekends(LocalDate date) {
        return switch (date.getDayOfWeek()) {
            case SATURDAY -> date.minusDays(1);
            case SUNDAY -> date.plusDays(1);
            default -> date;
        };
    }

    private static boolean isWithinDateRange(LocalDate startDate, LocalDate endDate, LocalDate testDate){
        return (testDate.isEqual(startDate) || testDate.isEqual(endDate))
                || (testDate.isBefore(endDate) && testDate.isAfter(startDate));
    }

    private static BigDecimal calculateTotalCharge(BigDecimal dailyCharge, int chargeDays) {

        return dailyCharge.multiply(BigDecimal.valueOf(chargeDays)).setScale(2,RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateDiscountAmount(BigDecimal totalCharge, int discountPercent) {

        BigDecimal discount = BigDecimal.valueOf(discountPercent).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
        return totalCharge.multiply(discount).setScale(2,RoundingMode.HALF_UP);
    }
}
