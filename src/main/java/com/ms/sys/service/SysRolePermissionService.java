package com.ms.sys.service;

import com.ms.sys.pojo.SysRolePermission;

import java.util.List;

/**
 * Created by leo on 2017/11/23.
 */
public interface SysRolePermissionService {

	int batchSaveSysRolePermission(List<SysRolePermission> sysRolePermissionList);

	List<SysRolePermission> listSysRolePermissionByRole(Integer... roleId);
}
