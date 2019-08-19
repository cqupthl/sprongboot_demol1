package com.asion.springboot_demol1.VO;

import java.io.Serializable;
import java.util.List;

public class QueryVo implements Serializable {

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
