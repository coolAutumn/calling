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
package net.moopa.calling.remoting.protocol.callingprotocol;

import net.moopa.calling.remoting.protocol.ProtocolMessage;
import net.moopa.calling.remoting.protocol.ProtocolMessageBody;
import net.moopa.calling.remoting.protocol.ProtocolMessageHeader;

/**
 * Created by LeeAutumn on 24/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 */
public class CallingProtocolMessage implements ProtocolMessage {

    public static byte version      =   (byte) 0x01;        //version : 1

    public static byte minorVersion   =   (byte) 0x02;        //minor version : 2

    public ProtocolMessageHeader header;

    public ProtocolMessageBody body;

    public CallingProtocolMessage(){
        this.header = new CallingProtocolMessageHeader();
        this.body = new CallingProtocolMessageBody();
    }

    public ProtocolMessageHeader getHeader() {
        return header;
    }

    public void setHeader(ProtocolMessageHeader header) {
        this.header =  header;
    }

    public ProtocolMessageBody getBody() {
        return body;
    }

    public void setBody(ProtocolMessageBody body) {
        this.body = body;
    }

}
