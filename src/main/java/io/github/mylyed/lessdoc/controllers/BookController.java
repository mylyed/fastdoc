package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.common.PojoUtil;
import io.github.mylyed.lessdoc.persist.entity.Book;
import io.github.mylyed.lessdoc.persist.entity.BookExample;
import io.github.mylyed.lessdoc.persist.mapper.BookMapper;
import io.github.mylyed.lessdoc.persist.mapper.TeamMemberMapper;
import io.github.mylyed.lessdoc.persist.mapper.TeamRelationshipMapper;
import io.github.mylyed.lessdoc.response.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 项目管理
 *
 * @author lilei
 * created at 2019/5/4
 */

@Controller
@RequestMapping("/book")
public class BookController extends BaseController {


    @Resource
    BookMapper bookMapper;

    @Resource
    TeamMemberMapper teamMemberMapper;

    @Resource
    TeamRelationshipMapper teamRelationshipMapper;

    @GetMapping({"", "/", "/index"})
    public String index(Model model, Integer page) {
        List<Book> books = bookMapper.selectByExample(new BookExample());

//        final Member member = TokenHolder.loginedMember();
//
//        TeamRelationshipExample teamRelationshipExample = new TeamRelationshipExample();
//        teamRelationshipExample.createCriteria().andBookIdEqualTo(p.getBookId());
//
//        TeamRelationship teamRelationship = teamRelationshipMapper.selectOneByExample(teamRelationshipExample);
//
//        TeamMemberExample teamMemberExample = new TeamMemberExample();
//        teamMemberExample.createCriteria().andMemberIdEqualTo(member.getMemberId());
//        List<TeamMember> teamMembers = teamMemberMapper.selectByExample(teamMemberExample);


        model.addAttribute("books", books.stream().map(p -> {
            Map map = PojoUtil.pojo2Map(p);

//
//            map.put("roleName", Role.values()[teamMember.getRoleId()]);
            return map;
        }).collect(Collectors.toList()));
        int pageIndex = page == null ? 1 : page;
        int pageSize = 18;
        Pagination pagination = new Pagination(pageIndex, pageSize, books.size() + 0L);
        model.addAttribute("pagination", pagination);
        return "book/index";

    }

}
