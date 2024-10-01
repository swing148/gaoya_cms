package com.xxgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 系统菜单
 */
@Data
public class SysMenu {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer pid;
    private String name;
    private String url;
    private String perms;   //角色
    private String icon;    //图标
    private Integer createBy;
    private Date createDate;//创建时间
    private Integer updateBy;
    private Date updateDate;//更新时间

    @TableLogic
    private String delFlag;
}