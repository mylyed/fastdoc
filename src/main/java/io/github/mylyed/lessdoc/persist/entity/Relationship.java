package io.github.mylyed.lessdoc.persist.entity;

import java.io.Serializable;

public class Relationship implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_relationship.relationship_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    private Integer relationshipId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_relationship.member_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    private Integer memberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_relationship.book_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    private Integer bookId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column md_relationship.role_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    private Integer roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table md_relationship
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_relationship.relationship_id
     *
     * @return the value of md_relationship.relationship_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    public Integer getRelationshipId() {
        return relationshipId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_relationship.relationship_id
     *
     * @param relationshipId the value for md_relationship.relationship_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    public void setRelationshipId(Integer relationshipId) {
        this.relationshipId = relationshipId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_relationship.member_id
     *
     * @return the value of md_relationship.member_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_relationship.member_id
     *
     * @param memberId the value for md_relationship.member_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_relationship.book_id
     *
     * @return the value of md_relationship.book_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_relationship.book_id
     *
     * @param bookId the value for md_relationship.book_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column md_relationship.role_id
     *
     * @return the value of md_relationship.role_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column md_relationship.role_id
     *
     * @param roleId the value for md_relationship.role_id
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}