package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DirectionVectorTest {

    @Test
    @DisplayName("북쪽으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateN() {
        Coordinate startCoordinate = Coordinate.createWithoutValidate(0, 0);
        Coordinate endCoordinate = Coordinate.createWithoutValidate(3, 0);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.NORTH);
    }

    @Test
    @DisplayName("북동으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateNE() {
        Coordinate startCoordinate = Coordinate.createWithoutValidate(0, 0);
        Coordinate endCoordinate = Coordinate.createWithoutValidate(3, 3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.NORTH_EAST);
    }

    @Test
    @DisplayName("동으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateE() {
        Coordinate startCoordinate = Coordinate.createWithoutValidate(0, 0);
        Coordinate endCoordinate = Coordinate.createWithoutValidate(0, 3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.EAST);
    }

    @Test
    @DisplayName("남동으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateSE() {
        Coordinate startCoordinate = Coordinate.createWithoutValidate(0, 0);
        Coordinate endCoordinate = Coordinate.createWithoutValidate(-3, 3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.SOUTH_EAST);
    }

    @Test
    @DisplayName("남으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateS() {
        Coordinate startCoordinate = Coordinate.createWithoutValidate(0, 0);
        Coordinate endCoordinate = Coordinate.createWithoutValidate(-3, 0);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.SOUTH);
    }

    @Test
    @DisplayName("남서로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateSW() {
        Coordinate startCoordinate = Coordinate.createWithoutValidate(0, 0);
        Coordinate endCoordinate = Coordinate.createWithoutValidate(-3, -3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.SOUTH_WEST);
    }

    @Test
    @DisplayName("서으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateW() {
        Coordinate startCoordinate = Coordinate.createWithoutValidate(0, 0);
        Coordinate endCoordinate = Coordinate.createWithoutValidate(0, -3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.WEST);
    }

    @Test
    @DisplayName("북서으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateNW() {
        Coordinate startCoordinate = Coordinate.createWithoutValidate(0, 0);
        Coordinate endCoordinate = Coordinate.createWithoutValidate(3, -3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.NORTH_WEST);
    }

    @ParameterizedTest(name = "{0}은 (1, 1)에서 ({1}, {2})로 이동한다")
    @MethodSource("moveToDirectionTestData")
    @DisplayName("특정 위치에서 DirectionVector 방향으로 한 칸 이동할 수 있다.")
    void moveToDirection(DirectionVector directionVector, Coordinate expectCoordinate) {
        Coordinate startCoordinate = Coordinate.createOnBoard(1, 1);

        Coordinate resultCoordinate = directionVector.moveToDirection(startCoordinate);

        assertThat(resultCoordinate).isEqualTo(expectCoordinate);
    }

    static Stream<Arguments> moveToDirectionTestData() {
        return Stream.of(
                Arguments.arguments(DirectionVector.NORTH, Coordinate.createOnBoard(2, 1)),
                Arguments.arguments(DirectionVector.NORTH_EAST, Coordinate.createOnBoard(2, 2)),
                Arguments.arguments(DirectionVector.EAST, Coordinate.createOnBoard(1, 2)),
                Arguments.arguments(DirectionVector.SOUTH_EAST, Coordinate.createOnBoard(0, 2)),
                Arguments.arguments(DirectionVector.SOUTH, Coordinate.createOnBoard(0, 1)),
                Arguments.arguments(DirectionVector.SOUTH_WEST, Coordinate.createOnBoard(0, 0)),
                Arguments.arguments(DirectionVector.WEST, Coordinate.createOnBoard(1, 0)),
                Arguments.arguments(DirectionVector.NORTH_WEST, Coordinate.createOnBoard(2, 0))
        );
    }
}
