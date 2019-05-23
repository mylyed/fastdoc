package io.github.mylyed.lessdoc.persist.mapper;

import io.github.mylyed.lessdoc.persist.entity.Attachment;
import io.github.mylyed.lessdoc.persist.entity.AttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttachmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    long countByExample(AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    int deleteByExample(AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    int deleteByPrimaryKey(Integer attachmentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    int insert(Attachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    int insertSelective(Attachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    List<Attachment> selectByExample(AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    Attachment selectByPrimaryKey(Integer attachmentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    int updateByExample(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    int updateByPrimaryKeySelective(Attachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated Wed May 22 22:45:58 CST 2019
     */
    int updateByPrimaryKey(Attachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table md_attachment
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Attachment selectOneByExample(AttachmentExample example);
}