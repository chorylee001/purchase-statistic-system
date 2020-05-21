package com.infowoo.purchase.service;

import com.infowoo.purchase.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;

@Slf4j
@Service
public class UserShiroRealm extends AuthorizingRealm {


    @Resource
    private IUserService userService;

    @Resource
    private SessionDAO sessionDAO;


    @PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我们自己的验证
        setCredentialsMatcher(new UserCredentialsMatcher());
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 检测顺序
     * RequiresRoles
     * RequiresPermissions
     * RequiresAuthentication
     * RequiresUser
     * RequiresGuest ：RequiresGuest === subject.getPrincipal() == null.
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        String userName = authenticationToken.getPrincipal().toString();

        //只允许同一账户单个登录
        Subject subject = SecurityUtils.getSubject();
        Session nowSession = subject.getSession();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        if(sessions != null && sessions.size() > 0) {
            for (Session session : sessions) {
                if (!nowSession.getId().equals(session.getId()) && (session.getTimeout() == 0
                        || userName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY))))) {
                    sessionDAO.delete(session);
                }
            }
        }

        UserInfo userInfo = userService.getUserBaseInfo(userName);
        if (userInfo == null) {
            return null;
        }
        SecurityUtils.getSubject().getSession().setAttribute("user_info", userInfo);

        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, userInfo.getPassword(), getName());
        return authenticationInfo;
    }
}
