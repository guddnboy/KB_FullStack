package org.scoula.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 🌱 Root Application Context 설정 클래스
 * - Spring Framework의 최상위(Root) 애플리케이션 컨텍스트를 설정하는 클래스
 * - 웹 계층과 무관한 비즈니스 로직, 서비스, 데이터 액세스 계층의 Bean들을 관리
 */
@Slf4j
@Configuration
@PropertySource({"classpath:/application.properties"})
@MapperScan(basePackages = {
        "org.scoula.board.mapper",
        "org.scoula.member.mapper"  // 회원 매퍼 스캔
})
@ComponentScan(basePackages = {
        "org.scoula.board.service",
        "org.scoula.member.service"  // 회원 서비스 스캔
})
public class RootConfig {

    // 현재는 기본 설정만 있는 상태
    // 프로젝트 발전에 따라 다음과 같은 빈들을 추가할 수 있습니다:
    // - 데이터베이스 설정
    // - 트랜잭션 관리
    // - 보안 설정
    // - 외부 API 클라이언트
    // - 캐시 설정
    // - 스케줄링 설정


    @Value("${jdbc.driver}")
    String driver;
    @Value("${jdbc.url}")
    String url;
    @Value("${jdbc.username}")
    String username;
    @Value("${jdbc.password}")
    String password;
    @Autowired
    ApplicationContext applicationContext;


    /**
     * HikariCP 커넥션 풀을 사용한 DataSource 빈 생성
     *
     * @return 설정된 DataSource 객체
     */
    @Bean
    public DataSource dataSource() {
        // HikariCP 설정 객체 생성
        HikariConfig config = new HikariConfig();

        // 데이터베이스 연결 정보 설정
        config.setDriverClassName(driver);          // JDBC 드라이버 클래스
        config.setJdbcUrl(url);                    // 데이터베이스 URL
        config.setUsername(username);              // 사용자명
        config.setPassword(password);              // 비밀번호

        // 커넥션 풀 추가 설정 (선택사항)
        config.setMaximumPoolSize(10);             // 최대 커넥션 수
        config.setMinimumIdle(5);                  // 최소 유지 커넥션 수
        config.setConnectionTimeout(30000);       // 연결 타임아웃 (30초)
        config.setIdleTimeout(600000);            // 유휴 타임아웃 (10분)

        // HikariDataSource 생성 및 반환
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    /**
     * SqlSessionFactory 빈 등록
     * - MyBatis의 핵심 팩토리 객체를 스프링 컨테이너에 등록
     *
     * @param dataSource 위 dataSource() 메서드에서 등록된 bean이 주입됨
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();

        // MyBatis 설정 파일 위치 지정
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));

        // 데이터베이스 연결 설정
        sqlSessionFactory.setDataSource(dataSource);

        return sqlSessionFactory.getObject();
    }

    /**
     * 트랜잭션 매니저 설정
     * - 데이터베이스 트랜잭션을 스프링이 관리하도록 설정
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
        return manager;
    }

}
