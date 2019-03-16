package com.hiekn.knowledge.mining.rbac.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserInfo {

    public interface Create {
    }

    public interface Update {
    }
    public interface Login {

    }

    private String id;

    @NotBlank(message = "登陆名", groups = {Create.class,Login.class})
    private String username;

    @NotBlank(message = "显示名称", groups = Create.class)
    private String name;

    @NotBlank(message = "密码不能为空", groups = {Create.class, Update.class,Login.class})
    private String password;

    @NotBlank(message = "新密码不能为空", groups = {Update.class})
    private String newPwd;

    private String email;

}