package com.kfc.model.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 536140254814307696L;

    private long userId;

    @NotEmpty(message = "名称不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "邮箱格式不正确")
    private String email;

    private String avatar;

    private String role;

    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$",message = "手机号格式不正确")
    private String phone;

    private Date createdAt;

    private Date updatedAt;

    private int isDeleted;

    private Date deletedAt;


}
