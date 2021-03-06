package com.matthew.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Matthew
 * @Date 2019/9/14 18:08
 * @Version 1.0
 */
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        currentPage = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if (page == 1) {
            setShowPrevious(false);
        } else {
            setShowPrevious(true);
        }
        //是否展示下一页
        if (page.equals(totalPage)) {
            setShowNext(false);
        } else {
            setShowNext(true);
        }
        //是否展示第一页
        if (pages.contains(1)) {
            setShowFirstPage(false);
        } else {
            setShowFirstPage(true);
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            setShowEndPage(false);
        } else {
            setShowEndPage(true);
        }
    }
}
