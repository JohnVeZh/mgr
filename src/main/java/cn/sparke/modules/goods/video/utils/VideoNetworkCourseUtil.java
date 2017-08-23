package cn.sparke.modules.goods.video.utils;

import cn.sparke.modules.goods.video.entity.VideoEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

/**
 * Created by wanghaiguang on 2017/7/26.
 */
public class VideoNetworkCourseUtil {


    public static void handleNetworkCourseId(String networkCourseId, Model model){
        String catalogId = null;
        if(!StringUtils.isEmpty(networkCourseId)) {
            String[] ids = networkCourseId.split(",");
            networkCourseId = ids[0];
            if (ids.length > 1) {
                catalogId = ids[1];
            }
        }
        model.addAttribute("networkCourseId",networkCourseId);
        model.addAttribute("catalogId",catalogId);
    }

    public static VideoEntity handleNetworkCourseIdForEntity(String networkCourseId,VideoEntity entity){
        String catalogId = null;
        if(!StringUtils.isEmpty(networkCourseId)) {
            String[] ids = networkCourseId.split(",");
            networkCourseId = ids[0];
            if (ids.length > 1) {
                catalogId = ids[1];
            }
            entity.setNetworkCourseId(networkCourseId);
            entity.setCatalogId(catalogId);
            return entity;
        }
        return entity;
    }

}
