package cn.sparke.core.modules.log.aop;

import cn.sparke.core.common.constants.dictmap.base.AbstractDictMap;
import cn.sparke.core.common.constants.dictmap.base.SystemDict;
import cn.sparke.core.common.constants.dictmap.factory.DictMapFactory;
import cn.sparke.core.common.entity.ZTreeNode;
import cn.sparke.core.common.support.HttpKit;
import cn.sparke.core.common.utils.Contrast;
import cn.sparke.core.modules.log.LogManager;
import cn.sparke.core.modules.log.LogObjectHolder;
import cn.sparke.core.modules.log.annotation.BussinessLog;
import cn.sparke.core.modules.log.factory.LogTaskFactory;
import cn.sparke.core.modules.shiro.ShiroKit;
import cn.sparke.core.modules.shiro.ShiroUser;
import cn.sparke.modules.system.menu.entity.Menu;
import cn.sparke.modules.system.menu.mapper.MenuMapper;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 日志记录
 *
 * @author spark
 * @date 2016年12月6日 下午8:48:30
 */
@Aspect
@Component
public class LogAopByExecution {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private MenuMapper menuMapper;

//    private HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
// || execution(* cn.sparke.modules..*.controller.*.add(..)) || execution(* cn.sparke.modules..*.controller.*.delete(..));
    // *(..))
    //业务日志操作
    @Pointcut(value = "execution(* cn.sparke.*..controller.*.update(..)) || execution(* cn.sparke.*..controller.*.add(..)) || execution(* cn.sparke.*..controller.*.delete(..))")
    public void cutBizService() {
    }

    @Around(value = "cutBizService()")
    public Object recordBizLog(ProceedingJoinPoint point) throws Throwable {

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
        String methodArgs = JSON.toJSONString(point.getArgs());
        Object[] params = point.getArgs();
        HttpServletRequest request = HttpKit.getRequest();
        String requestUri = request.getRequestURI();
        //获取操作名称
        Menu menu = this.getMenuInfo(requestUri);
        if (menu == null){
            return;
        }

        String bussinessName = menu.getName();
        String key = "id";
        String dictClass = SystemDict.class.getName();


        Map<String, String> parameters = HttpKit.getRequestParameters();
        String msg = JSON.toJSONString(parameters);
        String contentType = request.getContentType();
        if (contentType.contains("json")||contentType.contains("JSON")){
            msg += JSON.toJSONString(methodArgs);
        }
        //如果涉及到修改,比对变化

        if (bussinessName.contains("修改") || bussinessName.contains("编辑") || methodName.contains("update")) {
            if(!bussinessName.contains("修改") || !bussinessName.contains("编辑")){
                bussinessName += "修改";
            }
        } else if (bussinessName.contains("新增") || bussinessName.contains("增加") || methodName.contains("add")){
            if(!bussinessName.contains("新增") || !bussinessName.contains("新增")){
                bussinessName += "新增";
            }
        }else if (bussinessName.contains("删除") || methodName.contains("delete")){
            if(!bussinessName.contains("删除") ){
                bussinessName += "删除";
            }
        }

        LogManager.me().executeLog(LogTaskFactory.bussinessLog(user.getId(), bussinessName, className, methodName, msg));
    }

    private Menu getMenuInfo(String menuPath){
        List<Menu> nodeList = menuMapper.findList(new Menu());
        for (Menu zTreeNode:nodeList) {
            if (menuPath.contains(zTreeNode.getUrl())){
                return zTreeNode;
            }
        }
        return null;
    }
}