package io.github.mylyed.lessdoc.service;

import io.github.mylyed.lessdoc.persist.entity.Book;
import io.github.mylyed.lessdoc.persist.entity.BookExample;
import io.github.mylyed.lessdoc.persist.mapper.BookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lilei
 * created at 2019/5/1
 */
@Service
public class BookService {

    @Resource
    BookMapper bookMapper;

    public Book findBookByIdentify(String identify) {
        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andIdentifyEqualTo(identify);

        return bookMapper.selectOneByExample(bookExample);
    }
}
