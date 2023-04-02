package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardInitialImage {

    public static final int START_INDEX = 0;
    public static final int END_INDEX = 8;
    private static final int BOARD_FILE = 8;
    private static final int WHITE_BACK_RANK = 0;
    private static final int WHITE_FRONT_RANK = 1;
    private static final int START_EMPTY_RANK = 2;
    private static final int END_EMPTY_RANK = 5;
    private static final int BLACK_FRONT_RANK = 6;
    private static final int BLACK_BACK_RANK = 7;

    private static final Map<Coordinate, Piece> boardImage;

    private BoardInitialImage() {
    }

    static {
        boardImage = makeBoardImage();
    }

    private static Map<Coordinate, Piece> makeBoardImage() {
        Map<Coordinate, Piece> boardImage = new HashMap<>();
        boardImage.putAll(makeWhiteRanks());
        boardImage.putAll(makeEmptyRanks());
        boardImage.putAll(makeBlackRanks());
        return boardImage;
    }

    private static Map<Coordinate, Piece> makeWhiteRanks() {
        List<Piece> backPieces = makeBackRank(Camp.WHITE);
        List<Piece> frontPieces = makeWhiteFrontRank();

        Map<Coordinate, Piece> whiteBoardImage = new HashMap<>();
        for (int file = 0; file < BOARD_FILE; file++) {
            whiteBoardImage.put(Coordinate.createOnBoard(WHITE_BACK_RANK, file), backPieces.get(file));
            whiteBoardImage.put(Coordinate.createOnBoard(WHITE_FRONT_RANK, file), frontPieces.get(file));
        }
        return whiteBoardImage;
    }

    private static List<Piece> makeBackRank(final Camp camp) {
        return List.of(
                new Rook(camp), new Knight(camp), new Bishop(camp),
                new Queen(camp), new King(camp), new Bishop(camp),
                new Knight(camp), new Rook(camp)
        );
    }

    private static List<Piece> makeWhiteFrontRank() {
        return IntStream.range(START_INDEX, END_INDEX)
                        .mapToObj(i -> new WhitePawn())
                        .collect(Collectors.toList());
    }

    private static Map<Coordinate, Piece> makeEmptyRanks() {
        Map<Coordinate, Piece> emptyBoardImage = new HashMap<>();
        for (int rank = START_EMPTY_RANK; rank <= END_EMPTY_RANK; rank++) {
            emptyBoardImage.putAll(makeEachEmptyRank(rank));
        }
        return emptyBoardImage;
    }

    private static Map<Coordinate, Piece> makeEachEmptyRank(final int rank) {
        Map<Coordinate, Piece> emptyRank = new HashMap<>();
        for (int file = 0; file < BOARD_FILE; file++) {
            emptyRank.put(Coordinate.createOnBoard(rank, file), new Empty());
        }

        return emptyRank;
    }

    private static Map<Coordinate, Piece> makeBlackRanks() {
        List<Piece> backPieces = makeBackRank(Camp.BLACK);
        List<Piece> frontPieces = makeBlackFrontRank();

        Map<Coordinate, Piece> blackboardImage = new HashMap<>();
        for (int file = 0; file < BOARD_FILE; file++) {
            blackboardImage.put(Coordinate.createOnBoard(BLACK_FRONT_RANK, file), frontPieces.get(file));
            blackboardImage.put(Coordinate.createOnBoard(BLACK_BACK_RANK, file), backPieces.get(file));
        }
        return blackboardImage;
    }

    private static List<Piece> makeBlackFrontRank() {
        return IntStream.range(START_INDEX, END_INDEX)
                        .mapToObj(i -> new BlackPawn())
                        .collect(Collectors.toList());
    }

    public static Map<Coordinate, Piece> generate() {
        return Map.copyOf(boardImage);
    }
}
