package com.xxgc.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: 系统用户类
 */
@Data
public class SysUser {
    @TableId(type = IdType.AUTO) //设置主键自动增加
    private Integer id;
    private String username;    //登录名
    private String password;
    private String name; //用户名
    private String email;
    private String mobile;
    private Integer status; //用户状态

    private Integer createBy;
    private Date createDate;//创建时间
    private Integer updateBy;
    private Date updateDate;//更新时间
    // @TableLogic 用于设置该字段为逻辑字段
    @TableLogic
    private String delFlag;

    //角色
    //mybatis plus忽略该属性和数据库的映射
    @TableField(exist = false)
    private List<SysRole> roles;

}