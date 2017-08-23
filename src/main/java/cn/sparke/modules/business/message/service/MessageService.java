package cn.sparke.modules.business.message.service;

import cn.jiguang.common.connection.HttpProxy;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;
import cn.sparke.core.modules.jiguang.bean.JpushBean;
import cn.sparke.core.modules.jiguang.bean.JpushOptionsBean;
import cn.sparke.core.modules.jiguang.constants.AudienceTypeConstants;
import cn.sparke.core.modules.jiguang.constants.PlatformConstants;
import cn.sparke.core.modules.jiguang.service.JpushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import cn.sparke.modules.business.message.mapper.MessageMapper;
import cn.sparke.modules.business.message.entity.MessageEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息推送Dao
 *
 * @author spark
 * @Date 2017-07-24 14:17:38
 */
@Service
public class MessageService{
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    JpushService jpushService;

    public void save(MessageEntity message){
        message.preInsert();
        messageMapper.insert(message);
    }

    public void update(MessageEntity message){
        message.preUpdate();
        messageMapper.update(message);
    }

    public MessageEntity get(MessageEntity message){
        return messageMapper.get(message);
    }

    public MessageEntity getById(String id){
        return messageMapper.getById(id);
    }
    public Page<MessageEntity> findList(MessageEntity message){
       return messageMapper.findList(message);
    }

    public void deleteById(String id){
      messageMapper.deleteById(id);
    }

    /**
     * //自定义推送demo
     */
    public void sendNotificationAndMessage (MessageEntity messageEntity){
        JpushBean jpushBean = new JpushBean();
        List<Map<Integer,List<String>>> audienceMapList = new ArrayList<>();
        Map<Integer,List<String>> audienceMap = new HashMap<>();
        JpushOptionsBean options = new JpushOptionsBean() ;
//        List<String> audienceTag = new ArrayList<>();
//        audienceTag.add("tag1");
//        audienceTag.add("tag2");
//        audienceMap.put(AudienceTypeConstants.tag,audienceTag);
//        List<String> audienceAlias = new ArrayList<>();
//        audienceAlias.add("alias1");
//        audienceAlias.add("alias2");
//        audienceMap.put(AudienceTypeConstants.alias,audienceAlias);
        audienceMap.put(AudienceTypeConstants.all,new ArrayList<>());
        audienceMapList.add(audienceMap);
        options.setApnsProduction(false);//true 生产环境；false开发环境
        jpushBean.setPlatform(PlatformConstants.ALL);
        jpushBean.setAudience(audienceMapList);
        jpushBean.setNotification(messageEntity.getTitle());
        jpushBean.setOptions(options);
        jpushService.sendNotificationAndMessage(jpushBean);
    }
    /**
     * 官方推送demo
     */
    public void sendNotificationAll(MessageEntity messageEntity){
        final Logger LOG = LoggerFactory.getLogger(HttpProxy.class);
        JPushClient jPushClient = jpushService.getJpushClient();
        try {
            PushResult pushResult =  jPushClient.sendNotificationAll(messageEntity.getTitle());
        } catch (APIConnectionException e) {
            LOG.error("极光API请求网络错误！");
            throw new BussinessException(BizExceptionEnum.JPUSH_APICONNECTION_ERROR);
        } catch (APIRequestException e) {
            LOG.error("极光请求错误！");
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            throw new BussinessException(BizExceptionEnum.JPUSH_REQUEST_ERROR);
        }

    }
}
