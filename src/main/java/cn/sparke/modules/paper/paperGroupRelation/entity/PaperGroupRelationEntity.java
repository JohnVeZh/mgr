package cn.sparke.modules.paper.paperGroupRelation.entity;

import cn.sparke.core.common.entity.BaseEntity;
import java.util.*;
import java.math.*;

/**
 * 试卷组关系Entity
 *
 * @author spark
 * @Date 2017-07-27 16:23:44
 */
public class PaperGroupRelationEntity extends BaseEntity{
    //组ID
    private String groupId;
    //试卷ID
    private String paperId;

    public void setGroupId(String groupId){
        this.groupId = groupId;
    }
    public String getGroupId(){
        return this.groupId;
    }
    public void setPaperId(String paperId){
        this.paperId = paperId;
    }
    public String getPaperId(){
        return this.paperId;
    }

}
