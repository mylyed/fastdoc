package io.github.mylyed.lessdoc.persist.entity;

import java.io.Serializable;

public class Option implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_options.option_id
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private Integer optionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_options.option_title
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private String optionTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_options.option_name
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private String optionName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_options.option_value
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private String optionValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_options.remark
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table md_options
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_options.option_id
     *
     * @return the value of md_options.option_id
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public Integer getOptionId() {
        return optionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_options.option_id
     *
     * @param optionId the value for md_options.option_id
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_options.option_title
     *
     * @return the value of md_options.option_title
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public String getOptionTitle() {
        return optionTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_options.option_title
     *
     * @param optionTitle the value for md_options.option_title
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle == null ? null : optionTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_options.option_name
     *
     * @return the value of md_options.option_name
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public String getOptionName() {
        return optionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_options.option_name
     *
     * @param optionName the value for md_options.option_name
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_options.option_value
     *
     * @return the value of md_options.option_value
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public String getOptionValue() {
        return optionValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_options.option_value
     *
     * @param optionValue the value for md_options.option_value
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue == null ? null : optionValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_options.remark
     *
     * @return the value of md_options.remark
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_options.remark
     *
     * @param remark the value for md_options.remark
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}