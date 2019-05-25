package com.ms.sys.service;

import com.youguu.core.util.PageHolder;
import com.ms.sys.pojo.SysRole;

import java.util.List;

/**
 * Created by leo on 2017/11/23.
 */
public interface SysRoleService {

	int saveSysRole(SysRole role);

	int updateSysRole(SysRole role);

	int deleteSysRole(int id);

	SysRole getSysRole(int id);

	public PageHolder<SysRole> querySysRoleByPage(String name, int pageIndex, int pageSize);

	List<SysRole> listALLSysRole();
}
