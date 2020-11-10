package shanghai.shu.edu.mvc.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import shanghai.shu.edu.constant.CrowdConstant;
import shanghai.shu.edu.entity.Admin;
import shanghai.shu.edu.exception.AccessForbiddenException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过request对象获得session对象
        HttpSession session = request.getSession();
        //尝试从Session域中获取Admin对象
        Admin admin =(Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        //admin为空抛出错误
        if(admin==null){
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }
        //如果Admin对象不为null，返回true。
        return true;
    }
}
