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
package net.moopa3376.calling.remoting.protocol.callingprotocol;

import net.moopa3376.calling.remoting.rpc.RPCRequest;

/**
 * Created by LeeAutumn on 04/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public class CallingProtocolRPCRequest implements RPCRequest {
    String serviceClassName;
    String methodDesc;
    Object[] args;

    @Override
    public String getServiceClassName() {
        return serviceClassName;
    }

    @Override
    public String getMethodDesc() {
        return methodDesc;
    }

    @Override
    public Object[] getArgs() {
        return args;
    }

    @Override
    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    @Override
    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    @Override
    public void setArgs(Object[] args) {
        this.args = args;
    }
}
