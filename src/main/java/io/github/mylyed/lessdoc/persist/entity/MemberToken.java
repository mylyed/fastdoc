package io.github.mylyed.lessdoc.persist.entity;

import java.io.Serializable;
import java.util.Date;

public class MemberToken implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_member_token.token_id
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private Integer tokenId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_member_token.member_id
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private Integer memberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_member_token.token
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_member_token.email
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_member_token.is_valid
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private Boolean isValid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_member_token.valid_time
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private Date validTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_member_token.send_time
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private Date sendTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table md_member_token
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_member_token.token_id
     *
     * @return the value of md_member_token.token_id
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public Integer getTokenId() {
        return tokenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_member_token.token_id
     *
     * @param tokenId the value for md_member_token.token_id
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_member_token.member_id
     *
     * @return the value of md_member_token.member_id
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_member_token.member_id
     *
     * @param memberId the value for md_member_token.member_id
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_member_token.token
     *
     * @return the value of md_member_token.token
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_member_token.token
     *
     * @param token the value for md_member_token.token
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_member_token.email
     *
     * @return the value of md_member_token.email
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_member_token.email
     *
     * @param email the value for md_member_token.email
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_member_token.is_valid
     *
     * @return the value of md_member_token.is_valid
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public Boolean getIsValid() {
        return isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_member_token.is_valid
     *
     * @param isValid the value for md_member_token.is_valid
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_member_token.valid_time
     *
     * @return the value of md_member_token.valid_time
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public Date getValidTime() {
        return validTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_member_token.valid_time
     *
     * @param validTime the value for md_member_token.valid_time
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_member_token.send_time
     *
     * @return the value of md_member_token.send_time
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_member_token.send_time
     *
     * @param sendTime the value for md_member_token.send_time
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}