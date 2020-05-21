package com.infowoo.purchase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleInfo {
    private Long id;

    private String role;

    private Long userId;

    private Date createdAt;

    private Date updatedAt;

}