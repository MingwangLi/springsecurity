package com.szjzht.admin.security;

import com.szjzht.admin.mapper.ResourceMapper;
import com.szjzht.admin.mapper.RoleResourceMapper;
import com.szjzht.admin.model.Resource;
import com.szjzht.admin.model.Role;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: mayn
 * @Date: 2019/9/9 10:48
 * @Description:
 */
@Component
public class UrlPathFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();
        requestUrl = requestUrl.split("\\?")[0];
        logger.info("----requestUrl:{}",requestUrl);
        List<Resource> all = resourceMapper.selectAll();
        for (Resource resource : all) {
            List<Role> roles = roleResourceMapper.getRoleList(resource.getId());
            if (StringUtils.isEmpty(resource.getResUrl())) {
                continue;
            }
            if (null != roles && roles.size() > 0 && new AntPathMatcher().match(resource.getResUrl(), requestUrl)) {
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getRoleKey();
                }
                return SecurityConfig.createList(values);
            }
        }
        return SecurityConfig.createList("ROLE_UNKNOW");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
