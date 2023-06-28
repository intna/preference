package org.cris.preference.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cris.preference.entity.model.acl.Role;
import org.cris.preference.entity.vo.acl.RoleQueryVo;

import java.util.Map;


public interface RoleService extends IService<Role> {

    IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    //分配角色
    void saveUserRoleRealtionShip(Long adminId, Long[] roleIds);

    //根据用户获取角色数据
    Map<String, Object> findRoleByUserId(Long adminId);
}
