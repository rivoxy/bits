package com.bits.kata.repositories;

import com.bits.kata.model.MatchSets;
import com.bits.kata.model.RunMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rivo
 */
@Repository
public interface MatchSetsRepository extends JpaRepository<MatchSets, Integer> {

//    @Query("SELECT g FROM MatchSets g WHERE g.matchId != :matchId and g.rankSet = :rankSet and g.playerName = :playerName")
    MatchSets findByMatchIdAndRankSetAndPlayerName(@Param("matchId") RunMatch matchId, @Param("rankSet") int rankSet, @Param("playerName") String playerName);

//    @Query("SELECT g FROM MatchSets g WHERE g.matchId != :matchId and g.rankSet = :rankSet and g.playerName != :playerName")
    MatchSets findByMatchIdAndRankSetAndPlayerNameNot(@Param("matchId") RunMatch matchId, @Param("rankSet") int rankSet, @Param("playerName") String playerName);

}
