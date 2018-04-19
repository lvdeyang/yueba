package pub.caterpillar.mvc.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import pub.caterpillar.commons.util.stream.StreamUtil;

public class CommonMappingExceptionResolver extends SimpleMappingExceptionResolver{

	private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;
	
	private String customerAttribute = "cex";
	
	@Override
	public void setExceptionAttribute(String exceptionAttribute) {
		this.exceptionAttribute = exceptionAttribute;
	}
	
	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex) {
		ModelAndView mv = new ModelAndView(viewName);
		
		if (this.exceptionAttribute != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Exposing Exception as model attribute '" + this.exceptionAttribute + "'");
			}
			mv.addObject(this.exceptionAttribute, ex);
			
			Map<String, String> cex = new HashMap<String, String>();
			cex.put("name", ex.toString());
			try{
				cex.put("stack", StreamUtil.getStackTrace(ex));
			}catch(Exception e){
				e.printStackTrace();
			}
			mv.addObject(this.customerAttribute, cex);
		}
		return mv;
	}
}
