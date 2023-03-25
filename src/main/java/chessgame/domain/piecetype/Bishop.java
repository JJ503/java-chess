package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;
import chessgame.domain.piece.Camp;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.MINUS_ONE;
import static chessgame.domain.coordinate.Inclination.ONE;

public class Bishop extends PieceType {

    private static final double SCORE = 3;
    private static final List<Inclination> availableInclinations = List.of(ONE, MINUS_ONE);

    public Bishop(final Camp camp) {
        super(PieceTypeSymbol.BISHOP, camp, SCORE);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }

    @Override
    public boolean isCatchable(final Camp otherCamp, final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (isSameCamp(otherCamp)) {
            return false;
        }
        return isReachableByRule(startCoordinate, endCoordinate);
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
