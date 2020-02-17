package com.exam.registration.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.alipay.api.AlipayConstants.CHARSET;

/**
 * @author yhf
 * @classname Demo
 * @description TODO
 * @date 2020/2/17
 **/
@Controller
public class Demo {
    @RequestMapping("demo")
    public String demo() {
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCiExqHDcwi3khn8cuDsGFkdOIXtUwZRkbFQr6MOj3RIJoKHPOVnsfJmZ1rD7Ay71LPBIpOS3sAMD/QVmlrhfQIi3q+Vcf8TLTTmb62GgZXORX/rGw1O/8pnBFnZZcZmsgH/f9tubgDiV5FHzhpsJWqxIupk1AoB8bSMuDaQhAmnQAlnRuxzNgw0o5DX0geh/+Tgh6S/R1IiNwu4ssnJQ17yAMz3uCqgbaKmCYqAOHGMw8PWXiSYcQJXw+VNLtLkcLz0UVby495RVJ+GQVBpnvMZdyCcFF3zbyXM7SftJVM4kOBIeqoWUdnpnr98Dp2ZwZwtahhHcd5W7KW4quNe6ntAgMBAAECggEAIzBMoSfwu11RkSfhTN09uAbck9MsC4zDjs6X+nAmvcH7T9jLVh/cNrwgfn8wfU6SxxmwQaGHuhm/sSchylOSEUzfQHX1bqTk2dBWpMjvuX5OdW1lLQWyt8EH0lAvR0LgzUNrctJQzovDqPRHVkMdq67yt8WhMJ/Hnhmn0htn0EoLlsykkst5pfQoDtSQ4whG3RElj01MvJLNjsnjSNMwTp92JVof4O5ULSORvaHfL0uxN1MAp9oxReTsxUFZRq6z9WYJSRJLGyUNbAvx4B1PEputcpirFOQ5ITktqfLga/PgfF25cZXYPVUGaCh0uuZZG0wsRIIabVErzhTEo/PpoQKBgQDUox/LxwTeRypAROAUaQPTtD5YtN6v7ZHaO8mHxT33nUi+8EfofppcJDzZqI4H2WZrUylhPbx43MdXUwk5CWlbt7GsSjKAH5jozd12Bda6xhhCXwDVsStaenTiRtqLmgWWGC4yBY5oYJF8H6GHr7VPaIWSuN84r3gfiOydE/EGyQKBgQDDIFL4vaPJmRhoti2ZFHjbTOLlU5r7KoEW5iMH/3aL80Nua9mD+1vvhbhjfr0ovg9TGOTMIQyLT+Nl0Yqes8rDrEg103snr09QpH7ig89WOqBvAg8uYn0Rkxr+Z+IAXTxGo3lv2uJxcE8hbTc9Ulw4N4AQdqKlLi4uxArFCe5IBQKBgQC1M21dEfQsFykCK69otTT1q5uVrEw4T5MDDf/nG0Z7RCVB0TD2By0zaA4SzXh7Z9/Hhc61dw6tUDbvS2uIl2SZLCjUUUZHuKVtfkFC2ibqcvUpWhwz/g6bw87nJMoIPPCVv3VeWy3H9MMFq07TegBf8wogxjoy7BTJir1X/i5fQQKBgQCs+lDTtbonE5Mx8J6HoEBrmMNkLTOtCUca97WCd5OuCV9z2iQf/orH0bFecpJaNfL75Yue8oVeUtnx/0p12kkcIfR50xrQWXdktA7P263IcXycLCUtJ55cIB7D/W+6huzSakVTRaWMjtV3eoyHziV2qKzc627QJBpI1v39IwqbyQKBgANohgA1c9ovedq6NLPzydzOGiWobfmlkFro5nzO1DIaFT7bPQoQqBUaxCSgNPJ2Q8KSWCcgZXqt13eWri1TazWEHNgx/HxhQBXpDY6XUvrIgyAvKMAIZNpyYBGGbWsWi2UTtWQhoOuejw4/YbDSoYYp162Dbr6g+LavUZMeZAa9";
        AlipayClient alipayClient = new
                DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2016101800716477",privateKey,"json","utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsdXTXvq8qZoK9CxiiH0VN7ZfMtMf8kSHn6McpjmjyqCp/FQfKJo4357XWP5LsnGA2OqympcYlSe1mEDCatUeEHuSMuKDpMgEpWL8wt25C9XEkXzWgBPLGy/9KXZiL1yXj3VlyROUF6EgroKM/5+OdKAU0zyKe2kSsN5cR7Saoei2SfQmE38Ynd/VKuUDLAPqKnuGqjur9I1sLBLswXug3BgtKTu9oerkPcnlh47kHqgKfI7DXzG9QZJtPzBSvHCcINMXEJ2tUESSi8GtFtIHm4VWFIhFheBHkXFwPwS3nikwoXq+3sepMiFA56q6UWvyhpHJ7n+5yDp4SXWUMcwxKQIDAQAB","RSA2" );
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "1";
    }
}
