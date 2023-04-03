package chessgame.domain.piece;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;

public abstract class Piece {

    private final PieceType pieceType;
    private final Camp camp;

    protected Piece(final PieceType pieceType, final Camp camp) {
        this.pieceType = pieceType;
        this.camp = camp;
    }

    public abstract boolean isReachableByRule(final Coordinate startCoordinate,
                                              final Coordinate endCoordinate);

    public abstract boolean isCatchable(final Camp otherCamp,
                                        final Coordinate startCoordinate,
                                        final Coordinate endCoordinate);


    public abstract boolean canReap();

    public boolean isEmpty() {
        return pieceType.isEmpty();
    }

    public boolean isNotEmpty() {
        return !pieceType.isEmpty();
    }

    public boolean isSameCamp(final Camp camp) {
        return this.camp.equals(camp);
    }

    public boolean isSameTypeWith(final PieceType otherType) {
        return this.pieceType.equals(otherType);
    }

    public PieceType pieceType() {
        return pieceType;
    }

    public Camp camp() {
        return camp;
    }

    public double score() {
        return pieceType.score();
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                ", camp=" + camp +
                '}';
    }
}
