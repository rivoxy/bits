package com.bits.kata.repositories;

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
public interface RunMatchRepository extends JpaRepository<RunMatch, Integer> {

    @Query("SELECT g FROM RunMatch g WHERE g.id != :id and (g.player1 = :playerName or g.player2 = :playerName)")
    RunMatch findByIdAndPlayerName(@Param("id") Integer id, @Param("playerName") String playerName);
}
