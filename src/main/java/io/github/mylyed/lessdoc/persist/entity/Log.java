package io.github.mylyed.lessdoc.persist.entity;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_logs.log_id
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private Long logId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_logs.member_id
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private Integer memberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_logs.category
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_logs.create_time
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_logs.user_agent
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_logs.ip_address
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String ipAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_logs.content
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_logs.original_data
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String originalData;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_logs.present_data
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private String presentData;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table md_logs
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_logs.log_id
     *
     * @return the value of md_logs.log_id
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_logs.log_id
     *
     * @param logId the value for md_logs.log_id
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_logs.member_id
     *
     * @return the value of md_logs.member_id
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_logs.member_id
     *
     * @param memberId the value for md_logs.member_id
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_logs.category
     *
     * @return the value of md_logs.category
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getCategory() {
        return category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_logs.category
     *
     * @param category the value for md_logs.category
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_logs.create_time
     *
     * @return the value of md_logs.create_time
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_logs.create_time
     *
     * @param createTime the value for md_logs.create_time
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_logs.user_agent
     *
     * @return the value of md_logs.user_agent
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_logs.user_agent
     *
     * @param userAgent the value for md_logs.user_agent
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_logs.ip_address
     *
     * @return the value of md_logs.ip_address
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_logs.ip_address
     *
     * @param ipAddress the value for md_logs.ip_address
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_logs.content
     *
     * @return the value of md_logs.content
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_logs.content
     *
     * @param content the value for md_logs.content
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_logs.original_data
     *
     * @return the value of md_logs.original_data
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getOriginalData() {
        return originalData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_logs.original_data
     *
     * @param originalData the value for md_logs.original_data
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setOriginalData(String originalData) {
        this.originalData = originalData == null ? null : originalData.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_logs.present_data
     *
     * @return the value of md_logs.present_data
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public String getPresentData() {
        return presentData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_logs.present_data
     *
     * @param presentData the value for md_logs.present_data
     *
     * @mbg.generated Tue May 07 23:33:41 CST 2019
     */
    public void setPresentData(String presentData) {
        this.presentData = presentData == null ? null : presentData.trim();
    }
}