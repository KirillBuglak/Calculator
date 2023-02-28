package calcualtor.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CalcService {

    private static final String regex = "[-+/*\\s\\d]+";
    private boolean gotMinus = false;
    private static final String all = "-+/*";
    private static final String withoutMinus = "+/*";
    private static final String checkInput = "Check your input";
    private static final String zeroDiv = "Can't divide by zero";

    public String calculate(String string) {
        List<String> list = getList(string);
        if (list == null || list.size() == 0) return checkInput;
        return getResult(list);
    }

    private String getResult(List<String> list) {
        try {
            while (list.size() != 1) {
                float put;
                List<String> digits;
                if (list.contains("/")) {
                    digits = getIndexes(list, "/");
                    float second = Float.parseFloat(digits.get(1));
                    if (second == 0) {
                        return zeroDiv;
                    }
                    put = Float.parseFloat(digits.get(0)) / second;
                    compactList(list, "/", put);
                } else if (list.contains("*")) {
                    digits = getIndexes(list, "*");
                    put = Float.parseFloat(digits.get(0)) * Float.parseFloat(digits.get(1));
                    compactList(list, "*", put);
                } else if (list.contains("-")) {
                    digits = getIndexes(list, "-");
                    put = Float.parseFloat(digits.get(0)) - Float.parseFloat(digits.get(1));
                    compactList(list, "-", put);
                } else if (list.contains("+")) {
                    digits = getIndexes(list, "+");
                    put = Float.parseFloat(digits.get(0)) + Float.parseFloat(digits.get(1));
                    compactList(list, "+", put);
                }
            }
        } catch (Exception e) {
            return checkInput;
        }
        return list.get(0);
    }

    private List<String> getList(String string) {
        StringBuilder number;
        List<String> list = new ArrayList<>();
        char[] chars = string.trim().replaceAll("\\s", "").toCharArray();
        if (string.isBlank()
                || withoutMinus.contains(String.valueOf(chars[0]))
                || all.contains(String.valueOf(chars[chars.length - 1]))
                || !string.matches(regex)) {
            return null;
        } else {
            for (int i = 0; i < chars.length; i++) {
                if (all.contains(String.valueOf(chars[i]))) {
                    if (!gotMinus) {
                        gotMinus = true;
                        list.add(String.valueOf(chars[i]));
                    } else if (chars[i] == '-' && String.valueOf(chars[i + 1]).matches("\\d")) {
                        gotMinus = false;
                        list.add(String.valueOf(Arrays.copyOfRange(chars, i, ++i + 1)));
                    } else if (withoutMinus.contains(String.valueOf(chars[i]))
                            && String.valueOf(chars[i + 1]).matches("\\d")) {
                        list.add(String.valueOf(chars[i]));

                    } else {
                        return null;
                    }
                } else {
                    number = new StringBuilder();
                    while (i < chars.length && String.valueOf(chars[i]).matches("\\d")) {
                        number.append(chars[i++]);
                    }
                    list.add(number.toString());
                    i--;
                    gotMinus = false;
                }
            }
        }
        return list;
    }

    private void compactList(List<String> list, String symbol, float put) {
        int prev = list.indexOf(symbol) - 1;
        for (int i = 0; i < 3; i++) {
            list.remove(prev);
        }
        list.add(prev, String.valueOf(put));
    }

    private List<String> getIndexes(List<String> list, String symbol) {
        return new ArrayList<>(List.of(list.get(list.indexOf(symbol) - 1), list.get(list.indexOf(symbol) + 1)));
    }
}
