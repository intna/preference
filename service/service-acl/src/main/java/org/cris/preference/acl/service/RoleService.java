package org.cris.preference.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cris.preference.model.acl.Role;
import org.cris.preference.vo.acl.RoleQueryVo;

import java.util.Map;


public interface RoleService extends IService<Role> {

    IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

}
