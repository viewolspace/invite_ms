package com.ms.sys.dao.impl;

import com.ms.sys.base.InviteMsDAO;
import com.ms.sys.dao.SysPermissionDAO;
import com.ms.sys.pojo.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leo on 2017/11/23.
 */
@Repository
public class SysPermissionDAOImpl extends InviteMsDAO<SysPermission> implements SysPermissionDAO {
	@Override
	public int saveSysPermission(SysPermission permission) {
		return this.insert(permission);
	}

	@Override
	public int updateSysPermission(SysPermission permission) {
		return this.update(permission);
	}

	@Override
	public int deleteSysPermission(int id) {
		return this.delete(id);
	}

	@Override
	public SysPermission getSysPermission(int id) {
		return this.get(id);
	}

	@Override
	public List<SysPermission> queryAllSysPermission() {
		return this.getAll();
	}

	@Override
	public List<SysPermission> findSysPermissionByRoleidAndPermissionId(int roleId, int parentId) {
		Map<String, Object> map = new HashMap<>();
		map.put("parentId", parentId);
		map.put("roleId", roleId);
		return this.findBy("select_user_permission", map);
	}

	@Override
	public List<SysPermission> findSysPermissionByRoleid(int roleId) {
		return this.findBy("select_user_permission_by_rid", roleId);
	}
}
