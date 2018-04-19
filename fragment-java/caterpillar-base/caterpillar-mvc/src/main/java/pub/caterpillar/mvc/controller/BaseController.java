package pub.caterpillar.mvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.mvc.controller.exception.ExceptionController;


/**
 * 控制器基类
 * lvdeyang 2017年6月25日
 */
public class BaseController extends ExceptionController{



	@Autowired  
	private  HttpServletRequest request;  




	protected AdminPO getLoginInfo(){

		AdminPO l= new AdminPO();

		l.setAdminName((String)request.getSession().getAttribute("adminName"));
		l.setRoleId((int)request.getSession().getAttribute("roleid"));
		l.setCityCode((String)request.getSession().getAttribute("cityCode"));
		l.setCityName((String)request.getSession().getAttribute("cityName"));

		return l;
	}
	
	protected long  getLoginMerchantId() {
		return Long.parseLong(request.getSession().getAttribute("userMerchantId").toString());
	}
	
	protected long  getLoginUserId() {
		return Long.parseLong(request.getSession().getAttribute("userId").toString());
	}
	
	protected long  getLoginTableId() {
		return Long.parseLong(request.getSession().getAttribute("tableId").toString());
	}
	
	
	protected LoginInfo getNewLoginInfo(){

		LoginInfo l= new LoginInfo();

		l.setLoginName((String)request.getSession().getAttribute("loginName"));
		l.setRoleId((int)request.getSession().getAttribute("roleid"));
		l.setCityCode((String)request.getSession().getAttribute("cityCode"));
		l.setCityName((String)request.getSession().getAttribute("cityName"));
        l.setType((int)request.getSession().getAttribute("type"));
        l.setLoginId((long)request.getSession().getAttribute("loginId"));
		return l;
	}



	//返回成功码
	public Map<String, Object> success(Map<String, Object> result){
		result.put("status", StatusCode.SUCCESS.getCode());
		return result;
	}

	//返回错误码
	public Map<String, Object> ERROR(Map<String, Object> result){
		result.put("status", StatusCode.ERROR.getCode());
		return result;
	}
	//解决中文乱码
	public static String encodeStr(String str) {  
		try {  
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
		} catch (UnsupportedEncodingException e) {  

			return null;  
		}  
	}  
	//获取总页数
	public int  GetAllPages(int allcount,int pagesize) {
		int allpages=allcount%pagesize==0?allcount/pagesize:(allcount/pagesize)+1;
		return allpages;
	}

	public String getRequestJson() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String result="";
		String tempStr="";
		while((tempStr=reader.readLine())!=null){
			result+=tempStr;
		}
		return result;
	}
	
}

