package com.nash.product.filter;

import com.nash.product.annotation.PassToken;
import com.nash.product.constants.CommonConstant;
import com.nash.product.constants.ErrorCodeEnum;
import com.nash.product.entity.account.Account;
import com.nash.product.service.AccountService;
import com.nash.product.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/19 9:20 下午
 */
public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    /**
     * JWT服务接口
     */
    @Autowired
    private JwtUtils jwtUtils;
    /**
     * url请求鉴权头
     */
    @Value("${jwt.header}")
    private String header;
    /**
     * 账号服务
     */
    @Autowired
    AccountService accountService;

    /**
     * url请求前拦截方法，用于校验token
     * @param httpServletRequest
     * @param httpServletResponse
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从请求头中取出 token  这里需要和前端约定好把jwt放到请求头一个叫token的地方
        String token = httpServletRequest.getHeader(header);
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //默认全部检查
        else {
            // 执行认证
            if (token == null) {
                //这里其实是登录失效,没token了   这个错误也是我自定义的，读者需要自己修改
                throw new RuntimeException(ErrorCodeEnum.getDescription(ErrorCodeEnum.TOKEN_NOT_EXIST, httpServletRequest.getParameter(CommonConstant.LANG)));
            }
            // 获取 token 中的 user Name
            String userId = jwtUtils.getAudience(token);
            //获取载荷内容
            String userName = jwtUtils.getClaimByName(token, "userName").asString();
            //找找看是否有这个user   因为我们需要检查用户是否存在，读者可以自行修改逻辑
            Account user = accountService.getAccountByIdAndName(Long.valueOf(userId), userName);
            if (user == null) {
                //这个错误也是我自定义的
                throw new RuntimeException(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NOT_EXIST, jwtUtils.getClaimByName(token, CommonConstant.LANG).asString()));
            }
            // 验证 token
            jwtUtils.verifyToken(token, userId);
            //放入attribute以便后面调用
            httpServletRequest.setAttribute("userId", userId);
            httpServletRequest.setAttribute("userName", userName);
            httpServletResponse.setHeader(header, jwtUtils.createToken(Long.valueOf(userId), userName));
            return true;
        }
        return true;
    }

    /**
     * url拦截器后处理方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * url拦截器处理完成方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
