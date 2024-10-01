package com.xxgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxgc.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单管理 Mapper 接口
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户名获取对应的菜单
     */
    List<SysMenu> findByUserName(@Param("username") String username);
}