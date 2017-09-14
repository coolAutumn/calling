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

import net.moopa.calling.remoting.rpc.RPCResponse;
import net.moopa.calling.remoting.rpc.RPCResponseCode;

/**
 * Created by LeeAutumn on 04/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public class CallingProtocolRPCResponse implements RPCResponse {
    RPCResponseCode responseCode = null;
    Object rpcResult = null;
    Exception remoteException = null;

    @Override
    public RPCResponseCode getResponseCode() {
        return responseCode;
    }

    @Override
    public Object getRPCResult() {
        return rpcResult;
    }

    @Override
    public Exception getRemoteException() {
        return remoteException;
    }

    @Override
    public void setResponseCode(RPCResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public void setRPCResult(Object object) {
        this.rpcResult = object;
    }

    @Override
    public void setRemoteException(Exception exception) {
        this.remoteException = exception;
    }
}
