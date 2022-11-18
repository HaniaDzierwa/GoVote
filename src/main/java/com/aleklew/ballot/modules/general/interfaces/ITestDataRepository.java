package com.aleklew.ballot.modules.general.interfaces;

import com.aleklew.ballot.modules.general.models.test.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITestDataRepository extends JpaRepository<TestModel, Integer> {
}
