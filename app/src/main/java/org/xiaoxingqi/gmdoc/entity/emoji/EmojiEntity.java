package org.xiaoxingqi.gmdoc.entity.emoji;

/**
 * Created by yzm on 2017/12/28.
 * 表情的实体
 */

public class EmojiEntity {
    /**
     * 图片名称
     */
    private String iconName;
    /**
     * 图片类型  gif emoji
     */
    private EmojiType type;

    public EmojiEntity(String iconName, EmojiType type) {
        this.iconName = iconName;
        this.type = type;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public EmojiType getType() {
        return type;
    }

    public void setType(EmojiType type) {
        this.type = type;
    }

    public enum EmojiType {
        GIF,
        EMOJI,
        DEL
    }
}
