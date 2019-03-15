package com.hiekn.knowledge.mining.rbac.model.dto;

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

	private List<LinkInfo> children = new ArrayList<>();

}
