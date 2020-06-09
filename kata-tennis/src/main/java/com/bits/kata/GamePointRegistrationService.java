package com.bits.kata;

import com.bits.kata.model.GameScore;
import com.bits.kata.model.RunMatch;
import com.bits.kata.repositories.GameScoreRepository;
import com.bits.kata.repositories.RunMatchRepository;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rivo
 */
@Service
public class GamePointRegistrationService implements Serializable {

    @Autowired
    private MatchSetsPointRegistrationService mSetsPointRegistrationService;

    @Autowired
    private GameScoreRepository gScoreRepository;

    @Autowired
    private RunMatchRepository mRepository;

    private String playerName;

    private RunMatch match;

    private GameScore currentPlayerPointWinner;

    private GameScore opponentPlayerScore;

    public String greet(String name) {
        return (name == null || name.isEmpty()) ? "Hello anonymous" : "Hello " + name;
    }

    @Transactional
    public void registerPlayerPoint(Integer matchId, String playerName) {
        Optional<RunMatch> resOpt = mRepository.findById(matchId);
        if (resOpt == null) {
            throw new RuntimeException("L'identifiant fournit ne renvoi aucune donnée !");
        }
        registerPlayerPoint(resOpt.get(), playerName);
    }

    @Transactional
    public void registerPlayerPoint(RunMatch m, String playerName) {
        this.match = m;
        this.playerName = playerName;
        initializeCurrentPlayersScores();
        computeCurrentPlayerPoints();

    }

    private void initializeCurrentPlayersScores() {
//        currentPlayerPointWinner = gScoreRepository.findByPlayerNameAndMatchId(playerName, match.getId());
        currentPlayerPointWinner = gScoreRepository.findByPlayerNameAndMatchId(playerName, match);
        if (currentPlayerPointWinner == null) {
            throw new RuntimeException("Le nom du joueur est incorrect ou le couple joueur et id du match n'existe pas !");
        }
        opponentPlayerScore = gScoreRepository.findByMatchIdAndIdNot(match, currentPlayerPointWinner.getId());
    }

    private void computeCurrentPlayerPoints() {

        GameScoreEnum currentPlayerScoreEnum = getCurrentPlayerScoreEnum();
        GameScoreEnum opponentPlayerScoreEnum = findCorrespondingGameScoreEnum(opponentPlayerScore.getPlayerScore());

        if (GameScoreEnum.PADV.equals(currentPlayerScoreEnum) || (GameScoreEnum.P40.equals(currentPlayerScoreEnum) && (currentPlayerScoreEnum.ordinal() > opponentPlayerScoreEnum.ordinal() || !isDeuceRuleEnabled()))) {
            currentPlayerHasWonTheGame();
            return;
        }

        if (currentPlayerScoreEnum.equals(GameScoreEnum.P40) && currentPlayerScoreEnum.ordinal() < opponentPlayerScoreEnum.ordinal()) {
            currentPlayerPointWinner.setPlayerScore(GameScoreEnum.P40.toString());
            opponentPlayerScore.setPlayerScore(GameScoreEnum.P40.toString());

            gScoreRepository.save(currentPlayerPointWinner);
            gScoreRepository.save(opponentPlayerScore);
            return;
        }

        increaseCurrentPlayerWinnerScore();

    }

    private void increaseCurrentPlayerWinnerScore() {
        GameScoreEnum currentPlayerScoreEnum = getCurrentPlayerScoreEnum();
        GameScoreEnum[] values = GameScoreEnum.values();
        final int nextScore = 1 + currentPlayerScoreEnum.ordinal();
        GameScoreEnum nextScoreEnum = values[nextScore];
        currentPlayerPointWinner.setPlayerScore(nextScoreEnum.toString());
        gScoreRepository.save(currentPlayerPointWinner);
    }

    private void currentPlayerHasWonTheGame() {
        computeMatchSetsPoint();
        intializeGameScoreForNextSet();
    }

    private void computeMatchSetsPoint() {
        mSetsPointRegistrationService.registerPlayerPoint(match, playerName, currentPlayerPointWinner.getRankSet());
    }

    private void intializeGameScoreForNextSet() {
        currentPlayerPointWinner.setPlayerScore(GameScoreEnum.P0.toString());
        opponentPlayerScore.setPlayerScore(GameScoreEnum.P0.toString());
        gScoreRepository.save(currentPlayerPointWinner);
        gScoreRepository.save(opponentPlayerScore);
    }

    private GameScoreEnum getCurrentPlayerScoreEnum() {
        String playerScore = currentPlayerPointWinner.getPlayerScore();
        return findCorrespondingGameScoreEnum(playerScore);
    }

    private GameScoreEnum findCorrespondingGameScoreEnum(String playerScore) {
        Stream<GameScoreEnum> str = Arrays.stream(GameScoreEnum.values());
        final GameScoreEnum currentPlayerScoreEnum = str.filter(e -> e.toString().equalsIgnoreCase(playerScore)).findFirst().get();
        return currentPlayerScoreEnum;
    }

    private boolean isDeuceRuleEnabled() {
        return YesNoEnum.Y.equals(match.getEnableDeuceRule());
    }

}
