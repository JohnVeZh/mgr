package cn.sparke.modules.system.code.utils;

/**
 * Created by zhangbowen on 2017/7/18.
 */
public class ColumnConvert {

    public static String jdbcToJavaType(String jdbc) {
        jdbc = jdbc.toLowerCase();
        if (jdbc.contains("varchar") || jdbc.contains("char") || jdbc.contains("text")) {
            return "String";
        } else if (jdbc.contains("date")) {
            return "Date";
        } else if (jdbc.contains("bigint")) {
            return "Long";
        } else if (jdbc.contains("integer") || jdbc.contains("int") || jdbc.contains("tinyint")) {
            return "Integer";
        } else if (jdbc.contains("decimal")) {
            return "BigDecimal";
        }
        jdbc = jdbc.replaceAll("\\(.*\\)", "");
        return jdbc;
    }
}
