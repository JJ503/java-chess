package chessgame.domain.piece;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;
import chessgame.domain.coordinate.Row;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.MINUS_ONE;
import static chessgame.domain.coordinate.Inclination.ONE;

public class WhitePawn extends Pawn {

    private static final Row firstMoveRow = Row.createWithoutValidate(1);
    private static final List<Inclination> availableInclinationsWhenCatch = List.of(ONE, MINUS_ONE);

    public WhitePawn() {
        super(Camp.WHITE);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (isFirstMove(startCoordinate)) {
            return isReachableByRuleWhenFirstMove(startCoordinate, endCoordinate);
        }
        return isReachableByRuleAfterFirstMove(startCoordinate, endCoordinate);
    }

    private boolean isFirstMove(final Coordinate startCoordinate) {
        return startCoordinate.isSameRow(firstMoveRow);
    }

    public boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Inclination inclination = Inclination.of(startCoordinate, endCoordinate);
        return inclination.equals(Inclination.POSITIVE_INFINITY)
                && startCoordinate.hasDistanceLessThan(endCoordinate, 2);
    }

    public boolean isReachableByRuleAfterFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        Inclination inclination = Inclination.of(startCoordinate, endCoordinate);
        return inclination.equals(Inclination.POSITIVE_INFINITY)
                && startCoordinate.hasDistanceLessThan(endCoordinate, 1);
    }

    @Override
    public boolean isReachableWhenCatch(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        Inclination inclination = Inclination.of(startCoordinate, endCoordinate);
        Coordinate differenceCoordinate = startCoordinate.minus(endCoordinate);
        return availableInclinationsWhenCatch.contains(inclination)
                && differenceCoordinate.hasPositiveRowValue()
                && startCoordinate.hasDistanceLessThan(endCoordinate, 1);
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
