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
package net.leeautumn.client.demo;

import net.leeautumn.client.ClientFactory;
import net.leeautumn.services.services.HelloService;
import net.leeautumn.services.servicesImpl.HelloServiceImpl;

import java.net.*;

/**
 * Created by LeeAutumn on 03/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public class ClientDemo {

    public static void main(String[] args) {
        try {
            HelloService helloService = ClientFactory.newClient(HelloServiceImpl.class,InetAddress.getByName("localhost"),3341);
            System.out.println(helloService.sayHello("World"));
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
