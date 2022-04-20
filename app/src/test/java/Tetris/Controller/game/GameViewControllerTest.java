package Tetris.Controller.game;

import Tetris.Model.game.GameModel;
import Tetris.Model.game.GameStateModel;
import Tetris.Model.game.Player;
import Tetris.Model.scoreboard.ScoreModel;
import Tetris.Util.GameType;
import Tetris.Util.JsonReader;
import Tetris.View.game.GameView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameViewControllerTest {
    GameType type;
    GameType difficulty;
    GameStateModel gameStateModel;
    GameModel gameModel;
    GameViewController gameViewController;
    GameView gameView;
    Player player;

    @Test
    @DisplayName("일반모드: 스코어 업데이트 가능 여부 테스트")
    void basicModeIsPossibleUpdateScoreTest() {
        //given
        type = GameType.BASIC_MODE;
        difficulty = GameType.NORMAL;
        gameStateModel = new GameStateModel(type, difficulty);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        //when
        gameModel.getGameState().setScore(-1);

        //then
        assertFalse(gameViewController.isPossibleUpdateScore());
    }

    @Test
    @DisplayName("일반모드: 순위표 업데이트 테스트")
    void basicModeUpdateScoreTest() {
        type = GameType.BASIC_MODE;
        difficulty = GameType.NORMAL;
        gameStateModel = new GameStateModel(type, difficulty);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        List<ScoreModel> list = JsonReader.getScoreBoard(type);
        int inputScore = list.get(list.size() - 1).getScore() + 1;
        gameModel.getGameState().setScore(inputScore);
        player = new Player("김영균", gameModel.getGameState().getScore(), type, difficulty);
        gameModel.getGameState().setPlayer(player);
        gameViewController.updateScore();
        list = JsonReader.getScoreBoard(type);
        ScoreModel lastScore = new ScoreModel(inputScore, "김영균", difficulty.getKey());

        assertEquals(lastScore.getName(), list.get(list.size() - 1).getName());
        assertEquals(lastScore.getScore(), list.get(list.size() - 1).getScore());
        assertEquals(lastScore.getDifficulty(), list.get(list.size() - 1).getDifficulty());
    }

    @Test
    @DisplayName("아이템모드: 순위 변경 시 순위표 업데이트 테스트")
    void itemModeUpdateScoreTest() {
        type = GameType.ITEM_MODE;
        gameStateModel = new GameStateModel(type);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);
        List<ScoreModel> list = JsonReader.getScoreBoard(type);
        int inputScore = list.get(list.size() - 1).getScore() + 1;

        gameModel.getGameState().setScore(inputScore);
        player = new Player("김영균", gameModel.getGameState().getScore(), type);
        gameModel.getGameState().setPlayer(player);
        gameViewController.updateScore();
        list = JsonReader.getScoreBoard(type);
        ScoreModel lastScore = new ScoreModel(inputScore, "김영균");

        assertEquals(lastScore.getScore(), list.get(list.size() - 1).getScore());
        assertEquals(lastScore.getName(), list.get(list.size() - 1).getName());
    }

    @Test
    @DisplayName("일시정지 true -> false 전환 테스트")
    void reversePauseTest1() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameViewController = new GameViewController(gameModel);

        gameModel.getGameState().setPaused(true);
        gameViewController.reversePause();
        assertFalse(gameModel.getGameState().isPaused());
    }
    @Test
    @DisplayName("일시정지 false -> true 전환 테스트")
    void reversePauseTest2() {
        gameStateModel = new GameStateModel(GameType.BASIC_MODE);
        gameModel = new GameModel(gameStateModel, 20, 10);
        gameView = new GameView(0, 0, gameModel);
        gameView.dispose();
        gameViewController = new GameViewController(gameModel, gameView);

        gameModel.getGameState().setPaused(false);
        gameViewController.reversePause();
        assertTrue(gameModel.getGameState().isPaused());
    }
}