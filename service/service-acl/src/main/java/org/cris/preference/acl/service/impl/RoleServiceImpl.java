package org.cris.preference.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cris.preference.acl.mapper.RoleMapper;
import org.cris.preference.acl.service.AdminRoleService;
import org.cris.preference.acl.service.RoleService;
import org.cris.preference.entity.model.acl.AdminRole;
import org.cris.preference.entity.model.acl.Role;
import org.cris.preference.entity.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;
    @Override
    public IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //获取角色名
        String roleName = roleQueryVo.getRoleName();
        //创建条件构造器对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        //判空
        if (!StringUtils.isEmpty(roleName)) {
            //封装条件
            wrapper.like(Role::getRoleName,roleName);
        }
        //调用mapper方法实现条件分页查询
        IPage<Role> pageModel1 = baseMapper.selectPage(pageParam,wrapper);
        return pageModel1;
    }

    @Override
    public void saveUserRoleRealtionShip(Long adminId, Long[] roleIds) {
        //删除用户分配的角色
        adminRoleService.remove(new QueryWrapper<AdminRole>().eq("admin_id",adminId));

        //创建新的角色
        List<AdminRole> userRoles = new ArrayList<>();
        for (Long roleId : roleIds){
            if (StringUtils.isEmpty(roleId)) continue;
            AdminRole userRole = new AdminRole();
            userRole.setAdminId(adminId);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        }
        //批处理
        adminRoleService.saveBatch(userRoles);
    }

    @Override
    public Map<String, Object> findRoleByUserId(Long adminId) {
        //查询所有角色
        List<Role> allRoleList = baseMapper.selectList(null);

        //拥有的角色ID
        List<AdminRole> existUserRoleList = adminRoleService.list(new QueryWrapper<AdminRole>()
                .eq("admin_id",adminId)
                .select("role_id"));
        List<Long> existRoleList = existUserRoleList.stream()
                .map(c->c.getRoleId())
                .collect(Collectors.toList());
        //对角色进行分类
        List<Role> assignRoles = new ArrayList<Role>();
        for (Role role : allRoleList) {
            //已分配
            if(existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRoleList);
        return roleMap;
    }
}
