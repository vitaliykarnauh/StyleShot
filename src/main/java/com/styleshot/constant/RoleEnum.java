package com.styleshot.constant;

public enum RoleEnum {

    USER(2,"user"),EXPERT(1, "expert");

    private long roleId;

    private String roleName;

    private RoleEnum(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }


    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
