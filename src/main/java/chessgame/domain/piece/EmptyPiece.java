package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piecetype.PieceType;

import java.util.Optional;

public class EmptyPiece extends Piece {

    @Override
    public boolean canReap() {
        return false;
    }

    @Override
    public boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return false;
    }

    @Override
    public boolean isCatchable(Optional<Camp> otherCamp, Coordinate startCoordinate, Coordinate endCoordinate) {
        return false;
    }

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public Optional<PieceType> piece() {
        return Optional.empty();
    }

    @Override
    public Optional<Camp> camp() {
        return Optional.empty();
    }

    @Override
    public boolean isSameCamp(final Camp camp) {
        return false;
    }
}
