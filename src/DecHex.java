public class DecHex {

    public static String decToHex(int dec) {
        char[] hexDigits =  {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder hexBuilder = new StringBuilder(6);
        hexBuilder.setLength(6);
        for (int i = 5; i >= 0; --i) {
            int j = dec & 0x0F;
            hexBuilder.setCharAt(i, hexDigits[j]);
            dec >>= 4;
        }
        return hexBuilder.toString();
    }

    public static int hexToDec(String s) {
        s = s.substring(2, 8);
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }
}
