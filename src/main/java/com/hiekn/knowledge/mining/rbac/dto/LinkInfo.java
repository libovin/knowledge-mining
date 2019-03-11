package com.hiekn.knowledge.mining.rbac.dto;

import com.hiekn.knowledge.mining.rbac.domain.LinkType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LinkInfo {

	private String id;

	private String parentId;

	private String name;

	private String link;

	private String icon;

	private LinkType type;

	private List<LinkInfo> children = new ArrayList<>();

}
