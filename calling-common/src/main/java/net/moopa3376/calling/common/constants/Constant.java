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
package net.moopa3376.calling.common.constants;

/**
 * Created by LeeAutumn on 19/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 */
public class Constant {

    /* LoggerName */
    //common-util
    public static String PROPERTIES_FILE_UTIL_LOGGERNAME        =       "common-PropertiesFileUtil";
    public static String LOADCLASS_LOGGERNAME                   =       "common-LoadClass";


    //service-manager
    public static String PROCESS_REQUEST_HANDLER_LOGGERNAME     =       "servicemanager-ProcessRequestHandler";

    //service-server
    public static String SERVICE_SERVER_LOGGERNAME              =       "servicesrv-ServiceServer";
    public static String REGISTER_SUPPORTER_LOGGERNAME          =       "servicesrv-RegisterSupporter";

    //service providers
    public static String HELLO_SERVICEPROVIDER_LOGGERNAME       =       "serviceprovider-HelloService";


    //remoting
    public static String LINE_SEPARATOR                         =       System.getProperty("line.separator");

    //console
    public static String COMMAND_EXECUTOR_LOGGERNAME            =       "console-CommandExecutor";
}
