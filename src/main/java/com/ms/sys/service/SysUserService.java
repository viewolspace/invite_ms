package com.ms.sys.service;

import com.youguu.core.util.PageHolder;
import com.ms.sys.pojo.SysUser;

import java.util.Date;

public interface SysUserService {

	public int saveSysUser(SysUser sysUser);

	public int updateSysUser(SysUser sysUser);

	public int deleteSysUser(int id);

	public SysUser getSysUser(int id);

	public PageHolder<SysUser> querySysUserByPage(int userId, String realName, int pageIndex, int pageSize);

	public SysUser findSysUserByUserName(String username);

	public int updateLastLoginTime(String userName, Date lastLoginTime);

	int updatePwd(String userName, String oldPwd, String newPwd);

}
