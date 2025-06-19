package org.scoula.sample.service;

import org.springframework.stereotype.Service;

@Service  // Spring Bean으로 등록
public class SampleServiceImpl implements SampleService {

    @Override
    public Integer doAdd(String str1, String str2) throws Exception {
        // 핵심 비즈니스 로직: 문자열을 정수로 변환하여 덧셈
        return Integer.parseInt(str1) + Integer.parseInt(str2);
    }
}