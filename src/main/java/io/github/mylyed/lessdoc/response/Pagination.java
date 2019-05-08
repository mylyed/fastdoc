package io.github.mylyed.lessdoc.response;

import org.springframework.util.Assert;

/**
 * @author lilei
 * created at 2019/5/1
 */
public class Pagination {

    /**
     * 总共的数据条数
     */
    private long totalElements;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 是否是最后一页
     */
    private Boolean last;
    /**
     * 是否是第一页
     */
    private Boolean first;

    /**
     * 没有页多少条数据
     */
    private int size;
    /**
     * 当前第几页
     */
    private int number;

    /**
     * @param page          请求第几页
     * @param pageSize      每页多少条数据
     * @param totalElements 总共多少条数据
     */
    public Pagination(Integer page, Integer pageSize, Long totalElements) {
        Assert.notNull(page, "page参数为空");
        Assert.notNull(pageSize, "pageSize参数为空");
        Assert.notNull(totalElements, "totalElements参数为空");

        this.totalElements = totalElements;
        if ((totalElements % pageSize) == 0) {
            //能整除
            this.totalPages = (totalElements.intValue() / pageSize);
        } else {
            this.totalPages = (totalElements.intValue() / pageSize) + 1;
        }
        first = (page == 1);
        last = page == totalPages;
        size = pageSize;
        number = page;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                ", first=" + first +
                ", size=" + size +
                ", number=" + number +
                '}';
    }
}
