package cn.sparke.core.modules.log.aop;

import cn.sparke.core.common.constants.dictmap.base.AbstractDictMap;
import cn.sparke.core.common.constants.dictmap.factory.DictMapFactory;
import cn.sparke.core.common.support.HttpKit;
import cn.sparke.core.common.utils.Contrast;
import cn.sparke.core.modules.log.LogManager;
import cn.sparke.core.modules.log.LogObjectHolder;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.log.factory.LogTaskFactory;
import cn.sparke.core.modules.shiro.ShiroKit;
import cn.sparke.core.modules.shiro.ShiroUser;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 日志记录
 *
 * @author spark
 * @date 2016年12月6日 下午8:48:30
 */
//@Aspect
//@Component
public class LogAopByAnnotation {

    private Logger log = Logger.getLogger(this.getClass());

    @Pointcut(value = "@annotation(cn.sparke.core.modules.log.annotation.BussinessLog)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point) throws Exception {

        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //如果当前用户未登录，不做日志
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return;
        }

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        String dictClass = annotation.dict().getName();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

        //如果涉及到修改,比对变化
        String msg;
        if (bussinessName.contains("修改") || bussinessName.contains("编辑")) {
            Object obj1 = LogObjectHolder.me().get();
            Map<String, String> obj2 = HttpKit.getRequestParameters();
            msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
        } else {
            Map<String, String> parameters = HttpKit.getRequestParameters();
            AbstractDictMap dictMap = DictMapFactory.createDictMap(dictClass);
            msg = Contrast.parseMutiKey(dictMap, key, parameters);
        }

        LogManager.me().executeLog(LogTaskFactory.bussinessLog(user.getId(), bussinessName, className, methodName, msg));
    }
}