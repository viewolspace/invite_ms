package com.ms.sys.dao;

import com.youguu.core.util.PageHolder;
import com.ms.sys.pojo.SysRole;

import java.util.List;

/**
 * Created by leo on 2017/11/23.
 */
public interface SysRoleDAO {
	int saveSysRole(SysRole role);

	int updateSysRole(SysRole role);

	int deleteSysRole(int id);

	SysRole getSysRole(int id);

	PageHolder<SysRole> querySysRoleByPage(String name, int pageIndex, int pageSize);

	List<SysRole> listALLSysRole();

}
