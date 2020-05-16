package ch.silasberger.cardswebproto.util;

public class StringUtils {

    public static String toSnakeCase(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(input.charAt(0)));
        input.chars().skip(1).forEach(i -> {
            char c = (char) i;
            if (Character.isLowerCase(c)) {
                sb.append(c);
                return;
            }
            sb.append("_").append(Character.toLowerCase(c));
        });
        return sb.toString();
    }

}
