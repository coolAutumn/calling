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
package net.moopa3376.calling.remoting.protocol;

import java.util.Map;

/**
 * Created by LeeAutumn on 04/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public interface ProtocolMessageHeader {
    /**
     * 获取协议号
     */
    byte getProtocolType();
    void setProtocolType(byte protocolType);

    /**
     * 获取消息类型 request or response
     */
    byte getMessageType();
    void setMessageType(byte type);

    /**
     * 获取协议主版本
     */
    byte getVersion();
    void setVersion(byte version);

    /**
     * 获得协议子版本号
     */
    byte getMinorVersion();
    void setMinorVersion(byte minorVersion);

    /**
     * 获得协议 attachment size
     */
    int getAttachmentSize();
    void setAttachmentSize(int size);

    /**
     * 获得协议 attachment
     */
    Map<String,Object> getAttachment();
    void setAttachment(Map<String,Object> attachment);
}
