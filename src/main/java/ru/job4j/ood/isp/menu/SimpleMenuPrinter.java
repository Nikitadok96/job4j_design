package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleMenuPrinter implements MenuPrinter {

    private final String firstRegex = "^\\d.";
    private final String secondRegex = "(\\d.){2,}+";
    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
            String rsl = replaceTitle(i.getNumber() + i.getName());
            System.out.println(rsl);
        });
    }

    private String replaceTitle(String str) {
        Pattern pattern = Pattern.compile(firstRegex);
        Matcher matcher = pattern.matcher(str);
        StringBuilder strBuilder = new StringBuilder(str);
        while (matcher.find()) {
            strBuilder.insert(0, "Задача ");
        }
        str = strBuilder.toString();
        Pattern patternSecond = Pattern.compile(secondRegex);
        str.lines().forEach(i -> {
            Matcher secondMatcher = patternSecond.matcher(i);
            int startIndex = 0;
            int count = 0;
            while (secondMatcher.find(startIndex)) {
                count++;
                startIndex = secondMatcher.start() + 1;
            }
            startIndex = 0;
            if (secondMatcher.find(startIndex)) {
                strBuilder.insert(0, "----".repeat(count) + " ");
            }
        });
        str = strBuilder.toString();
        return str;
    }
}
