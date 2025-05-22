package org.scoula.travel.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.scoula.database.JDBCUtil;
import org.scoula.travel.domain.TravelVO;

import java.util.List;
import java.util.Optional;

// Assertions 클래스에서 제공하는 static 메서드 호출 시 클래스명 생략
import static org.junit.jupiter.api.Assertions.*;

class TravelDaoTest {

    private TravelDao dao = new TravelDaoImpl();

    /**
     * 모든 테스트 완료 후 데이터베이스 연결 종료
     */
    @AfterAll
    static void tearDown() {
        JDBCUtil.close();
    }


    /**
     * 전체 여행지 개수 조회 기능 테스트
     */
    @Test
    @DisplayName("전체 여행지 개수 조회")
    void getTotalCount() {

        // 전체 여행지 개수 조회
        int count = dao.getTotalCount();
        System.out.println("전체 여행지 개수: " + count);

        // 개수가 0보다 큰지 검증 (데이터가 존재하는지 확인)
        assertTrue(count > 0, "여행지 데이터 없음");
    }

    /**
     * 전체 여행지 목록 조회 기능 테스트
     */
    @Test
    @DisplayName("전체 여행지 목록 조회")
    void getTravels() {

        // 전체 여행지 목록 조회
        List<TravelVO> travels = dao.getTravels();

        // 여행지 목록이 비어있지 않은지 검증
        assertNotNull(travels, "여행지 목록 null임");
        assertFalse(travels.isEmpty(), "여행지 목록 없음");

        // 여행지 정보 출력
        System.out.println("=== 전체 여행지 목록 ===");
        travels.forEach(travel -> System.out.println(travel.getDistrict() + " - " + travel.getTitle()));

        System.out.println("총 여행지 개수 : " + travels.size());

    }

    /**
     * 페이지별 여행지 목록 조회 기능 테스트
     */
    @Test
    @DisplayName("페이지별 여행지 목록 조회")
    void testGetTravels() {

        int page = 1; // 첫 번째 페이지(바꿔가며 테스트)

        // 첫 번째 페이지 여행지 목록 조회
        List<TravelVO> travels = dao.getTravels(page);

        // 페이징 결과 검증
        assertNotNull(travels, "페이징된 여행지 목록 null임");
        assertFalse(travels.isEmpty(), "페이징된 여행지 목록 없음");
        assertTrue(travels.size() <= 10, "페이지당 최대 10개 초과");

        System.out.println("=== "+ page + "페이지 여행지 목록 ===");
        travels.forEach(travel ->
                System.out.println(travel.getNo() + ": " + travel.getDistrict() + " - " + travel.getTitle()));

        System.out.println(page + "페이지 여행지 개수 : " + travels.size());
    }

    /**
     * 특정 지역별 여행지 목록 조회 기능 테스트
     */
    @Test
    @DisplayName("특정 지역 여행지 목록 조회")
    void testGetTravels1() {

        String district = "강원권";  // 강원권, 경상권, 수도권 등 권역 입력

        // 해당 지역의 여행지 목록 조회
        List<TravelVO> travels = dao.getTravels(district);

        // 결과 검증
        assertNotNull(travels, "지역별 여행지 목록 null임");

        System.out.println("=== '" + district + "' 여행지 목록 ===");
        travels.forEach(travel ->
                System.out.println(travel.getNo() + ": " + travel.getTitle()));

        System.out.println("'" + district + "' 여행지 개수 :  " + travels.size() + "개");
    }

    /**
     * 특정 여행지 상세 조회 기능 테스트
     */
    @Test
    @DisplayName("특정 여행지 상세 정보 조회")
    void getTravel() {

        Long travelNo = 1L; // 여행지 번호 입력(long 형)

        // 특정 여행지 상세 정보 조회
        Optional<TravelVO> optionalTravel = dao.getTravel(travelNo);

        // Optional 결과 검증
        assertTrue(optionalTravel.isPresent(), "해당 번호의 여행지 정보가 없음");

        TravelVO travel = optionalTravel.get();

        // 상세 정보 출력
        System.out.println("=== 여행지 상세 정보 ===");
        System.out.println("번호: " + travel.getNo());
        System.out.println("지역: " + travel.getDistrict());
        System.out.println("제목: " + travel.getTitle());
        System.out.println("설명: " + travel.getDescription());
        System.out.println("주소: " + travel.getAddress());
        System.out.println("전화번호: " + travel.getPhone());

        // 이미지 정보 확인
        if (travel.getImages() != null && !travel.getImages().isEmpty()) {
            System.out.println("=== 연관 이미지 정보 ===");
            travel.getImages().forEach(image ->
                    System.out.println("- " + image.getFilename()));
        } else {
            System.out.println("연관된 이미지가 없습니다.");
        }
    }
}