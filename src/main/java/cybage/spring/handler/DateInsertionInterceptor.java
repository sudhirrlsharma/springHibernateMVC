package cybage.spring.handler;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DateInsertionInterceptor implements HandlerInterceptor,Ordered {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("Hi I am preHandle");
		return true; // always continue
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Hi I am postHandle");
		modelAndView.addObject("currentTime", new Date());
	}

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("Hi I am afterCompletion");
		// TODO Auto-generated method stub
		
	}

	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
}