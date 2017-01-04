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
package net.leeautumn.remoting.protocol.callingprotocol;

import net.leeautumn.remoting.protocol.Code;
import net.leeautumn.remoting.protocol.ProtocolMessageHeader;

import java.util.Map;

/**
 * Created by LeeAutumn on 30/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 */
public class CallingProtocolMessageHeader implements ProtocolMessageHeader{
    public byte protocolType = (byte)0xCA;      //CallingProtocol的协议标识

    public byte type = 0x00;

    public byte version = CallingProtocolMessage.version;

    public byte minorVersion = CallingProtocolMessage.minorVersion;

    public int  attachmentSize = 0;

    public Map<String,Object> attachment = null;

    @Override
    public byte getProtocolType() {
        return protocolType;
    }

    @Override
    public byte getMessageType() {
        return type;
    }

    @Override
    public byte getVersion() {
        return version;
    }

    @Override
    public byte getMinorVersion() {
        return minorVersion;
    }

    @Override
    public int getAttachmentSize() {
        return attachmentSize;
    }

    @Override
    public Map<String, Object> getAttachment() {
        return attachment;
    }

    @Override
    public void setProtocolType(byte protocolType) {
        this.protocolType = protocolType;
    }

    @Override
    public void setMessageType(byte type) {
        this.type = type;
    }

    @Override
    public void setVersion(byte version) {
        this.version = version;
    }

    @Override
    public void setMinorVersion(byte minorVersion) {
        this.minorVersion = minorVersion;
    }

    @Override
    public void setAttachmentSize(int size) {
        this.attachmentSize = size;
    }

    @Override
    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }
}
