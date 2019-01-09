package org.xiaoxingqi.gmdoc.entity.emoji;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzm on 2017/12/28.
 * 表情分页的实体
 */

public class EmojiArrayData {

    private List<EmojiEntity> data = new ArrayList<>();

    public List<EmojiEntity> getData() {
        return data;
    }

    public void setData(List<EmojiEntity> data) {
        this.data = data;
    }
}
