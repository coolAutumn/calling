/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.moopa.calling.servicesrv;

import net.moopa.calling.common.constants.Constant;
import net.moopa.calling.common.util.loadclass.LoadClasses;
import net.moopa.calling.servicesrv.config.ServiceServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by LeeAutumn on 30/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 *
 *  This class is used to help service provider register its service in the whole CallingServiceServer.
 *
 *  All service provider is referred by ServiceProviderAn class.
 */

public class RegisterSupporter {
    private static Logger logger = LoggerFactory.getLogger(Constant.REGISTER_SUPPORTER_LOGGERNAME);

    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    //通过扫描配置中的包来找到注解,并且进行注册ServiceProvider
    public static void register(Map<String,Object> registeredServices){

        String serviceProviderPackagePath = ServiceServerConfig.getConfig("servicesPackagePath");

        //根据包名来获得相关的class
        List<Class<?>> serviceImpllist = LoadClasses.getClassByPkgName(serviceProviderPackagePath,true,classLoader);

        findProviderClasses(serviceImpllist,registeredServices);

    }

    private static void findProviderClasses(List<Class<?>> servicelist,Map<String,Object> registeredServices) {
        for (Class<?> clazz : servicelist) {
            try {

                Object serviceImpl = clazz.newInstance();

//                ServicesAn servicesAn = clazz.getAnnotation(ServicesAn.class);

                registeredServices.put(clazz.getInterfaces()[0].getName(), serviceImpl);

                logger.info("Succeed register service : {}", clazz.getInterfaces()[0].getName());
            }catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
