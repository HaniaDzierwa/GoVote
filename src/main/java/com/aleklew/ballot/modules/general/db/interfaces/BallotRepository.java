package com.aleklew.ballot.modules.general.db.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleklew.ballot.modules.general.db.dbmodels.Ballot;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, Integer> {
}
