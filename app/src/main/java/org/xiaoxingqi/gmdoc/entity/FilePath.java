package org.xiaoxingqi.gmdoc.entity;

import java.io.Serializable;

/**
 * Created by DoctorKevin on 2017/7/17.
 */

public class FilePath implements Serializable {

    private String path;
    private boolean isSelected;
    private int count;
    private String firstPath;
    private String name;

    public FilePath(String path, boolean isSelected) {
        this.path = path;
        this.isSelected = isSelected;
    }

    public FilePath() {

    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFirstPath() {
        return firstPath;
    }

    public void setFirstPath(String firstPath) {
        this.firstPath = firstPath;
    }

    public FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilePath filePath = (FilePath) o;

        if (isSelected() != filePath.isSelected()) return false;
        return getPath().equals(filePath.getPath());
    }

    @Override
    public int hashCode() {
        int result = getPath().hashCode();
        result = 31 * result + (isSelected() ? 1 : 0);
        return result;
    }
}
