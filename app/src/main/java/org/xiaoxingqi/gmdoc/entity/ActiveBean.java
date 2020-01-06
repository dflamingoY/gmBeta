package org.xiaoxingqi.gmdoc.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ActiveBean implements Parcelable {
    /**
     * id : 1
     * activity_name : 异度之刃2
     * activity_pic : http://cdn.gmdoc.com//Dv3katEB_400x400.jpg
     * activity_desc : 包含#异度之刃2#和#异度神剑2#的动态和博文会在这里显示，欢迎与大家分享你的乐园见闻，关键剧情记得打剧透哦~
     */

    private int id;
    private String activity_name;
    private String activity_pic;
    private String activity_desc;

    protected ActiveBean(Parcel in) {
        id = in.readInt();
        activity_name = in.readString();
        activity_pic = in.readString();
        activity_desc = in.readString();
    }

    public static final Creator<ActiveBean> CREATOR = new Creator<ActiveBean>() {
        @Override
        public ActiveBean createFromParcel(Parcel in) {
            return new ActiveBean(in);
        }

        @Override
        public ActiveBean[] newArray(int size) {
            return new ActiveBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_pic() {
        return activity_pic;
    }

    public void setActivity_pic(String activity_pic) {
        this.activity_pic = activity_pic;
    }

    public String getActivity_desc() {
        return activity_desc;
    }

    public void setActivity_desc(String activity_desc) {
        this.activity_desc = activity_desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(activity_name);
        dest.writeString(activity_pic);
        dest.writeString(activity_desc);
    }
}
