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
package net.leeautumn.remoting.protocol;

import java.util.Arrays;

/**
 * Created by LeeAutumn on 04/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public abstract class AbstractProtocolSerializer implements ProtocolSerializer {

    //这个步骤主要是用来在第一个byte字段表明 通信协议类型,这样在服务器上就能根据这个标志来序列化特定的ProtocolMessage了.
    @Override
    public byte[] serializeMessage(Object protocolMessage) {
        byte[] realMessage = serializeRealMessage(protocolMessage);
        byte[] result = new byte[realMessage.length + 1];
        result[0] = ((ProtocolMessage)protocolMessage).getHeader().getProtocolType();
        System.arraycopy(realMessage,0,result,1,realMessage.length);
        return result;
    }

    public abstract byte[] serializeRealMessage(Object protocolMessage);
}
