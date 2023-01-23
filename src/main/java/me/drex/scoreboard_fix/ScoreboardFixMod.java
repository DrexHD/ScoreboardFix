package me.drex.scoreboard_fix;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;

import java.util.Map;

public class ScoreboardFixMod implements ModInitializer {

    @Override
    public void onInitialize() {
    }

    public static void updateScoreboardEntries(Scoreboard scoreboard, String previousName, String updatedName) {
        Map<Objective, Score> playerScores = scoreboard.getPlayerScores(previousName);
        for (Map.Entry<Objective, Score> entry : playerScores.entrySet()) {
            Score score = scoreboard.getOrCreatePlayerScore(updatedName, entry.getKey());
            score.setScore(entry.getValue().getScore());
        }
        // Reset old scores
        scoreboard.resetPlayerScore(previousName, null);
    }
}
