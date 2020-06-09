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
@Table(name = "MATCHSETS", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "MatchSets.findAll", query = "SELECT m FROM MatchSets m"),
    @NamedQuery(name = "MatchSets.findById", query = "SELECT m FROM MatchSets m WHERE m.id = :id"),
    @NamedQuery(name = "MatchSets.findByRankSet", query = "SELECT m FROM MatchSets m WHERE m.rankSet = :rankSet"),
    @NamedQuery(name = "MatchSets.findByWinner", query = "SELECT m FROM MatchSets m WHERE m.winner = :winner"),
    @NamedQuery(name = "MatchSets.findByPlayerName", query = "SELECT m FROM MatchSets m WHERE m.playerName = :playerName"),
    @NamedQuery(name = "MatchSets.findByPlayerScore", query = "SELECT m FROM MatchSets m WHERE m.playerScore = :playerScore")})
public class MatchSets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "RANK_SET")
    private int rankSet;

    @Size(max = 50)
    @Column(name = "WINNER", length = 50)
    private String winner;

    @Size(max = 50)
    @Column(name = "PLAYER_NAME", length = 50)
    private String playerName;

    @Column(name = "PLAYER_SCORE")
    private int playerScore;

    @JsonIgnore
    @JoinColumn(name = "MATCH_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RunMatch matchId;

    public MatchSets() {
    }

    public MatchSets(Integer id) {
        this.id = id;
    }

    public MatchSets(Integer id, int rankSet, int playerScore) {
        this.id = id;
        this.rankSet = rankSet;
        this.playerScore = playerScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRankSet() {
        return rankSet;
    }

    public void setRankSet(int rankSet) {
        this.rankSet = rankSet;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
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
        if (!(object instanceof MatchSets)) {
            return false;
        }
        MatchSets other = (MatchSets) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Set [ id=" + id + " n° " + rankSet + " Player : " + playerName + " Score : " + playerScore + " Winner : " + winner + " ]";
    }

}
