package com.ms.sys.dao;

import com.ms.sys.pojo.SysRolePermission;

import java.util.List;

/**
 * Created by leo on 2017/11/23.
 */
public interface SysRolePermissionDAO {

	int batchSaveSysRolePermission(List<SysRolePermission> sysRolePermissionList);

	int deleteSysRolePermissionByRole(int roleId);

	List<SysRolePermission> listSysRolePermissionByRole(Integer... roleId);
}
