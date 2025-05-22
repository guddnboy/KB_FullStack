package org.scoula.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC 연결 관리 유틸리티 클래스
 * - 데이터베이스 연결을 생성하고 관리하는 싱글톤 패턴 구현
 * - 설정 정보는 application.properties 파일에서 로드
 * - 정적 초기화 블록에서 애플리케이션 시작 시 한 번만 연결 생성
 */
public class JDBCUtil {
    /**
     * 데이터베이스 연결 객체 (싱글톤 인스턴스)
     * 모든 DAO에서 공유하는 단일 연결 객체
     */
    private static Connection conn = null;

    /**
     * 정적 초기화 블록
     * - 클래스가 로드될 때 한 번만 실행됨
     * - 설정 파일에서 데이터베이스 연결 정보를 읽어옴
     * - JDBC 드라이버를 로드하고 데이터베이스 연결 수립
     */
    static {
        try {
            // Properties 객체를 사용하여 설정 파일 로드
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getResourceAsStream("/application.properties"));

            // 설정 파일에서 연결 정보 추출
            String driver = properties.getProperty("driver");   // JDBC 드라이버 클래스명
            String url = properties.getProperty("url");         // 데이터베이스 URL
            String id = properties.getProperty("id");           // 데이터베이스 사용자 ID
            String password = properties.getProperty("password"); // 비밀번호

            // JDBC 드라이버 로드 및 연결 수립
            Class.forName(driver);
            conn = DriverManager.getConnection(url, id, password);
        } catch (Exception e) {
            // 연결 실패 시 스택 트레이스 출력
            e.printStackTrace();
        }
    }

    /**
     * 데이터베이스 연결 객체를 반환하는 메서드
     *
     * @return Connection 데이터베이스 연결 객체
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * 데이터베이스 연결을 종료하는 메서드
     * - 애플리케이션 종료 시 또는 명시적인 연결 종료 시 호출
     * - 연결이 이미 닫혀있거나 null인 경우 안전하게 처리
     */
    public static void close() {
        try {
            if (conn != null) {
                conn.close();  // 연결 종료
                conn = null;   // 객체 참조 제거
            }
        } catch (SQLException e) {
            // 연결 종료 중 오류 발생 시 스택 트레이스 출력
            e.printStackTrace();
        }
    }
}