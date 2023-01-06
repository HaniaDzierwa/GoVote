package com.aleklew.ballot.modules.general.rest.controllers;

import com.aleklew.ballot.modules.general.db.interfaces.ITestDataRepository;
import com.aleklew.ballot.modules.general.models.test.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    private ITestDataRepository dataRepository;

    // region BASE

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "OK";
    }

    @RequestMapping(value = "/testMap", method = RequestMethod.GET)
    public Map<String, String> testMap() {
        Map<String, String> testResultMap = new HashMap<>();
        testResultMap.put("testStatus", "TEST PASSED");
        return testResultMap;
    }

    // endregion

    @RequestMapping(value = "/testSelect", method = RequestMethod.GET)
    public List<TestModel> testSelect() {
        List<TestModel> testModelList = dataRepository.findAll();
        return testModelList;
    }

}
