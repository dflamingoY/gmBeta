package org.xiaoxingqi.gmdoc.entity.game;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yzm on 2017/11/3.
 */

public class BaseGameBean implements Parcelable {

    /**
     * cover : http://is1.mzstatic.com/image/thumb/Purple128/v4/a5/bb/e5/a5bbe51f-5ba0-575d-de94-da6f65deb3a2/source/175x175bb.jpg
     * created_at : 0000-00-00 00:00:00
     * developer : Shenzhen Tencent Computer Systems Company Limited
     * extra : 需要 iOS 7.0 或更高版本。与 i
     * game_name : 王者荣耀
     * id : 100507
     * introduce : 王者荣耀：无处不团，两亿好友聚好玩【游戏介绍】《王者荣耀》是腾讯第一5V5英雄公平对战手游，腾讯最新MOBA手游大作！5V5王者峡谷、5V5深渊大乱斗、以及3V3、1V1等多样模式一键体验，热血竞技尽享快感！海量英雄随心选择，精妙配合默契作战！10秒实时跨区匹配，与好友组队登顶最
     * introduce_2 : 强王者！操作简单易上手，一血、五杀、超神，极致还原经典体验！实力操作公平对战，回归MOBA初心！赶快加入《王者荣耀》，随时开启你的激情团战！ 【游戏特色】1、5V5！越塔强杀！超神！5V5经典地图，三路推塔，呈现原汁原味的对战体验。英雄策略搭配，组建最强阵容，默契配合极限666！2、深渊大乱斗！随机英雄一路团战！5V5大乱斗，即刻激情团战！随机盲选英雄，全团杀中路，冲突一触即发！一条路，全神装，血战到底！3、随时开团！10分钟爽一把！适合手机的MOBA游戏，10分钟享受极致竞技体验。迂回作战，手脑配合，一战到底！人多，速来！4、公平竞技！好玩不坑拼实力！凭实力carry全场，靠技术决定胜负。不做英雄养成，不设体力，还你最初的游戏乐趣！5、指尖超神！精妙走位秀操作！微操改变战局！手速流？意识流？看我精妙走位，力压群雄，打出钻石操作！收割，连杀超神！【特别说明】在游戏《王者荣耀》中，用户登录时可以选择“与QQ好友玩/与微信好友玩/游客登录”，三种登录方式在iOS设备上的游戏数据不互通（包括等级、钻石、金币等）。用户在游戏中购买的游戏代币“点券”仅限在本应用中使用。腾讯的虚拟货币，比如Q币、Q点无法在本应用中使用。【联系我们】如果您喜欢我们的游戏，欢迎随时给我们评价、留言。官方网站：http://pvp.qq.com官方微信：heromoba
     * link : https://itunes.apple.com/cn/app/%E7%8E%8B%E8%80%85%E8%8D%A3%E8%80%80/id989673964?mt=8
     * platform : IOS
     * platform_id : 28
     * release : 1
     * remarks1 : 免费
     * remarks2 : 2017年07月21日
     * sale_time : 1970-01-01
     * score : 10
     * state : 1
     * type : 热门,动作,策略
     * updated_at : 2017-07-28 23:41:16
     * ver_name : 国行
     * version : 1
     */

    private String cover;
    private String created_at;
    private String developer;
    private String extra;
    private String game_name;
    private String id;
    private String introduce;
    private String introduce_2;
    private String link;
    private String platform;
    private int platform_id;
    private int release;
    private String remarks1;
    private String remarks2;
    private String sale_time;
    private float score;
    private int state;
    private String type;
    private String updated_at;
    private String ver_name;
    private String version;
    private String user_id;
    private String game_id;//更多想玩 在玩中的数据
    private int f_score;//关注均分

    protected BaseGameBean(Parcel in) {
        cover = in.readString();
        created_at = in.readString();
        developer = in.readString();
        extra = in.readString();
        game_name = in.readString();
        id = in.readString();
        introduce = in.readString();
        introduce_2 = in.readString();
        link = in.readString();
        platform = in.readString();
        platform_id = in.readInt();
        release = in.readInt();
        remarks1 = in.readString();
        remarks2 = in.readString();
        sale_time = in.readString();
        score = in.readFloat();
        state = in.readInt();
        type = in.readString();
        updated_at = in.readString();
        ver_name = in.readString();
        version = in.readString();
        user_id = in.readString();
        game_id = in.readString();
        f_score = in.readInt();
    }

    public static final Creator<BaseGameBean> CREATOR = new Creator<BaseGameBean>() {
        @Override
        public BaseGameBean createFromParcel(Parcel in) {
            return new BaseGameBean(in);
        }

        @Override
        public BaseGameBean[] newArray(int size) {
            return new BaseGameBean[size];
        }
    };

    public int getF_score() {
        return f_score;
    }

    public void setF_score(int f_score) {
        this.f_score = f_score;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduce_2() {
        return introduce_2;
    }

    public void setIntroduce_2(String introduce_2) {
        this.introduce_2 = introduce_2;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }

    public String getRemarks2() {
        return remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public String getSale_time() {
        return sale_time;
    }

    public void setSale_time(String sale_time) {
        this.sale_time = sale_time;
    }


    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getVer_name() {
        return ver_name;
    }

    public void setVer_name(String ver_name) {
        this.ver_name = ver_name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cover);
        dest.writeString(created_at);
        dest.writeString(developer);
        dest.writeString(extra);
        dest.writeString(game_name);
        dest.writeString(id);
        dest.writeString(introduce);
        dest.writeString(introduce_2);
        dest.writeString(link);
        dest.writeString(platform);
        dest.writeInt(platform_id);
        dest.writeInt(release);
        dest.writeString(remarks1);
        dest.writeString(remarks2);
        dest.writeString(sale_time);
        dest.writeFloat(score);
        dest.writeInt(state);
        dest.writeString(type);
        dest.writeString(updated_at);
        dest.writeString(ver_name);
        dest.writeString(version);
        dest.writeString(user_id);
        dest.writeString(game_id);
        dest.writeInt(f_score);
    }
}
