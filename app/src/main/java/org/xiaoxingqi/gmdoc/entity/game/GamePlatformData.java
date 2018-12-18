package org.xiaoxingqi.gmdoc.entity.game;

import java.util.List;

/**
 * Created by yzm on 2017/11/3.
 */

public class GamePlatformData {
    private int state;
    private List<PlatformListData> pla_list;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public List<PlatformListData> getPla_list() {
        return pla_list;
    }

    public void setPla_list(List<PlatformListData> pla_list) {
        this.pla_list = pla_list;
    }

    public static class PlatformListData {
        private int id;
        private String name;
        private String url;
        private List<PlatformData> version;

        public List<PlatformData> getVersion() {
            return version;
        }

        public void setVersion(List<PlatformData> version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class PlatformData {
        private int id;
        private String name;
        private boolean isSelected;

        public PlatformData() {
        }

        public PlatformData(int id, String name, boolean isSelected) {
            this.id = id;
            this.name = name;
            this.isSelected = isSelected;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
