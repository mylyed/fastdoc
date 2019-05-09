package io.github.mylyed.lessdoc.persist.mapper;

import io.github.mylyed.lessdoc.model.HomeBook;
import io.github.mylyed.lessdoc.model.Limit;

import java.util.List;

public interface BookMapperExt {

    List<HomeBook> select4Anonymous(Limit limit);

    long count4Anonymous();

}