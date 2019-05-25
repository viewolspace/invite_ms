package com.ms.sys.base;

import com.youguu.core.dao.SqlDAO;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.Resource;

public class InviteMsDAO<T> extends SqlDAO<T> {
	public InviteMsDAO() {
		super();
		setUseSimpleName(true);
	}

	@Resource(name = "inviteMsSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

}
