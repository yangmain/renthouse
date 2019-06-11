package com.asiainfo.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Ares
 * @Date: 2019/5/31 16:40
 * @Description: 公共请求入口
 * @Version: JDK 1.8
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController
{
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * 代理服务Map
     */
    private static final Map<String, RemoteProxyService> PROXY_SERVICE_MAP = new HashMap<>();

    /**
     * @Author: Ares
     * @Description: 统一调用地址
     * @Date: 2019/6/10 20:21
     * @Param: [request] 请求参数
     * @return: java.lang.Object 响应参数
     */
    @RequestMapping(value = "/invoke")
    public Object invoke(HttpServletRequest request)
    {
        logger.info(request.getParameter("serviceId"));
        return null;
    }

    /**
     * @Author: Ares
     * @Description: 内部实例互相调用
     * @Date: 2019/6/10 20:22
     * @Param: [request] 请求参数
     * @return: java.lang.Object 响应参数
     **/
    @RequestMapping(value = "/innerInvoke")
    public Object innerInvoke(HttpServletRequest request)
    {
        return null;
    }
}
