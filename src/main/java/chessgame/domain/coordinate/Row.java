package chessgame.domain.coordinate;

import java.util.Objects;

public class Row {

    private static final int MIN_ROW = 0;
    private static final int MAX_ROW = 7;

    private final int value;

    public Row(final int value) {
        this.value = value;
    }

    public static Row from(final int value) {
        validate(value);
        return new Row(value);
    }

    public static Row fromWithoutValidate(final int value) {
        return new Row(value);
    }

    private static void validate(final int value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 행의 값입니다.");
        }
    }

    private static boolean isOutOfRange(final int value) {
        return value < MIN_ROW || value > MAX_ROW;
    }

    public int add(final int otherRow) {
        return this.value + otherRow;
    }

    public int minus(final Row otherRow) {
        return this.value - otherRow.value;
    }

    public int absoluteOfMinus(final Row otherRow) {
        return Math.abs(this.value - otherRow.value);
    }

    public boolean isPositive() {
        return this.value > 0;
    }

    public boolean isNegative() {
        return this.value < 0;
    }

    public boolean isZero() {
        return this.value == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return value == row.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
