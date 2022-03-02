# exam-registration-api 报名系统后端 API
**1.开发框架**

SpringBoot

**2.数据库**

MySQL

**3.缓存**

Redis 考号生成/token 缓存/订单检测等

**4.API设计**

采用RestFul架构。
拿缴费相关的API举例如下：

* GET /orders 代表获取所有的订单
* GET /orders/{id} 代表获取 id 对应的订单
* POST /orders 代表创建一个新的订单
* PUT /orders/{id} 代表更新 id 对应的订单
* DELETE /orders/{id} 代表删除 id 对应的订单

**5.鉴权设计**

采用使用JWT做跨域身份验证，实现方式是在用户登录并验证通过后，调用jjwt中的builder方法并设置好主题、过期时间、签发时间等生成签名，并对签名进行HS256哈希加密。后端将生成好的token返回给前端，前端在收到token后存储在Local Storage中，而后前端的每一次请求都会通过公共的请求方法携带上{Authorization:token}请求头。后端通过编写拦截器拦截请求，在本系统中实现此功能的拦截器是JwtInterceptor，继承自HandlerInterceptorAdapter，需要在继承WebMvcConfigurationSupport类的类中调用addInterceptors方法添加自定义拦截器。在JwtInterceptor中重写preHandle方法，通过request.getHeader("Authorization")方法获取授权头，获取到的token拿去验证并解析，如果验证且解析通过，则将请求的用户id和角色加入到request中，避免之后的重复解析，提高性能。如果验证失败或解析失败，则抛出异常，结束本次请求。

**6.注解权限判定**

权限判定使用自定义注解@RequireRoles()和拦截器结合JWT技术实现。在需要进行权限判断的方法上加上@RequireRoles()注解。例如一个方法仅允许管理员执行，则在方法上加上注解@RequireRoles("admin")即可。

**7.支付功能**

支付功能使用了支付宝提供的接口，用户点击支付按钮会请求AlipayController接口中的pay方法，pay方法会根据设置的APPID、私钥、公钥等信息返回支付表单，支付表单自动提交到支付宝网关进行下一步的处理。在支付完成后支付宝会回调设置的回调接口，通知服务器该订单已经支付完成，同时前端界面会跳转到设置的支付成功界面，至此支付流程结束。

**...**
