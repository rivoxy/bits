package com.bits.kata.model;

import com.bits.kata.YesNoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rivo
 */
@Entity
@Table(name = "RUNMATCH", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "RunMatch.findAll", query = "SELECT r FROM RunMatch r"),
    @NamedQuery(name = "RunMatch.findById", query = "SELECT r FROM RunMatch r WHERE r.id = :id"),
    @NamedQuery(name = "RunMatch.findByPlayer1", query = "SELECT r FROM RunMatch r WHERE r.player1 = :player1"),
    @NamedQuery(name = "RunMatch.findByPlayer2", query = "SELECT r FROM RunMatch r WHERE r.player2 = :player2"),
    @NamedQuery(name = "RunMatch.findByWinner", query = "SELECT r FROM RunMatch r WHERE r.winner = :winner")})
public class RunMatch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 1, max = 50)
    @Column(name = "PLAYER_1", length = 50)
    private String player1;

    @Size(min = 1, max = 50)
    @Column(name = "PLAYER_2", length = 50)
    private String player2;

    @Size(min = 1, max = 50)
    @Column(name = "WINNER", length = 50)
    private String winner = "NONE";

    @Enumerated(EnumType.STRING)
    @Column(name = "ENABLEDEUCERULE")
    private YesNoEnum enableDeuceRule;

    @Enumerated(EnumType.STRING)
    @Column(name = "ENABLETIEBREAKRULE")
    private YesNoEnum enableTieBreakRule;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matchId", fetch = FetchType.LAZY)
    private List<GameScore> gameScoreList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matchId", fetch = FetchType.LAZY)
    private List<MatchSets> matchSetsList;

    public RunMatch() {
    }

    public RunMatch(Integer id) {
        this.id = id;
    }

    public RunMatch(Integer id, String player1, String player2, String winner) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     * @return the enableDeuceRule
     */
    public YesNoEnum getEnableDeuceRule() {
        return enableDeuceRule;
    }

    /**
     * @param enableDeuceRule the enableDeuceRule to set
     */
    public void setEnableDeuceRule(YesNoEnum enableDeuceRule) {
        this.enableDeuceRule = enableDeuceRule;
    }

    /**
     * @return the enableTieBreakRule
     */
    public YesNoEnum getEnableTieBreakRule() {
        return enableTieBreakRule;
    }

    /**
     * @param enableTieBreakRule the enableTieBreakRule to set
     */
    public void setEnableTieBreakRule(YesNoEnum enableTieBreakRule) {
        this.enableTieBreakRule = enableTieBreakRule;
    }

    public List<GameScore> getGameScoreList() {
        return gameScoreList;
    }

    public void setGameScoreList(List<GameScore> gameScoreList) {
        this.gameScoreList = gameScoreList;
    }

    public List<MatchSets> getMatchSetsList() {
        return matchSetsList;
    }

    public void setMatchSetsList(List<MatchSets> matchSetsList) {
        this.matchSetsList = matchSetsList;
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
        if (!(object instanceof RunMatch)) {
            return false;
        }
        RunMatch other = (RunMatch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tennis match id=" + id + " ]" + " Player 1 : " + player1 + " - Player 2 : " + player2 + " Winner : " + winner + " Deuce : " + enableDeuceRule + " Tie break : " + enableTieBreakRule;
    }

}
