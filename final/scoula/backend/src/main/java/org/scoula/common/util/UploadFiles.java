package org.scoula.common.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class UploadFiles {
    /**
     * 파일을 지정된 디렉토리에 업로드하는 메서드
     * @param baseDir 기본 저장 디렉토리
     * @param part 업로드된 파일 객체
     * @return 저장된 파일의 전체 경로
     * @throws IOException 파일 처리 중 오류 발생 시
     */
    public static String upload(String baseDir, MultipartFile part) throws IOException {
        // 기본 디렉토리 존재 여부 확인 및 생성
        File base = new File(baseDir);
        if (!base.exists()) {
            base.mkdirs();  // 중간 디렉토리까지 모두 생성
        }

        // 원본 파일명 획득
        String fileName = part.getOriginalFilename();

        // 고유한 파일명으로 대상 파일 생성
        File dest = new File(baseDir, UploadFileName.getUniqueName(fileName));

        // 업로드된 파일을 지정된 경로로 이동
        part.transferTo(dest);

        // 저장된 파일의 전체 경로 반환
        return dest.getPath();
    }

    /**
     * 파일 크기를 사용자 친화적 형태로 변환
     * @param size 바이트 단위 파일 크기
     * @return 포맷된 문자열 (예: 1.2 MB)
     */
    public static String getFormatSize(Long size) {
        if (size <= 0) return "0";

        final String[] units = new String[]{"Bytes", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));

        return new DecimalFormat("#,##0.#")
                .format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }



    /**
     * 파일 다운로드 처리
     * @param response HTTP 응답 객체
     * @param file 다운로드할 파일
     * @param orgName 원본 파일명 (다운로드 시 표시될 이름)
     * @throws Exception
     */
    public static void download(HttpServletResponse response, File file, String orgName)
            throws Exception {

        /* *** 응답 헤더 설정 *** */

        // application/download
        // - 범용 다운로드 타입을 나타내는 MIME TYPE
        // - 브라우저가 미리보기를 시도하지 않고 다운로드 시도
        response.setContentType("application/download");


        // Content-Length
        // - 브라우저에게 전송될 데이터의 크기를 미리 알려주는 중요 응답 헤더
        // - 다운로드 진행율 표시 가능, 연결 최적화(HTTP Keep-Alive), 브라우저 메모리 최적화
        response.setContentLength((int) file.length());


        // 한글 파일명 인코딩 (UTF-8)
        String filename = URLEncoder.encode(orgName, "UTF-8");


        // Content-disposition
        // - 브라우저가 응답을 어떻게 처리하지 지정하는 HTTP 헤더

        // attachment;filename="파일명"
        // - 지정된 "파일명"으로 다운로드 처리를 지시
        response.setHeader("Content-disposition",
                "attachment;filename=\"" + filename + "\"");

        // 파일 스트림을 응답으로 전송
        try (OutputStream os = response.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(os)) {

            Files.copy(Paths.get(file.getPath()), bos);
        }
    }
}
