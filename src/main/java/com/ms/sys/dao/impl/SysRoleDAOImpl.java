package com.ms.sys.dao.impl;

import com.youguu.core.util.PageHolder;
import com.ms.sys.base.InviteMsDAO;
import com.ms.sys.dao.SysRoleDAO;
import com.ms.sys.pojo.SysRole;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leo on 2017/11/23.
 */
@Repository
public class SysRoleDAOImpl extends InviteMsDAO<SysRole> implements SysRoleDAO {
	@Override
	public int saveSysRole(SysRole role) {
		this.insert(role);
		return role.getId();
	}

	@Override
	public int updateSysRole(SysRole role) {
		return this.update(role);
	}

	@Override
	public int deleteSysRole(int id) {
		return this.delete(id);
	}

	@Override
	public SysRole getSysRole(int id) {
		return this.get(id);
	}

	@Override
	public PageHolder<SysRole> querySysRoleByPage(String name, int pageIndex, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		return this.pagedQuery("querySysRoleByPage", map, pageIndex, pageSize);
	}

	@Override
	public List<SysRole> listALLSysRole() {
		return this.getAll();
	}

}
