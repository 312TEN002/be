package com.be.poten.service;

import com.be.poten.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;

    @Transactional(readOnly = true)
    public String getTestData() {
        return testMapper.getTestData();
    }
}
