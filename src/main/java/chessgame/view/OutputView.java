package chessgame.view;

import chessgame.domain.board.Board;
import chessgame.domain.square.Square;
import chessgame.domain.board.Rank;

public class OutputView {

    public void printBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : board.getRanks()) {
            stringBuilder.insert(0, makeRank(rank));
        }
        System.out.println(stringBuilder);
    }

    private StringBuilder makeRank(Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Square square : rank.getPieces()) {
            stringBuilder.append(PieceTypeMapper.getTarget(square));
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder;
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}