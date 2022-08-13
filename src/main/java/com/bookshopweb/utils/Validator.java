package com.bookshopweb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Validator<T> {
    private T o;
    private final List<String> violations = new ArrayList<>();

    public static <T> Validator<T> of(T o) {
        Validator<T> validator = new Validator<>();
        validator.o = o;
        return validator;
    }

    public Validator<T> changeTo(T o) {
        this.o = o;
        return this;
    }

    public List<String> toList() {
        return violations;
    }

    public Validator<T> isNotNull() {
        Optional<String> violation = o != null ? Optional.empty() : Optional.of("Không để trống");
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isNotNullAndEmpty() {
        Optional<String> violation = o != null && !String.valueOf(o).trim().isEmpty() ? Optional.empty() : Optional.of("Không để trống");
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isNotEmpty() {
        Optional<String> violation = Optional.ofNullable(o)
                .filter(obj -> String.valueOf(obj).trim().isEmpty())
                .map(obj -> "Không để trống");
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isNotBlankAtBothEnds() {
        Optional<String> violation = Optional.ofNullable(o)
                .filter(obj -> !String.valueOf(obj).equals(String.valueOf(obj).trim()))
                .map(obj -> "Không có dấu cách ở hai đầu");
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isAtMostOfLength(int max) {
        Optional<String> violation = Optional.ofNullable(o)
                .filter(obj -> String.valueOf(obj).length() > max)
                .map(obj -> String.format("Chỉ được có nhiều nhất là %d ký tự", max));
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isAtLeastOfLength(int min) {
        Optional<String> violation = Optional.ofNullable(o)
                .filter(obj -> String.valueOf(obj).length() < min)
                .map(obj -> String.format("Phải có ít nhất là %d ký tự", min));
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> hasPattern(String pattern, String patternName) {
        Optional<String> violation = Optional.ofNullable(o)
                .filter(obj -> !String.valueOf(obj).matches(pattern))
                .map(obj -> String.format("Phải đúng dạng %s", patternName));
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isNotExistent(boolean existence, String subjectName) {
        Optional<String> violation = !existence ? Optional.empty() : Optional.of(String.format("%s đã tồn tại", subjectName));
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isExistent(boolean existence, String subjectName) {
        Optional<String> violation = existence ? Optional.empty() : Optional.of(String.format("%s chưa tồn tại", subjectName));
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isEqualTo(String other, String subjectName) {
        Optional<String> violation = Optional.ofNullable(o)
                .filter(obj -> !String.valueOf(obj).equals(other))
                .map(obj -> String.format("%s không đúng", subjectName));
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isSmallerThan(double number, String subjectName) {
        Optional<String> violation = Optional.ofNullable(o)
                .filter(obj -> !(Double.parseDouble(String.valueOf(o)) <= number))
                .map(obj -> String.format("%s phải nhỏ hơn %s", subjectName, number));
        violation.ifPresent(violations::add);
        return this;
    }

    public Validator<T> isLargerThan(double number, String subjectName) {
        Optional<String> violation = Optional.ofNullable(o)
                .filter(obj -> !(Double.parseDouble(String.valueOf(o)) >= number))
                .map(obj -> String.format("%s phải lớn hơn %s", subjectName, number));
        violation.ifPresent(violations::add);
        return this;
    }
}
