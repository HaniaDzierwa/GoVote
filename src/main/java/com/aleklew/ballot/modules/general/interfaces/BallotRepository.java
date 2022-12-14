package com.aleklew.ballot.modules.general.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleklew.ballot.modules.general.dbmodels.Ballot;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, Integer> {
}
