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
package net.moopa.calling.remoting.protocol;

import net.moopa.calling.remoting.protocol.callingprotocol.CallingProtocolMessage;
import net.moopa.calling.remoting.protocol.callingprotocol.CallingProtocolSerializer;

/**
 * Created by LeeAutumn on 30/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 */
public enum ProtocolType {
    CallingProtocol((byte)0xCA,"CallingProtocol");

    byte protocolType;
    String protocolName;

    ProtocolType(byte protocolType,String protocolName){
        this.protocolType = protocolType;
        this.protocolName = protocolName;
    }

    public static ProtocolSerializer getSerializerByType(ProtocolType type){
        switch (type){
            case CallingProtocol:
                return new CallingProtocolSerializer();
            default:
                return null;
        }
    }

    public static Class<?> getMessageClassByType(ProtocolType type){
        switch (type){
            case CallingProtocol:
                return CallingProtocolMessage.class;
            default:
                return null;
        }
    }

    public static ProtocolType getProtocolTypeByByte(byte b){
        switch (b){
            case (byte)0xCA:
                return CallingProtocol;
            default:
                return null;
        }
    }

}
