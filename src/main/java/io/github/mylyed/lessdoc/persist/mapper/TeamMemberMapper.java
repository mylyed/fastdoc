package io.github.mylyed.lessdoc.persist.mapper;

import io.github.mylyed.lessdoc.persist.entity.TeamMember;
import io.github.mylyed.lessdoc.persist.entity.TeamMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeamMemberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    long countByExample(TeamMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    int deleteByExample(TeamMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    int deleteByPrimaryKey(Integer teamMemberId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    int insert(TeamMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    int insertSelective(TeamMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    List<TeamMember> selectByExample(TeamMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    TeamMember selectByPrimaryKey(Integer teamMemberId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    int updateByExampleSelective(@Param("record") TeamMember record, @Param("example") TeamMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    int updateByExample(@Param("record") TeamMember record, @Param("example") TeamMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    int updateByPrimaryKeySelective(TeamMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated Fri May 10 23:30:51 CST 2019
     */
    int updateByPrimaryKey(TeamMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_team_member
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    TeamMember selectOneByExample(TeamMemberExample example);
}