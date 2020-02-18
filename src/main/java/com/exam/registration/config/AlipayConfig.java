package com.exam.registration.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author yhf
 * @classname AlipayConfig
 **/
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String APP_ID = "2016101800716477";

    // 商户私钥，您的PKCS8格式RSA2私钥，这些就是我们刚才设置的
    public static String MERCHANT_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCSmq+jgiP0enQvlw6lqgC5HyJZmJOaENIUc97+nZfBgdtjcvoPt2QJaVN4o1+37qYnhG6Op5ezh2SbkyZc0TJtjtpdCXYT3sAa9eauT5kBmKwXegKyt2afEd/03IhPrgaiWSkTxdyrVpQ3CmFiXNoO0/9w9xgwK4393CuYYxgn0PTFMzpp/GED56nqC8AjfNJjmLq6ZpLlTPDop1CLM7rlRVJbxsZHKh7kept/ADnDhtoY9Ae2c+1z2MDN+dZxEia9CGB+peiuC/pXTeD2ACHjD+2HqQdYydDLB9tvNPEJUVod+PcZQUuA+dUjetmPHKJCLb8jPpqLSCiP3wWKZwo5AgMBAAECggEAIDgUr6eTRVCAJlFZa7kf7Y9VGkG49pwJv2rzsBtsVZ+3WnTTVg7k0liueo3VJHQzWe316i4U3eS8jKBbRvzGy2MgxF+m7pj+uS4fo2Fk6//kmkg7PZGm4QCpqoOpBXHmjVlw+KJ8a79cVXhXjKmBVs7qjaYQT83Sp97yScxFa8JgHlCCwsi318FgLfKsGYiE4OfsmjBQgiRzudjU272yqSON/lGx32Qjmufbe1GOMPbUgRo2cW9haaIT7QrJ5nTeWB0QH3vKJbjYQSsGtRfPkntgkMFUjUrL6XcMutVspB1GmD1xw8D1g9kHyYxEgN8L9DYH9PmiQI0uFYeryYl6gQKBgQDPg5XBr/qitJZaxCWnVI7R6zYDnVAiL4theZ+3CAqOToG79E/vB72+PZ4J/Du7QUiZllZPewr24YbkO2nfzbhTrzBAr2/jEd5cDTC39nbLXVTB7ChnbnsXMDfQ4FoieD8BFJDGD13qXhXjvXy/3cW9uYNWHUeyQtTkfcu2M5Z78QKBgQC028tGJ3N3854F8tqz79GJyQMnH7OxYbiRpnB40M5OU7dkY+EgWdpqupmyCMQBVaE+yY61aYlwaaBOculr3xyGcrj/ZcbaBNmQrKFv4QtzPXZv6S1P5zDcZteJ1xqEvcrvzhgeiERL8/mUm4JkUte46WR3Xer6Ft7k93jUbX1ayQKBgQCTjXUG7CLDXrUqhR5hBPcovJ8DovA/3p61CM5QTx9yYNBO3UGZLV/1zcp6PygZ1inRS9zDVzJMqcBIuRZnVNgkaQVeeWCNjT8XJSnnJREDObHy0gFIyn4r1tVZaK2upOHn2r+2lWaqJ4xZC3uQ0hiw9yA0M02vq2XFss0PGU9/gQKBgFlWfSnxUOt+zp9XoErj+tiaD9GiqkvD3r0rv0Eeu9obJmsTn2AwomC4PjWgeNnD57uxKqpYMIjz1DkMMFuVwWx0fQfDyZ9mZ+Y8MB6oxtQtFzUetMBUjmFA99ViCPprGjSs1GM4oqv2H2IWEynldiQkPflsziatZ3so92nPj+1pAoGBALz97Q3HknRinP3KoO4p1wAGKtNkSyWUl8QAzcvOPHf8EZ6OS8OsiJ9qua+/rYM9zRxzaxWigkT6Pdra9n25li+q+kkqUfr5E3SrIzRpM8p7NHCnkrBz7EqWkYenLY6EVL/w0cFDujZg3/62TZMOBHlxkEEIkRLICuOpo8/30Epi";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。，这些就是我们刚才设置的
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsdXTXvq8qZoK9CxiiH0VN7ZfMtMf8kSHn6McpjmjyqCp/FQfKJo4357XWP5LsnGA2OqympcYlSe1mEDCatUeEHuSMuKDpMgEpWL8wt25C9XEkXzWgBPLGy/9KXZiL1yXj3VlyROUF6EgroKM/5+OdKAU0zyKe2kSsN5cR7Saoei2SfQmE38Ynd/VKuUDLAPqKnuGqjur9I1sLBLswXug3BgtKTu9oerkPcnlh47kHqgKfI7DXzG9QZJtPzBSvHCcINMXEJ2tUESSi8GtFtIHm4VWFIhFheBHkXFwPwS3nikwoXq+3sepMiFA56q6UWvyhpHJ7n+5yDp4SXWUMcwxKQIDAQAB";

    //异步通知，再这里我们设计自己的后台代码
    public static String notify_url = "http://47.105.93.192/api/alipay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://47.105.93.192/api/alipay/return";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关
    public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String LOG_PATH = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(LOG_PATH + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

