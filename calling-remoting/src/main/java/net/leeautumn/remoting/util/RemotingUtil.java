package net.leeautumn.remoting.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by LeeAutumn on 04/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public class RemotingUtil {

    public static String getMethodDesc(Method method){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName()+"&");
        for(Class<?> clazz : method.getParameterTypes()) {
            stringBuilder.append(clazz.getName());
        }
        return stringBuilder.toString();
    }

//    public static String

}
