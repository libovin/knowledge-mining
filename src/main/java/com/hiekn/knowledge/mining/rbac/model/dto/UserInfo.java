package com.hiekn.knowledge.mining.rbac.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserInfo {
	
	private String id;

	@NotBlank(message = "角色id不能为空")
	private String roleId;

	@NotBlank(message = "用户名不能为空")
	private String username;

}