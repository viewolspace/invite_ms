package com.ms.sys.dao;

import com.ms.sys.pojo.SysUserRole;

/**
 * Created by leo on 2017/11/23.
 */
public interface SysUserRoleDAO {

	int saveSysUserRole(SysUserRole userRole);

	int updateSysUserRole(SysUserRole userRole);

	SysUserRole findSysUserRoleByUid(int uid);

	int deleteSysUserRoleByUid(int uid);


}
