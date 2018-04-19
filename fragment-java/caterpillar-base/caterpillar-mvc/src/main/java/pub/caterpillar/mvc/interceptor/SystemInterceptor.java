package pub.caterpillar.mvc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 系统拦截器
 * lvdeyang 2017年6月12日
 */

public class SystemInterceptor extends HandlerInterceptorAdapter {
	
	
    // private RoleMenuDAO conn_rolemenu=SpringContext.getBean(requiredType);
	// 最后执行,一般用于释放资源
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
		System.out.println("afterCompletion");
	}
	
	//生成视图之前
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		
		System.out.println("postHandle:");
	}
	
	//进入action之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        String url=request.getServletPath().toString();
   	    System.out.println("preHandlesess:"+request.getSession().getAttribute("roleid"));
		return true;
	}
	
}
