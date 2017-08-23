package cn.sparke.modules.popUp.task;

import cn.sparke.modules.popUp.entity.PopUpEntity;
import cn.sparke.modules.popUp.mapper.PopUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Ning
 * 2017/8/19.
 */
@Component
public class Timer {
    @Autowired
    private PopUpMapper popUpMapper;

    @Scheduled(fixedRate = 1000*60*5)
    public void expired() {
        List<PopUpEntity> popUpEntities = popUpMapper.getExpiredList();
        for (PopUpEntity popUpEntity:popUpEntities
             ) {
            popUpMapper.expired(popUpEntity.getId());
        }
    }
}
