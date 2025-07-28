/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.web.Util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.Row;

import org.springframework.util.ClassUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Administrator
 */
public class Helper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String getUserAuthenticated() {
        String user;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            user = "anonymousUser";
        }
        return user;
    }

    public static String incrementString(String suffix) {

        Integer suffixint = Integer.parseInt(suffix);
        suffixint++;
        String format = "%0" + suffix.length() + "d";
        String newsuffix = String.format(format, suffixint);
        return newsuffix;
    }

//        public static Integer incrementIntegre(Integer suffix) {
//
//        Integer suffixint = Integer.parseInt(suffix);
//        suffixint++;
//        Integer format = "%0" + suffix.length() + "d";
//        Integer newsuffix = Integer.max(format, suffixint);
//        return newsuffix;
//    }
    public static int incrementInteger1(int suffix) {
        return suffix + 1;
    }

    public static int incrementInteger100(int suffix) {
        return suffix + 100;
    }

    public static int incrementInteger10000(int suffix) {
        return suffix + 10000;
    }
    
    
    
    public static int incrementInteger10000000(int suffix) {
        return suffix + 10000000;
    }
    

    public static String generateSuffix(Integer codeLength, String prefix) {

        int taillesuffix = codeLength - prefix.length();
        String format = "%0" + taillesuffix + "d";
        String suffix = String.format(format, 1);
        return suffix;
    }

    public static String generateSuffixWithCompteur(Integer codeLength, String prefix, Integer compteur) {

        int taillesuffix = codeLength - prefix.length();
        String format = "%0" + taillesuffix + "d";
        String suffix = String.format(format, compteur);
        return suffix;
    }

    public static String generatecodeFromPrefixAndCompteur(Integer codeLength, String prefix, Integer compteur) {

        int taillesuffix = codeLength - prefix.length();
        String format = "%0" + taillesuffix + "d";
        String suffix = prefix + String.format(format, compteur);
        return suffix;
    }

    public static byte[] extractBytes(String ImageName, String extension) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(ImageName));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, extension, baos);
        baos.flush();
        return baos.toByteArray();
    }

    public static String incrementCode(String code) {
        String intPart = code.replaceAll("[^0-9]", "");
        String stringPart = code.replaceAll("[^A-z]+", "");
        return stringPart + incrementString(intPart);
    }

    public static boolean isStringContainsLatinCharactersOnly(final String iStringToCheck) {
        return iStringToCheck.matches("^[a-zA-Z0-9. ]+$");
    }

    public static byte[] read(ByteArrayInputStream bais) throws IOException {
        byte[] array = new byte[bais.available()];
        bais.read(array);

        return array;
    }

    public static Date resetTime(Date d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastHourInDate(Date d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
//        cal.set(Calendar.MILLISECOND, 998);
        return cal.getTime();
    }

    /**
     * get what change between two object
     *
     * @param obj1
     * @param obj2
     * @param clazz
     * @param ignoredFildes
     * @return a list of String
     * @author Hichem Dabbèchi
     */
    public static List<String> whatChange(Object obj1, Object obj2, Class clazz, Collection<String> ignoredFildes) {
        List<String> whatChange = new ArrayList<>();
        List<Field> fieldsFinal = new ArrayList<>();
        List<Field> Superfields = Arrays.asList(clazz.getSuperclass().getDeclaredFields());
//        System.out.println("Superfields.size() " + Superfields.size());
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
//        System.out.println("fields.size() " + fields.size());
        fieldsFinal.addAll(Superfields);
        fieldsFinal.addAll(fields);
//        System.out.println("fieldsFinal.size() " + fieldsFinal.size());
        fieldsFinal.forEach((Field field) -> {
            try {
                field.setAccessible(true);
                if (!ignoredFildes.contains(field.getName())) {
                    if (field.get(obj1) != null && field.get(obj2) != null) {
                        if ((field.get(obj1) instanceof Collection) == false) {
                            if (ClassUtils.isPrimitiveOrWrapper(field.get(obj1).getClass()) || field.get(obj1) instanceof String || field.get(obj1) instanceof BigDecimal || field.get(obj1) instanceof Date) {
                                if (!field.get(obj1).equals(field.get(obj2))) {
                                    whatChange.add(field.getName());
                                }
//                        } else if ((field.get(obj1).equals(field.get(obj2)))) {
                            } else if (!(field.get(obj1).hashCode() == (field.get(obj2).hashCode()))) {
                                whatChange.add(field.getName());
                            }
                        }
                    } else if (!(field.get(obj1) == null && field.get(obj2) == null)) {
                        whatChange.add(field.getName());
                    }
                }
            } catch (IllegalArgumentException ex) {
                java.util.logging.Logger.getLogger(Helper.class.getName()).log(Level.WARNING, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Helper.class.getName()).log(Level.WARNING, null, ex);
            }
        });
        return whatChange;
    }

    public static Integer[] removeNullValueFromArray(Integer[] codes) {
        return Arrays.asList(codes).stream().filter(f -> f != null).toArray(Integer[]::new);
    }

    public static String[] removeNullValueFromArrayString(String[] codes) {
        return Arrays.asList(codes).stream().filter(f -> f != null).toArray(String[]::new);
    }

    public static <T, U> List<T> removeNullValueFromCollection(Collection<T> booleans) {
        return booleans.stream().filter(f -> f != null).collect(Collectors.toList());
    }

    public static <T, U> List<T> removeNullValueFromCollection(Collection<T> booleans, Collection<T> booleans2) {
        return booleans.stream().filter(f -> f != null).collect(Collectors.toList());
    }

    public static LocalDate resetTime(LocalDate date) {
        LocalTime time = LocalTime.parse("00:00:00");
        date.atTime(time);
        return date;
    }

    public static LocalDate getLastHourInDate(LocalDate date) {
        LocalTime time = LocalTime.parse("23:59:59");
        date.atTime(time);
        return date;
    }
//
//    public static boolean isRowEmpty(Row row) {
//        boolean isEmpty = true;
//        DataFormatter dataFormatter = new DataFormatter();
//
//        if (row != null) {
//            for (Cell cell : row) {
//                if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
//                    isEmpty = false;
//                    break;
//                }
//            }
//        }
//
//        return isEmpty;
//    }

    public static ObjectMapper kafkaMapper() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static String namesLookLike(String str, Boolean newDictionary) {
        if (newDictionary) {
            return str.replace(" ", "").replace(".", "").replace(",", "").replace(":", "").replace("''", "")
                    .replace("،", "").replace("{", "").replace("}", "").replace("[", "").replace("]", "")
                    .replace("=", "").replace("+", "").replace("_", "").replace("-", "").replace("(", "")
                    .replace(")", "").replace("&", "").replace("*", "").replace("^", "").replace("%", "")
                    .replace("$", "").replace("#", "").replace("@", "").replace("!", "").replace("÷", "")
                    .replace("‘", "").replace("؛", "").replace("×", "").replace("<", "").replace(">", "")
                    .replace("إ", "ا").replace("لإ", "ل").replace("\"", "").replace("/", "").replace("،", "")
                    .replace("ـ", "").replace("أ", "ا").replace("لأ", "ل").replace("~", "").replace("لآ", "ل")
                    .replace("آ", "ا").replace("’", "").replace(",", "").replace("؟", "").replace("ئ", "ا")
                    .replace("ء", "ا").replace("ؤ", "ا").replace("لا", "ل").replace("ي", "ى").replace("ة", "ه")
                    //                .replace("و", "ا")
                    .replace("ض", "ظ").replace("ث", "ت").replace("اا", "ا") //                .replace("ى", "ا")
                    ;
        } else {
            return str.replace(" ", "").replace(".", "").replace(",", "").replace(":", "").replace("''", "")
                    .replace("،", "").replace("{", "").replace("}", "").replace("[", "").replace("]", "")
                    .replace("=", "").replace("+", "").replace("_", "").replace("-", "").replace("(", "")
                    .replace(")", "").replace("&", "").replace("*", "").replace("^", "").replace("%", "")
                    .replace("$", "").replace("#", "").replace("@", "").replace("!", "").replace("÷", "")
                    .replace("‘", "").replace("؛", "").replace("×", "").replace("<", "").replace(">", "")
                    .replace("إ", "ا").replace("لإ", "ل").replace("\"", "").replace("/", "").replace("،", "")
                    .replace("ـ", "").replace("أ", "ا").replace("لأ", "ل").replace("~", "").replace("لآ", "ل")
                    .replace("آ", "ا").replace("’", "").replace(",", "").replace("؟", "").replace("ئ", "ا")
                    .replace("ء", "ا").replace("ؤ", "ا").replace("لا", "ل").replace("ي", "ى").replace("ة", "ه")
                    .replace("و", "ا")
                    .replace("ض", "ظ").replace("ث", "ت").replace("اا", "ا")
                    .replace("ى", "ا");
        }
    }
}
