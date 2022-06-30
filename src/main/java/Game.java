public class Game {

    private final int[] rolls = new int[21];
    private int rollCounter = 0;

    public static Game of(int... rolls){
        final Game game = new Game();
        for (int roll : rolls) {
            game.roll(roll);
        }
        return game;
    }

    public void roll(int knockedDownPins){
        roll(knockedDownPins, false);
    }

    public Game roll(int knockedDownPins, boolean foul) {
        if (rollCounter > 20 || (rollCounter == 20 && (rolls[18] + rolls[19] < 10))) {
            throw new IllegalArgumentException("Game is over");
        }
        if (!foul){
            this.rolls[rollCounter] = knockedDownPins;
        }
        if (rollCounter % 2 == 0 && knockedDownPins == 10 && rollCounter < 18) {
            rollCounter++;
        }
        rollCounter++;
        return this;
    }

    public int score() {
        int totalScore = 0;
        for (int i = 0; i < 20; i += 2) {
            boolean isStrike = rolls[i] == 10;
            boolean isSpare = (rolls[i] + rolls[i + 1]) == 10;
            boolean isLastFrame = i == 18;
            boolean isLastButOneFrame = i == 16;

            if (isLastFrame){
                totalScore += rolls[18] + rolls[19] + rolls[20];
            } else if (isStrike && isLastButOneFrame){
                totalScore += rolls[16] + rolls[18] + rolls[19];
            } else if (isStrike) {
                boolean isNextStrike = rolls[i + 2] == 10;
                if (isNextStrike){
                    totalScore += rolls[i] + rolls[i + 2] + rolls[i + 4];
                } else {
                    totalScore += rolls[i] + rolls[i + 2] + rolls[i + 3];
                }
            } else if (isSpare) {
                totalScore += rolls[i] + rolls[i + 1] + rolls[i + 2];
            } else {
                totalScore += rolls[i] + rolls[i + 1];
            }
        }
        return totalScore;
    }
}
