package com.bookshopweb.utils;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextUtils {
    public static String toParagraph(@NotNull String description) {
        String[] paragraphs = description.split("(\r\n|\n)");
        return Stream.of(paragraphs)
                .filter(paragraph -> !paragraph.isEmpty())
                .map(paragraph -> "<p>" + paragraph + "</p>")
                .collect(Collectors.joining(""));
    }
}
