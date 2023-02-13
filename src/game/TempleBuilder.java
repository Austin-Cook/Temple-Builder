package game;

import java.io.IOException;

public class TempleBuilder {

    public static void main(String[] args) throws IOException, InterruptedException {
        GameQuests gameQuests = new GameQuests();

        // Player Selects Options
        String[] playerSelections = gameQuests.startGame();
        String characterNum = playerSelections[0];
        String weaponNum = playerSelections[1];
        String questNum = playerSelections[2];

        // Begin the Quest
        gameQuests.startQuest(characterNum, weaponNum, questNum);
    }
}
