package io.github.mylyed.lessdoc.persist.entity;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.member_id
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private Integer memberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.account
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String account;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.real_name
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String realName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.password
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.auth_method
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String authMethod;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.description
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.email
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.phone
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.avatar
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.role
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private Integer role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.status
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.create_time
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.create_at
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private Integer createAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_members.last_login_time
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private Date lastLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table md_members
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.member_id
     *
     * @return the value of md_members.member_id
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.member_id
     *
     * @param memberId the value for md_members.member_id
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.account
     *
     * @return the value of md_members.account
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.account
     *
     * @param account the value for md_members.account
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.real_name
     *
     * @return the value of md_members.real_name
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getRealName() {
        return realName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.real_name
     *
     * @param realName the value for md_members.real_name
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.password
     *
     * @return the value of md_members.password
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.password
     *
     * @param password the value for md_members.password
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.auth_method
     *
     * @return the value of md_members.auth_method
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getAuthMethod() {
        return authMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.auth_method
     *
     * @param authMethod the value for md_members.auth_method
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod == null ? null : authMethod.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.description
     *
     * @return the value of md_members.description
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.description
     *
     * @param description the value for md_members.description
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.email
     *
     * @return the value of md_members.email
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.email
     *
     * @param email the value for md_members.email
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.phone
     *
     * @return the value of md_members.phone
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.phone
     *
     * @param phone the value for md_members.phone
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.avatar
     *
     * @return the value of md_members.avatar
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.avatar
     *
     * @param avatar the value for md_members.avatar
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.role
     *
     * @return the value of md_members.role
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.role
     *
     * @param role the value for md_members.role
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.status
     *
     * @return the value of md_members.status
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.status
     *
     * @param status the value for md_members.status
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.create_time
     *
     * @return the value of md_members.create_time
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.create_time
     *
     * @param createTime the value for md_members.create_time
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.create_at
     *
     * @return the value of md_members.create_at
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public Integer getCreateAt() {
        return createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.create_at
     *
     * @param createAt the value for md_members.create_at
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_members.last_login_time
     *
     * @return the value of md_members.last_login_time
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_members.last_login_time
     *
     * @param lastLoginTime the value for md_members.last_login_time
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}