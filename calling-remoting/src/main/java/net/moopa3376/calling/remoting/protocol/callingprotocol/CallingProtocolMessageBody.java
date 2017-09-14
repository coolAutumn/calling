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

import net.moopa3376.calling.remoting.protocol.ProtocolMessageBody;
import net.moopa3376.calling.remoting.rpc.RPCRequest;
import net.moopa3376.calling.remoting.rpc.RPCResponse;

/**
 * Created by LeeAutumn on 30/12/2016.
 * blog: leeautumn.net
 *
 * @author LeeAutumn
 */
public class CallingProtocolMessageBody implements ProtocolMessageBody {
    RPCRequest rpcRequest;
    RPCResponse rpcResponse;

    public CallingProtocolMessageBody(){
        this.rpcRequest = new CallingProtocolRPCRequest();
        this.rpcResponse = new CallingProtocolRPCResponse();
    }


    @Override
    public RPCRequest getRPCRequest() {
        return rpcRequest;
    }

    @Override
    public RPCResponse getRPCResponse() {
        return rpcResponse;
    }

    @Override
    public void setRPCRequest(RPCRequest rpcRequest) {
        this.rpcRequest = rpcRequest;
    }

    @Override
    public void setRPCResponse(RPCResponse rpcResponse) {
        this.rpcResponse = rpcResponse;
    }
}
