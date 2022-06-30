import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void whenPerfectGameOccursThenScoreShouldBe300(){
        final int score = Game.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).score();
        Assertions.assertEquals(300,score);
    }

    @Test
    void whenRollCounterIsOutOfBoundThenExceptionShouldBeThrown(){
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Game.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).score()
        );
    }

    @Test
    void whenOpenFrameOccursInLastFrameThenExtraRollShouldNotBeIncluded(){
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Game.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 3, 1).score()
        );
    }

    @Test
    void whenStrikeOccursInLastFrameThenExtraRollCanBeIncluded(){
        Assertions.assertDoesNotThrow(
                () -> Game.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).score()
        );
    }

    @Test
    void whenSpareOccursInLastFrameThenExtraRollCanBeIncluded(){
        Assertions.assertDoesNotThrow(
                () -> Game.of(10, 10, 10, 10, 10, 10, 10, 10, 10, 5, 5, 10).score()
        );
    }

    @Test
    void whenSpareOccursThenNextRollPointsAreDoubled(){
        final int score = Game.of(5, 5, 4).score();
        Assertions.assertEquals(18,score);
    }

    @Test
    void whenStrikeOccursThenNextTwoRollPointsAreDoubled(){
        final int score = Game.of(10, 5, 4).score();
        Assertions.assertEquals(28,score);
    }

    @Test
    void whenFrameIsOpenThenPointsShouldBeAddedWithoutExtras(){
        final int score = Game.of(1, 2, 3, 4).score();
        Assertions.assertEquals(10,score);
    }

    @Test
    void whenFoulOccursThenPointsShouldNotBeIncludedInScore(){
        final int score = new Game()
                .roll(10, true)
                .score();
        Assertions.assertEquals(0,score);
    }


}
