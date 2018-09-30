package org.xiaoxingqi.gmdoc.tools;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yzm on 2017/11/30.
 */

public class AppConfig {
    public static final String AT = "@[\\w\\p{InCJKUnifiedIdeographs}-]{1,26}";// @人
    private static final String TOPIC = "#[\\p{Print}\\p{InCJKUnifiedIdeographs}&&[^#]]+#";// ##话题
    public static final String SPOLIER = "\\[剧透:[\\s\\S]*?]";
    public static final String EMOJI = ":[0-9a-zA-Z_]+:";//
    public static final String URLPATH = "((http|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    public static final String ALL = "(" + AT + ")" + "|" + "(" + TOPIC + ")" + "|" + "(" + URLPATH + ")";
    private static Pattern mPattern = Pattern.compile(EMOJI);

    public static String getImageHtml(String text) {
        String result = text;
        try {
            Matcher matcher = mPattern.matcher(text);
            while (matcher.find()) {
                String group = matcher.group();
                if (group != null) {
                    String faceId = null;
                    if ((faceId = FaceData.gifFaceInfo.get(group)) != null) {
                        result = result.replaceFirst(group, "<img src=\"file:///android_asset/" + faceId + "\">");
                    } /*else if ((faceId = FaceData.staticFaceInfo.get(group)) != null) {
                    result = result.replaceFirst(group, *//*"<img src=\"file:///android_asset/" + faceId + "\">"*//*faceId);
                }*/
                }
            }
            return result.replace("\n", "<br/>");
        } catch (Exception e) {
            return result;
        }
    }


}
