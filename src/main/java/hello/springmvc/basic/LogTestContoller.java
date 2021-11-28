package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j // lombok logger
@RestController
public class LogTestContoller {
	//lombok @Slf4j
	//private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping("/log-test")
	public String logTest(){
		String name = "String";

		System.out.println("name = " + name);

		// 로그 레벨을 표시
		// application.properties에 레벨을 설정할 수 있다. 아래로 갈수록 레벨 낮음
		log.trace("trace log={}", name); 	// 가장 레벨이 높음. 기본은 info. 하위는 다보임.
		log.debug("debug log={}", name); 	// 디버그에서 보는 정보. 개발 서버
		log.info(" info log={}", name); 	// 운영 서버
		log.warn(" warn log={}", name); 	// 경고
		log.error("error log={}", name); 	// 위험

		return "ok";
	}
}
