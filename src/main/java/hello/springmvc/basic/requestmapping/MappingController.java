package hello.springmvc.basic.requestmapping;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MappingController {

	/**
	 * 기본 요청
	 * 둘다 허용 /hello-basic, /hello-basic/, 여러개도 됨
	 * method 지정하지 않으면 HTTP 메서드 모두 허용. GET, POST, HEAD, PUT, PATCH, DELETE
	 */
	@RequestMapping(value = {"/hello-basic", "/hello-go"}, method = RequestMethod.GET)
	public String helloBasic() {
		log.info("helloBasic");
		return "ok";
	}

	@GetMapping(value = "/mapping-get-v2")
	public String mappingGetV2() {
		log.info("mappingGetV2");
		return "ok";
	}

	/**
	 * PathVariable 사용
	 * 변수명이 같으면 생략 가능. 즉,
	 * @PathVariable("userId") String userId -> @Pathvariable String userId
	 *
	 * /mapping/userA
	 */
	@GetMapping("/mapping/{userId}")
	public String mappingPath(@PathVariable("userId") String data) {
		log.info("mappingPath userId={}", data);
		return "ok";
	}

	/**
	 * 다중 매핑
	 */
	@GetMapping("/mapping/users/{userId}/orders/{orderId}")
	public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
		log.info("mappingPath userId={}, orderId={}", userId, orderId);
		return "ok";
	}

	/**
	 * 파라미터로 추가 매핑
	 * params="mode",
	 * params="!mode"
	 * params="mode=debug"
	 * params="mode!=debug"
	 * params={"mode=debug", "data=good"}
	 */
	@GetMapping(value = "/mapping-param", params = "mode=debug") //?mode=debug가 붙어야만 매핑해줌. 잘 안쓴다.
	public String mappingParam() {
		log.info("mappingParam");
		return "ok";
	}

	/**
	 * 특정 헤더로 추가 매핑
	 * headers="mode"
	 * headers="!mode"
	 * headers="mode=debug"
	 * headers="mode!=debug"
	 */
	@GetMapping(value = "/mapping-header", headers = "mode=debug")
	public String mappingHeader() {
		log.info("mappingHeader");
		return "ok";
	}

	/**
	 * Content-Type 헤더 기반 추가 매핑 Media Type
	 * consumes="application/json"
	 * consumes="!application/json"
	 * consumes="application/*"
	 * consumes="*\/*"
	 * MediaType.APPLICATION_JSON_VALUE
	 */
	@PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String mappingConsumes() {
		log.info("mappingConsumes");
		return "ok";
	}

	/**
	 * Accept 헤더 기반 Media Type - 클라이언트가 받을 수 있는 데이터 타입을 서버한테 부탁할 때 Accept 사용 406 - Not Acceptable 오류
	 * produces = "text/html"
	 * produces = "!text/html"
	 * produces = "text/*"
	 * produces = "*\/*"
	 */
	@PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
	public String mappingProduces(){
		log.info("mappingProduces");
		return "ok";
	}
}
