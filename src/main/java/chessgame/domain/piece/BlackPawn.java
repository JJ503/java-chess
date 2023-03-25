package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;
import chessgame.domain.chessgame.Camp;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.MINUS_ONE;
import static chessgame.domain.coordinate.Inclination.ONE;

public class BlackPawn extends Pawn {

    private static final List<Inclination> availableInclinationsWhenCatch = List.of(ONE, MINUS_ONE);

    public BlackPawn() {
        super(Camp.BLACK);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (isFirstMove) {
            return isReachableByRuleWhenFirstMove(startCoordinate, endCoordinate);
        }
        return isReachableByRuleAfterFirstMove(startCoordinate, endCoordinate);
    }

    private boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Inclination inclination = startCoordinate.getInclination(endCoordinate);
        return inclination.equals(Inclination.POSITIVE_INFINITY)
                && startCoordinate.hasDistanceLessThan(endCoordinate, 2);
    }

    private boolean isReachableByRuleAfterFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        Inclination inclination = startCoordinate.getInclination(endCoordinate);
        return inclination.equals(Inclination.POSITIVE_INFINITY)
                && startCoordinate.hasDistanceLessThan(endCoordinate, 1);
    }

    @Override
    public boolean isReachableWhenCatch(Coordinate startCoordinate, Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        Inclination inclination = startCoordinate.getInclination(endCoordinate);
        Coordinate differenceCoordinate = startCoordinate.minus(endCoordinate);
        return availableInclinationsWhenCatch.contains(inclination)
                && differenceCoordinate.hasNegativeRowValue()
                && startCoordinate.hasDistanceLessThan(endCoordinate, 1);
    }

    @Override
    public boolean canReap() {
        return false;
    }
}