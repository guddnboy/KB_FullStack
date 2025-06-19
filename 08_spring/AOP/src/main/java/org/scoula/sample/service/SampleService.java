package org.scoula.sample.service;

public interface SampleService {
    /**
     * 두 문자열을 정수로 변환하여 덧셈 연산 수행
     * @param str1 첫 번째 숫자 문자열
     * @param str2 두 번째 숫자 문자열
     * @return 덧셈 결과
     * @throws Exception 숫자 변환 실패 시 예외 발생
     */
    public Integer doAdd(String str1, String str2) throws Exception;
}
