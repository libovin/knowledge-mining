package com.hiekn.knowledge.mining.rbac.service;

import com.hiekn.knowledge.mining.rbac.dto.LinkInfo;

/**
 * 资源服务
 * 
 * @author zhailiang
 *
 */
public interface LinkService {
	
	/**
	 * 获取资源树
	 *
	 * @param userId 用户ID
	 * @date  2015年7月10日下午7:08:26
	 * @since 1.0.0
	*/
	LinkInfo getTree(String userId);

	/**
	 * 根据资源ID获取资源信息
	 *
	 * @param id 资源ID
	 * @return LinkInfo 资源信息
	 * @date  2015年7月10日下午7:01:48
	 * @since 1.0.0
	*/
	LinkInfo getInfo(String id);

	/**
	 * 新增资源
	 *
	 * @param info 页面传入的资源信息
	 * @return LinkInfo 资源信息
	 * @date  2015年7月10日下午7:01:51
	 * @since 1.0.0
	*/
	LinkInfo create(LinkInfo info);
	/**
	 * 更新资源
	 *
	 * @param info 页面传入的资源信息
	 * @return LinkInfo 资源信息
	 * @date  2015年7月10日下午7:01:54
	 * @since 1.0.0
	*/
	LinkInfo update(LinkInfo info);
	/**
	 * 根据指定ID删除资源信息
	 *
	 * @param id 资源ID
	 * @date  2015年7月10日下午7:01:57
	 * @since 1.0.0
	*/
	void delete(String id);
	/**
	 * 上移/下移资源
	 * @param id
	 * @param up
	 */
	Long move(String id, boolean up);

}
