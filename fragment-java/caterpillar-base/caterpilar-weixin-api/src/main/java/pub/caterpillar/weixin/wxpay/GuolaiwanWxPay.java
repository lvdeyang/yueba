package pub.caterpillar.weixin.wxpay;

import java.util.HashMap;
import java.util.Map;

public class GuolaiwanWxPay {
	 // mchid ， 微信支付商户号
    public final static String MchId = "1266701001"; //

    // appid，应用ID， 在微信公众平台中 “开发者中心”栏目可以查看到
    public final static String AppId = "wxc40369fd3ab2b254"; 

    // appsecret ，应用密钥， 在微信公众平台中 “开发者中心”栏目可以查看到,WEB里不用
    public final static String AppSecret = "19a82d83400da499ea7e85dd8391c356"; 

    // paysignkey，API密钥，在微信商户平台中“账户设置”--“账户安全”--“设置API密钥”，只能修改不能查看
    public final static String AppKey = "21232f297a57a5a743894a0e4a801fc3";
	
	
	private static GuolaiwanWxPay instance;

	private WXPay wxPay;
	private GuolaiwanWxPay(String callBack) throws Exception {
		GuolaiwanWxConfig config=new GuolaiwanWxConfig(AppId, MchId,AppKey);
		wxPay=new WXPay(config,callBack);
	}

	public static synchronized GuolaiwanWxPay getInstance(String callBack) throws Exception {
		if (instance == null) {
			instance = new GuolaiwanWxPay(callBack);
		}
		return instance;
	}
	
	public Map<String, String> pay(Map<String, String> reqData) throws Exception{
		Map<String, String> resultMap=wxPay.unifiedOrder(reqData);
		return resultMap;
	}
	
	public Map<String, String> processResponseXml(String xml) throws Exception {
		return wxPay.processResponseXml(xml);
	}

	
}
