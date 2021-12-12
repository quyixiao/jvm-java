package com.jvm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 陈金虎 2017年1月16日 下午11:42:34
 * @类描述：数字处理类
 * @注意：本内容仅限于杭州霖梓网络科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class NumberUtil {

    private static final Logger logger = LoggerFactory.getLogger(NumberUtil.class);
    private static Pattern numberPattern = Pattern.compile("[0-9]*");
    /**
     * 无默认返回，返回null
     *
     * @param source
     * @return
     */
    public static Long strToLong(String source, Long defValue) {
        if (StringUtils.isBlank(source)) {
			return defValue;
		}
        if (!StringUtils.isNumeric(source)) {
			return defValue;
		}
        return Long.parseLong(source);
    }

    /**
     * 无默认返回，返回null
     *
     * @param source
     * @return
     */
    public static Integer strToInteger(String source, Integer defValue) {
        if (StringUtils.isBlank(source)) {
			return defValue;
		}
        if (!StringUtils.isNumeric(source)) {
			return defValue;
		}
        return Integer.valueOf(source);
    }

    /**
     * 无默认返回
     * <p>
     * 若obj为null，则返回null
     *
     * @param source
     * @return
     */
    public static Long strToLong(String source) {
        if (StringUtils.isBlank(source)) {
			return null;
		}
        if (!StringUtils.isNumeric(source)) {
			return null;
		}
        return Long.parseLong(source);
    }

    public static Long objToLongDefault(Object obj, long defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Long objToLongDefault(Object obj, Long defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Boolean objToBooleanDefault(Object obj, Boolean defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return Boolean.parseBoolean(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Character objToCharacterDefault(Object obj, Character defaultValue) {
        if (null == obj) return defaultValue;
        try {
            char[] chars = obj.toString().toCharArray();
            return chars[0];
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * 方法说明: 判断输入的数值是否为空或者0
     *
     * @param num
     * @return
     */
    public static boolean isNullOrZero(Integer num) {
        if (num == null || num == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 无默认返回
     * <p>
     * 若obj为null，则返回null
     *
     * @param obj
     * @return
     */
    public static Long objToLong(Object obj) {
        if (null == obj) {
			return null;
		}
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    public static int objToIntDefault(Object obj, int defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Integer objToIntDefault(Object obj, Integer defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Short objToShortDefault(Object obj, Short defaultValue) {
        if (null == obj) return defaultValue;
        try {
            return Short.parseShort(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Byte objToByteDefault(Object obj, Byte defaultValue) {
        if (null == obj) return defaultValue;
        try {
            return Byte.parseByte(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static int objToPageIntDefault(Object obj, int defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            int pageNum = Integer.parseInt(obj.toString());
            return pageNum == 0 ? 1 : pageNum;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Long objToPageLongDefault(Object obj, Long defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            Long pageNum = Long.parseLong(obj.toString());
            return pageNum == 0L ? 1L : pageNum;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static float objToFloatDefault(Object obj, Float defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static double objToDoubleDefault(Object obj, double defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Double objToDoubleDefault(Object obj, Double defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static BigDecimal objToBigDecimalDefault(Object obj, BigDecimal defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return new BigDecimal(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }




    public static BigDecimal objToBigDecimalZeroToDefault(Object obj, BigDecimal defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            BigDecimal objValue = new BigDecimal(obj.toString());
            if (objValue.compareTo(BigDecimal.ZERO) == 0) {
                return defaultValue;
            }
            return objValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 无默认返回
     * <p>
     * 若obj为null，则返回null
     *
     * @param obj
     * @return
     */
    public static Integer objToInteger(Object obj) {
        if (null == obj) {
			return null;
		}
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            logger.error("格式转换异常", e);
            return null;
        }
    }

    /**
     * 保留两位小数
     *
     * @param d
     * @return
     */
    public static String format2Str(BigDecimal d) {
        if (d == null) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String ds = df.format(d);
        return ds;
    }

    /**
     * 比较数字是否在指定两个数字范围呢
     *
     * @param compareNum 需要转换的数字
     * @param minNumber  最小范围
     * @param maxNumber  最大范围
     * @return 成功返回Ineger，失败返回null
     */
    public static boolean between2Number(Number compareNum, Number minNumber, Number maxNumber) {
        if (compareNum.longValue() > maxNumber.longValue() || compareNum.longValue() < minNumber.longValue()) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T parseNumber(String text, Class<T> targetClass) {
        if (StringUtils.isBlank(text) || null == targetClass) {
            logger.info("text string or target class must not be null or empty");
            return null;
        }
        String trimmed = trimAllWhitespace(text);

        if (targetClass.equals(Byte.class)) {
            return (T) (isHexNumber(trimmed) ? Byte.decode(trimmed) : Byte.valueOf(trimmed));
        } else if (targetClass.equals(Short.class)) {
            return (T) (isHexNumber(trimmed) ? Short.decode(trimmed) : Short.valueOf(trimmed));
        } else if (targetClass.equals(Integer.class)) {
            return (T) (isHexNumber(trimmed) ? Integer.decode(trimmed) : Integer.valueOf(trimmed));
        } else if (targetClass.equals(Long.class)) {
            return (T) (isHexNumber(trimmed) ? Long.decode(trimmed) : Long.valueOf(trimmed));
        } else if (targetClass.equals(BigInteger.class)) {
            return (T) (isHexNumber(trimmed) ? decodeBigInteger(trimmed) : new BigInteger(trimmed));
        } else if (targetClass.equals(Float.class)) {
            return (T) Float.valueOf(trimmed);
        } else if (targetClass.equals(Double.class)) {
            return (T) Double.valueOf(trimmed);
        } else if (targetClass.equals(BigDecimal.class) || targetClass.equals(Number.class)) {
            return (T) new BigDecimal(trimmed);
        } else {
            throw new IllegalArgumentException("Cannot convert String [" + text + "] to target class ["
                    + targetClass.getName() + "]");
        }
    }

    /**
     * Determine whether the given value String indicates a hex number, i.e. needs to be passed into
     * {@code Integer.decode} instead of {@code Integer.valueOf} (etc).
     */
    private static boolean isHexNumber(String value) {
        int index = (value.startsWith("-") ? 1 : 0);
        return (value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index));
    }

    /**
     * Decode a {@link BigInteger} from a {@link String} value. Supports decimal, hex and octal notation.
     *
     * @see BigInteger#BigInteger(String, int)
     */
    private static BigInteger decodeBigInteger(String value) {
        int radix = 10;
        int index = 0;
        boolean negative = false;

        // Handle minus sign, if present.
        if (value.startsWith("-")) {
            negative = true;
            index++;
        }

        // Handle radix specifier, if present.
        if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
            index += 2;
            radix = 16;
        } else if (value.startsWith("#", index)) {
            index++;
            radix = 16;
        } else if (value.startsWith("0", index) && value.length() > 1 + index) {
            index++;
            radix = 8;
        }

        BigInteger result = new BigInteger(value.substring(index), radix);
        return (negative ? result.negate() : result);
    }

    private static String trimAllWhitespace(String str) {
        if (!(str != null && str.length() > 0)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int index = 0;
        while (sb.length() > index) {
            if (Character.isWhitespace(sb.charAt(index))) {
                sb.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return sb.toString();
    }

    public static boolean isValidRangeForInteger(Integer obj, int min, int max) {
        return (obj != null && obj >= min && obj <= max);
    }

    public static boolean isNotValidRangeForInteger(Integer obj, int min, int max) {
        return (obj == null || obj < min || obj > max);
    }

    public static boolean isValidForLong(Long obj) {
        return (obj != null && obj >= 0);
    }

    public static boolean isNotValidForLong(Long obj) {
        return (obj == null || obj < 0);
    }

    public static boolean isValidForInteger(Integer obj) {
        return (obj != null && obj >= 0);
    }

    public static boolean isNotValidForInteger(Integer obj) {
        return (obj == null || obj < 0);
    }


    public static BigDecimal objToBigDecimalDivideOnehundredDefault(Object obj, BigDecimal defaultValue) {
        if (null == obj) {
			return defaultValue;
		}
        try {
            return new BigDecimal(obj.toString()).divide(BigDecimal.valueOf(100.00), 2, BigDecimal.ROUND_DOWN);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    /**
     * 判断数字类型
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        Matcher isNum = numberPattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String toChinese(String string) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };

        String result = "";

        int n = string.length();
        for (int i = 0; i < n; i++) {

            int num = string.charAt(i) - '0';

            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }

        return result;

    }

    /**
     * 取最大值
     * @param v1
     * @param v2
     * @param v3
     * @return
     */
    public static BigDecimal compare(BigDecimal v1,BigDecimal v2,BigDecimal v3){
        BigDecimal max = v1.compareTo(v2)>=0 ? v1:v2;
        max = max.compareTo(v3)>=0 ? max:v3;
        return max;

    }


    public static boolean notEquals(Integer a,Integer ... b ){
        return !equals(a,b);
    }

    public static boolean equals(Integer a,Integer ... b ){
        if(a == null && b==null){
            return true;
        }else if (a == null ){
            return false;
        }else if (b == null){
            return false;
        }
        for(Integer c : b ){
            if(!c.equals(a)){
                return false;
            }
        }
        return true;
    }


    public static boolean randomM_N(Integer max,int rand){
        Random random = new Random();
        int i = random.nextInt(max);
        if(i < rand){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int j = 0 ;
        for(int i = 0 ;i < 20000;i ++){
           int result =   NumberUtil.randomM_N(1000, 1) ? 0 : 1;
           if(result == 0 ){
               System.out.println("命中");
               j ++;
           }
        }
        System.out.println(j);

    }
}
