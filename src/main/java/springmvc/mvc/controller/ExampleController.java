package springmvc.mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.view.RedirectView;
import springmvc.mvc.service.ExampleService;

@Controller
public class ExampleController {
	@Autowired
	ExampleService exampleService;
	
//	@RequestMapping(path = "/example/draw-example.do", method = { RequestMethod.GET })
	@GetMapping("/example/draw-example.do")
	public ModelAndView drawExample(ModelAndView mv) {
		
		System.out.println("잘 들어 오는군?");
		
		exampleService.invokeWithoutTransaction();
		
		// TODO 아래 메서드 내부에서 브레이크 걸어보면 CGLIB 스택이 중간에 없다. 트랜잭션 설정 제대로(WebConfig.java) 해놓고 다시 해봐야 함.
		exampleService.invokeWithTransaction();
		
		mv.setViewName("/example/example");
		return mv;
	}
	
//	@RequestMapping(path = "/example/session-invalidate-test.do", method = { RequestMethod.GET })
	@GetMapping("/example/session-invalidate-test.do")
	public ModelAndView sessionInvalidateTest(ModelAndView mv, HttpSession session) {
		
		System.out.println("session id: \n" + session.getId());
		
		session.setAttribute("a", 123);
		int a = (int) session.getAttribute("a");
		System.out.println(String.valueOf(a == 123));
		
		session.invalidate(); // 세션 무효화
		
		// 아이디는 아직 그대로
		System.out.println("session id after invalidate: \n" + session.getId());
				
		mv.setViewName("/example/example");
		return mv;
	}

	@ModelAttribute("anyLastWord")
	public String bindSomething() {
		return "soylent-green-is-people";
	}

	@GetMapping("/example/redirection-test.do")
	public RedirectView redirectionTest() {
		RedirectView rv = new RedirectView("/example/draw-example.do");
		rv.setStatusCode(HttpStatus.TEMPORARY_REDIRECT); // 307 Temporary Redirect
		rv.setHttp10Compatible(true); // HTTP 1.0 호환 설정
		rv.setExposeModelAttributes(false); // ModelAttribute가 URL에 노출되는 것을 방지함
		return rv;
	}
}
