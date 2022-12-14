package com.aleklew.ballot.modules.general.db.interfaces;

import com.aleklew.ballot.modules.general.models.test.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITestDataRepository extends JpaRepository<TestModel, Integer> {
}
