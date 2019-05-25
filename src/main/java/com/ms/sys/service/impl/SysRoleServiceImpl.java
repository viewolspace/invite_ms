package com.ms.sys.service.impl;

import com.youguu.core.util.PageHolder;
import com.ms.sys.dao.SysRoleDAO;
import com.ms.sys.dao.SysRolePermissionDAO;
import com.ms.sys.pojo.SysRole;
import com.ms.sys.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by leo on 2017/11/23.
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private SysRoleDAO sysRoleDAO;
	@Resource
	private SysRolePermissionDAO sysRolePermissionDAO;

	@Override
	public int saveSysRole(SysRole role) {
		return sysRoleDAO.saveSysRole(role);
	}

	@Override
	public int updateSysRole(SysRole role) {
		return sysRoleDAO.updateSysRole(role);
	}

	@Override
	public int deleteSysRole(int id) {
		sysRoleDAO.deleteSysRole(id);//删除角色
		return sysRolePermissionDAO.deleteSysRolePermissionByRole(id);//删除角色关联的权限
	}

	@Override
	public SysRole getSysRole(int id) {
		return sysRoleDAO.getSysRole(id);
	}

	@Override
	public PageHolder<SysRole> querySysRoleByPage(String name, int pageIndex, int pageSize) {
		return sysRoleDAO.querySysRoleByPage(name, pageIndex, pageSize);
	}

	@Override
	public List<SysRole> listALLSysRole() {
		return sysRoleDAO.listALLSysRole();
	}
}
