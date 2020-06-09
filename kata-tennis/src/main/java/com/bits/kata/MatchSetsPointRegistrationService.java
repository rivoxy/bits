package com.bits.kata;

import com.bits.kata.model.MatchSets;
import com.bits.kata.model.RunMatch;
import com.bits.kata.repositories.MatchSetsRepository;
import com.bits.kata.repositories.RunMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rivo
 */
@Service
public class MatchSetsPointRegistrationService {

    @Autowired
    private MatchSetsRepository mSetsRepository;

    @Autowired
    private RunMatchRepository matchRepository;

    private MatchSets currentPlayerPointWinner;

    private MatchSets opponentPlayerPoint;

    private RunMatch match;

    private String playerName;

    private int rankSet;

    @Transactional
    public void registerPlayerPoint(RunMatch m, String playerName, int rankSet) {
        this.match = m;
        this.playerName = playerName;
        this.rankSet = rankSet;
        initializeCurrentPlayersScores();
        computeCurrentPlayerPoints();

    }

    private void initializeCurrentPlayersScores() {
        currentPlayerPointWinner = mSetsRepository.findByMatchIdAndRankSetAndPlayerName(match, rankSet, playerName);
        opponentPlayerPoint = mSetsRepository.findByMatchIdAndRankSetAndPlayerNameNot(match, rankSet, playerName);
    }

    private void computeCurrentPlayerPoints() {
        int playerScore = currentPlayerPointWinner.getPlayerScore();
        int opponentScore = opponentPlayerPoint.getPlayerScore();
        int diff = playerScore - opponentScore;

        if ((playerScore == 5 && opponentScore <= 4 || (playerScore == 6 && opponentScore >= 4 && !isTieBreakRuleEnabled())) || (playerScore >= 7 && diff >= 2)) {
            currentPlayerHasWonTheSet();
            return;
        }

        currentPlayerPointWinner.setPlayerScore(currentPlayerPointWinner.getPlayerScore() + 1);
        mSetsRepository.save(currentPlayerPointWinner);

    }

    public void currentPlayerHasWonTheSet() {
        currentPlayerPointWinner.setPlayerScore(currentPlayerPointWinner.getPlayerScore() + 1);
        currentPlayerPointWinner.setPlayerName(playerName);
        opponentPlayerPoint.setPlayerName(playerName);

        mSetsRepository.save(currentPlayerPointWinner);
        mSetsRepository.save(opponentPlayerPoint);

//        computeNextSet();
        endMatch();
    }

    private boolean isTieBreakRuleEnabled() {
        return YesNoEnum.Y.equals(match.getEnableTieBreakRule());
    }

//    private void computeNextSet() {
    // The match is only played in 1 set !!
//    }
    private void endMatch() {
        match.setWinner(playerName);
        matchRepository.save(match);
    }
}
