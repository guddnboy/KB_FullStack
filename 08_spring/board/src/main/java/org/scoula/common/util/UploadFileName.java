package org.scoula.common.util;

public class UploadFileName {
    /**
     * 고유한 파일명을 생성하는 메서드
     * @param filename 원본 파일명
     * @return timestamp가 추가된 고유 파일명
     */
    public static String getUniqueName(String filename) {
        // 파일명과 확장자를 분리
        int ix = filename.lastIndexOf(".");
        String name = filename.substring(0, ix);        // 파일명 부분 추출
        String ext = filename.substring(ix + 1);        // 확장자 부분 추출

        // 현재 시간을 밀리초로 변환하여 고유성 보장
        return String.format("%s-%d.%s", name, System.currentTimeMillis(), ext);
    }
}