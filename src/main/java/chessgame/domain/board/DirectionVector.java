package chessgame.domain.board;

import chessgame.domain.coordinate.Coordinate;

import java.util.Arrays;

public enum DirectionVector {

    N(1, 0) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.hasPositiveRowValue() && coordinate.isColZero();
        }
    },
    NE(1, 1) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.hasPositiveRowValue() && coordinate.hasPositiveColValue();
        }
    },
    E(0, 1) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.isRowZero() && coordinate.hasPositiveColValue();
        }
    },
    SE(-1, 1) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.hasNegativeRowValue() && coordinate.hasPositiveColValue();
        }
    },
    S(-1, 0) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.hasNegativeRowValue() && coordinate.isColZero();
        }
    },
    SW(-1, -1) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.hasNegativeRowValue() && coordinate.hasNegativeColValue();
        }
    },
    W(0, -1) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.isRowZero() && coordinate.hasNegativeColValue();
        }
    },
    NW(1, -1) {
        @Override
        boolean isExist(final Coordinate coordinate) {
            return coordinate.hasPositiveRowValue() && coordinate.hasNegativeColValue();
        }
    };

    private final int row;
    private final int column;

    DirectionVector(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public static DirectionVector calculate(final Coordinate startCoordinate,
                                            final Coordinate endCoordinate) {
        Coordinate minusCoordinate = startCoordinate.minus(endCoordinate);

        return Arrays.stream(DirectionVector.values())
                     .filter(directionVector -> directionVector.isExist(minusCoordinate))
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("[ERROR] 방향을 찾을 수 없습니다."));
    }

    public Coordinate moveToDirection(final Coordinate coordinate) {
        return coordinate.add(row, column);
    }

    abstract boolean isExist(final Coordinate coordinate);
}
