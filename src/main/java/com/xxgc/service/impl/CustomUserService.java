package com.xxgc.service.impl;

import com.xxgc.entity.SysRole;
import com.xxgc.entity.SysUser;
import com.xxgc.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 专门用于security账户验证的服务层
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.findUserByName(username);
        if (user != null) {
            List<SysRole> roles = user.getRoles();//获取用户角色
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysRole role : roles) {
                if (role != null && role.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
                    grantedAuthorities.add(grantedAuthority);
                    String sql = "SELECT sm.url " +
                            "        from sys_role sr " +
                            "                 LEFT JOIN sys_role_menu srm on srm.sys_role_id = sr.id left join sys_menu sm on sm.id = srm.sys_menu_id  " +
                            "        where sr.id = ? and sm.url is not null and sm.url != '' ";
                    List<Map<String,Object>> granted = jdbcTemplate.queryForList(sql,role.getId());
                    for(Map<String,Object> m : granted) {
                        GrantedAuthority grantedAuthority1 = new SimpleGrantedAuthority(String.valueOf(m.get("url")));
                        grantedAuthorities.add(grantedAuthority1);
                    }
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}