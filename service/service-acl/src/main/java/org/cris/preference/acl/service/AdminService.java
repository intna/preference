package org.cris.preference.acl.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cris.preference.entity.model.acl.Admin;
import org.cris.preference.entity.vo.acl.AdminQueryVo;

public interface AdminService extends IService<Admin> {

    IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo);
}
