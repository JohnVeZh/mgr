package cn.sparke.core.common.constants.dictmap.factory;


import cn.sparke.core.common.constants.factory.ConstantFactory;
import cn.sparke.core.common.constants.factory.IConstantFactory;
import cn.sparke.core.common.exception.BizExceptionEnum;
import cn.sparke.core.common.exception.BussinessException;

import java.lang.reflect.Method;

/**
 * 字段的包装创建工厂
 *
 * @author spark
 * @date 2017-05-06 15:12
 */
public class DictFieldWarpperFactory {

    public static Object createFieldWarpper(Object field, String methodName) {
        IConstantFactory me = ConstantFactory.me();
        try {
            Method method = IConstantFactory.class.getMethod(methodName, field.getClass());
            return method.invoke(me, field);
        } catch (Exception e) {
            try {
                Method method = IConstantFactory.class.getMethod(methodName, Integer.class);
                return method.invoke(me, Integer.parseInt(field.toString()));
            } catch (Exception e1) {
                throw new BussinessException(BizExceptionEnum.ERROR_WRAPPER_FIELD);
            }
        }
    }

}
