package com.aleklew.ballot.modules.general.controllers;

import com.aleklew.ballot.modules.general.interfaces.ITestDataRepository;
import com.aleklew.ballot.modules.general.models.test.TestModel;
import com.aleklew.ballot.modules.general.services.SharedMailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/public/test")
public class TestController {

    private final ITestDataRepository dataRepository;
    private final SharedMailingService sharedMailingService;

    public TestController(ITestDataRepository dataRepository, SharedMailingService sharedMailingService) {
        this.dataRepository = dataRepository;
        this.sharedMailingService = sharedMailingService;
    }

    // region BASE

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        sharedMailingService.sendActivateAccountMail("ola.lewandowska12@gmail.com", "some guid");
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
