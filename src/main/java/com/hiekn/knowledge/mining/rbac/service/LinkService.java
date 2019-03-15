package com.hiekn.knowledge.mining.rbac.service;

import com.hiekn.knowledge.mining.rbac.model.dto.LinkInfo;

public interface LinkService {

	LinkInfo getTree(String userId);

	LinkInfo getInfo(String id);

	LinkInfo create(LinkInfo info);

	LinkInfo update(LinkInfo info);

	void delete(String id);

	Long move(String id, boolean up);

}
