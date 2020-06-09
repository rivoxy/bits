package com.bits.kata.repositories;

import com.bits.kata.model.GameScore;
import com.bits.kata.model.RunMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rivo
 */
@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Integer> {

    @Query(nativeQuery = true, value = "SELECT g FROM GAME_SCORE g WHERE g.PLAYER_NAME = ?1 and g.MATCH_ID = ?2")
    GameScore findByPlayerNameAndMatchId(String playerName, Integer matchId);

//    @Query("SELECT g FROM GameScore g WHERE g.playerName = :playerName and g.matchId = :matchId")
    GameScore findByPlayerNameAndMatchId(@Param("playerName") String playerName, @Param("matchId") RunMatch matchId);

//    @Query("SELECT g FROM GameScore g WHERE g.id != :gScoreId and g.matchId = :matchId")
//    GameScore findByMatchIdAndIdNot(@Param("matchId") Integer matchId, @Param("gScoreId") Integer gScoreId);
//    @Query("SELECT g FROM GameScore g WHERE g.id != :gScoreId and g.matchId = :matchId")
    GameScore findByMatchIdAndIdNot(@Param("matchId") RunMatch matchId, @Param("gScoreId") Integer gScoreId);

}
