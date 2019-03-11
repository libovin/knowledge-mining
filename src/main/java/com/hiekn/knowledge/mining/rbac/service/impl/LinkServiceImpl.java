package com.hiekn.knowledge.mining.rbac.service.impl;

import com.hiekn.knowledge.mining.rbac.dto.LinkInfo;
import com.hiekn.knowledge.mining.rbac.service.LinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhailiang
 *
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService {

	@Override
	public LinkInfo getTree(String userId) {
		return null;
	}

	@Override
	public LinkInfo getInfo(String id) {
		return null;
	}

	@Override
	public LinkInfo create(LinkInfo info) {
		return null;
	}

	@Override
	public LinkInfo update(LinkInfo info) {
		return null;
	}

	@Override
	public void delete(String id) {

	}

	@Override
	public Long move(String id, boolean up) {
		return null;
	}
}