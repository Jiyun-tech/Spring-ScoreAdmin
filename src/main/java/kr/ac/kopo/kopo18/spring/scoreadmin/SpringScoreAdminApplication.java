package kr.ac.kopo.kopo18.spring.scoreadmin;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
	
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
// 의미 : DataBase 설정 안 함 -> 뒤에서 데이터베이스 설정 작업 따로 할 예정
public class SpringScoreAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringScoreAdminApplication.class, args);
	}

}
