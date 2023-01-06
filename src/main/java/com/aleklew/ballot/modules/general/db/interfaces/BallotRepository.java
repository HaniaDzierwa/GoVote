package com.aleklew.ballot.modules.general.db.interfaces;

import com.aleklew.ballot.modules.general.db.dbmodels.BallotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallotRepository extends JpaRepository<BallotEntity, Integer> {
}
