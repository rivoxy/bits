package com.bits.kata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rivo
 */
@Entity
@Table(name = "GAME_SCORE", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "GameScore.findAll", query = "SELECT g FROM GameScore g"),
    @NamedQuery(name = "GameScore.findByRankSet", query = "SELECT g FROM GameScore g WHERE g.rankSet = :rankSet"),
    @NamedQuery(name = "GameScore.findByPlayerName", query = "SELECT g FROM GameScore g WHERE g.playerName = :playerName"),
    @NamedQuery(name = "GameScore.findByPlayerScore", query = "SELECT g FROM GameScore g WHERE g.playerScore = :playerScore"),
//    @NamedQuery(name = "GameScore.findByPlayerNameAndMatchId", query = "SELECT g FROM GameScore g WHERE g.playerName = :playerName and g.matchId = :matchId"),
    @NamedQuery(name = "GameScore.findById", query = "SELECT g FROM GameScore g WHERE g.id = :id")})
public class GameScore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "RANK_SET", nullable = false)
    private int rankSet;

    @Size(max = 50)
    @Column(name = "PLAYER_NAME", length = 50)
    private String playerName;

    @Column(name = "PLAYER_SCORE", length = 3)
    private String playerScore = "0";

    @JsonIgnore
    @JoinColumn(name = "MATCH_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RunMatch matchId;

    public GameScore() {
    }

    public GameScore(Integer id) {
        this.id = id;
    }

    public GameScore(Integer id, int rankSet, String playerScore) {
        this.id = id;
        this.rankSet = rankSet;
        this.playerScore = playerScore;
    }

    public int getRankSet() {
        return rankSet;
    }

    public void setRankSet(int rankSet) {
        this.rankSet = rankSet;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(String playerScore) {
        this.playerScore = playerScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RunMatch getMatchId() {
        return matchId;
    }

    public void setMatchId(RunMatch matchId) {
        this.matchId = matchId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GameScore)) {
            return false;
        }
        GameScore other = (GameScore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game [ id=" + id + " n° " + rankSet + " Player : " + playerName + " Score : " + playerScore + " ]";
    }

}
