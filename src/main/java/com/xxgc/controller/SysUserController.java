package com.xxgc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxgc.entity.SysRole;
import com.xxgc.entity.SysUser;
import com.xxgc.service.ISysRoleService;
import com.xxgc.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/a/user")
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;

    //请求登录
    @RequestMapping("/login")
    public String login() {
        return "sys/login";
    }

    @RequestMapping("/logout")
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //设置用户厉害
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:/a/user/login";
    }

    //加载用户列表数据
    @RequestMapping("/list")
    public String list(Integer curr, Integer size, SysUser sysUser, Model model) {
        //默认配置
        Integer current = 1;
        Integer pageSize = 2;
        //判断前台传递的页码和每页记录数
        if (curr != null) current = curr;
        if (size != null) pageSize = size;

        //查询用户的数据，并分页
        //列表数据，总记录数，当前页
        //分页对象
        //查询对象
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        //绑定查询条件
        if (sysUser != null && sysUser.getUsername() != null && sysUser.getUsername() != "") {
            queryWrapper.eq("username",sysUser.getUsername());
        }
        if (sysUser != null && sysUser.getEmail() != null && sysUser.getEmail() != "") {
            queryWrapper.eq("email",sysUser.getEmail());
        }

        IPage<SysUser> page = sysUserService.page(new Page<>(current,pageSize),queryWrapper);

        model.addAttribute("userList",page.getRecords());//获取某一页的记录数
        model.addAttribute("total",page.getTotal());    //获取记录总数
        model.addAttribute("current",page.getCurrent());//当前页
        return "sys/views/users";
    }


    /**
     * 新增、编辑
     */
    @RequestMapping("/form")
    public String form(Model model, Integer id, SysUser sysUser) {
        //获取所有的角色
        List<SysRole> roleList = sysRoleService.list();
        if (id != null) {
            sysUser = sysUserService.findUserById(id);
        }
        model.addAttribute("roleList", roleList);
        System.out.println(sysUser);
        model.addAttribute("sysUser", sysUser);
        return "sys/views/userForm";
    }

    @RequestMapping("/save")
    public String save(SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return "redirect:list";
    }

}
