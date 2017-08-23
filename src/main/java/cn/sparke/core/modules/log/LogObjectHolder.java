package cn.sparke.core.modules.log;

import cn.sparke.core.common.utils.SpringContextHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 被修改的bean临时存放的地方
 *
 * @author spark
 * @date 2017-03-31 11:19
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class LogObjectHolder {

    private Object object = null;

    public void set(Object obj) {
        this.object = obj;
    }

    public Object get() {
        return object;
    }

    public static LogObjectHolder me(){
        return SpringContextHolder.getBean(LogObjectHolder.class);
    }
}
