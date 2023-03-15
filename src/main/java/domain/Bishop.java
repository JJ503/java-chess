package domain;

import java.util.List;

public class Bishop implements NewPieceType {

    private static final List<Double> availableInclinations = List.of(1.0, -1.0);

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }
}
