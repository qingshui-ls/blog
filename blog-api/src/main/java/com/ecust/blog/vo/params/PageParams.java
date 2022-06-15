package com.ecust.blog.vo.params;

import lombok.Data;

/**
 * @authour shuangliang
 * @date 2022/6/2 - 12:49
 */
@Data
public class PageParams {
    private int page = 1;
    private int pageSize = 10;
    private Long categoryId;
    private Long tagId;
    private String year;
    private String month;

    public String getMonth() {
        // "06"
        if (this.month != null && this.month.length() == 1) {
            return "0" + this.month;
        }
        return this.month;
    }
}
