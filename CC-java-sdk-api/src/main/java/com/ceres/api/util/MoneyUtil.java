package com.ceres.api.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class MoneyUtil {
    public static int MONEY_SCALE = 16;
    public static int MONEY_DISPLAY_SCALE = 8;
    public static int DEFAULT_RATIO_DISPLAY_SCALE = 6;

    public MoneyUtil() {
    }

    public static BigDecimal covert16(BigDecimal source) {
        return source == null ? null : source.setScale(MONEY_SCALE, 1);
    }

    public static BigDecimal covert8(BigDecimal source) {
        return source == null ? null : source.setScale(MONEY_DISPLAY_SCALE, 1);
    }

    public static String covert8String(BigDecimal source) {
        if (source == null) {
            return "";
        } else {
            return BigDecimal.ZERO.compareTo(source) == 0 ? "0" : source.setScale(MONEY_DISPLAY_SCALE, 1).stripTrailingZeros().toPlainString();
        }
    }

    public static String covertString(String source, int scale) {
        return StringUtils.isEmpty(source) ? "" : (new BigDecimal(source)).setScale(scale, 4).stripTrailingZeros().toPlainString();
    }

    public static String covertStringRoundDown(String source, int scale) {
        return StringUtils.isEmpty(source) ? "" : (new BigDecimal(source)).setScale(scale, 1).stripTrailingZeros().toPlainString();
    }

    public static String covertStringRoundUp(String source, int scale) {
        return StringUtils.isEmpty(source) ? "" : (new BigDecimal(source)).setScale(scale, 0).stripTrailingZeros().toPlainString();
    }

    public static String covertString(BigDecimal source) {
        if (source == null) {
            return "";
        } else {
            return BigDecimal.ZERO.compareTo(source) == 0 ? "0" : source.stripTrailingZeros().toPlainString();
        }
    }

    public static String covertString(String source) {
        return StringUtils.isEmpty(source) ? "" : covertString(new BigDecimal(source));
    }

    public static BigDecimal divide(BigDecimal value, BigDecimal divisor) {
        return value.divide(divisor, MONEY_SCALE, RoundingMode.DOWN);
    }

    public static BigDecimal divide(BigDecimal value, BigDecimal divisor, int scale) {
        return value.divide(divisor, scale, RoundingMode.DOWN);
    }

    public static BigDecimal divide(BigDecimal value, BigDecimal divisor, int scale, RoundingMode roundingMode) {
        return value.divide(divisor, scale, roundingMode);
    }

    public static BigDecimal multiply(BigDecimal value, BigDecimal divisor) {
        return covert16(value.multiply(divisor));
    }

    public static BigDecimal multiply(BigDecimal value, BigDecimal divisor, int scale) {
        return value.multiply(divisor).setScale(scale, RoundingMode.DOWN);
    }

    public static BigDecimal multiply(BigDecimal value, BigDecimal divisor, int scale, RoundingMode roundingMode) {
        return value.multiply(divisor).setScale(scale, roundingMode);
    }

    public static BigDecimal covertInterestRatio(BigDecimal ratio) {
        if (null == ratio) {
            throw new IllegalArgumentException("参数错误");
        } else {
            return ratio.setScale(DEFAULT_RATIO_DISPLAY_SCALE, 4).multiply(new BigDecimal(100)).stripTrailingZeros();
        }
    }

    public static String numFormatToPercentage(BigDecimal bigDecimal) {
        Objects.requireNonNull(bigDecimal);
        BigDecimal num1 = BigDecimal.ONE;
        BigDecimal num2 = new BigDecimal("100");
        return bigDecimal.divide(num1).multiply(num2).stripTrailingZeros().toPlainString() + "%";
    }

    public static String numFormatToPercentage(String bigDecimal) {
        Objects.requireNonNull(bigDecimal);
        if (StringUtils.isEmpty(bigDecimal)) {
            throw new NullPointerException();
        } else {
            BigDecimal bigDecimal1 = new BigDecimal(bigDecimal);
            BigDecimal num1 = BigDecimal.ONE;
            BigDecimal num2 = new BigDecimal("100");
            return bigDecimal1.divide(num1).multiply(num2).stripTrailingZeros().toPlainString() + "%";
        }
    }

    public static void main(String[] args) {
        BigDecimal value = divide(multiply((new BigDecimal("10")).multiply(new BigDecimal("0.00020000000000000000")), new BigDecimal("1000")), new BigDecimal("100"));
        System.out.println(value.toPlainString());
        System.out.println(covert8(value));
        BigDecimal ratio = new BigDecimal("0.0005125000");
        String ratiostr = ratio.setScale(6, 4).multiply(new BigDecimal(100)).stripTrailingZeros().toString();
        System.out.println(ratiostr);
    }
}
