package com.bits.kata;

import com.bits.kata.model.GameScore;
import com.bits.kata.model.MatchSets;
import com.bits.kata.model.RunMatch;
import com.bits.kata.repositories.GameScoreRepository;
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
public class GameInitializerService {

    @Autowired
    private RunMatchRepository mRepository;

    @Autowired
    private MatchSetsRepository mSetsRepository;

    @Autowired
    private GameScoreRepository gScoreRepository;

    @Transactional
    public RunMatch updateRules(Integer matchId, YesNoEnum enableDeuce, YesNoEnum enableTieBreak) {
        RunMatch m = mRepository.findById(matchId).get();
        m.setEnableDeuceRule(enableDeuce);
        m.setEnableTieBreakRule(enableTieBreak);
        mRepository.save(m);

        return m;
    }

    @Transactional
    public RunMatch createNewGame(String player1, String player2, YesNoEnum enableDeuce, YesNoEnum enableTieBreak) {
        RunMatch m = new RunMatch();
        m.setPlayer1(player1);
        m.setPlayer2(player2);

        if (enableDeuce != null) {
            m.setEnableDeuceRule(enableDeuce);
        }
        if (enableTieBreak != null) {
            m.setEnableTieBreakRule(enableTieBreak);
        }

        m = mRepository.save(m);

        MatchSets mSetsP1 = new MatchSets();
        mSetsP1.setMatchId(m);
        mSetsP1.setPlayerName(player1);
        mSetsP1.setPlayerScore(0);
        mSetsP1.setWinner("NONE");
        mSetsP1.setRankSet(1);

        MatchSets mSetsP2 = new MatchSets();
        mSetsP2.setMatchId(m);
        mSetsP2.setPlayerName(player2);
        mSetsP2.setPlayerScore(0);
        mSetsP2.setWinner("NONE");
        mSetsP2.setRankSet(1);

        mSetsRepository.save(mSetsP1);
        mSetsRepository.save(mSetsP2);

        GameScore gs = new GameScore();
        gs.setMatchId(m);
        gs.setPlayerName(player1);
        gs.setPlayerScore("0");
        gs.setRankSet(1);

        GameScore gs2 = new GameScore();
        gs2.setMatchId(m);
        gs2.setPlayerName(player2);
        gs2.setPlayerScore("0");
        gs2.setRankSet(1);

        gScoreRepository.save(gs);
        gScoreRepository.save(gs2);

        return m;
    }

}
