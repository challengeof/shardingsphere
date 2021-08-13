/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.proxy.backend.text.distsql.ral.common.hint.executor;

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.proxy.backend.response.header.ResponseHeader;
import org.apache.shardingsphere.proxy.backend.response.header.update.UpdateResponseHeader;
import org.apache.shardingsphere.proxy.backend.text.distsql.ral.common.hint.HintSourceType;
import org.apache.shardingsphere.proxy.backend.text.sctl.hint.internal.HintManagerHolder;
import org.apache.shardingsphere.readwritesplitting.distsql.parser.statement.hint.SetReadwriteSplittingHintSourceStatement;

/**
 * Set readwrite-splitting hint source statement executor.
 */
@RequiredArgsConstructor
public final class SetReadwriteSplittingHintSourceExecutor extends AbstractHintUpdateExecutor<SetReadwriteSplittingHintSourceStatement> {
    
    private final SetReadwriteSplittingHintSourceStatement sqlStatement;
    
    @Override
    public ResponseHeader execute() {
        HintSourceType sourceType = HintSourceType.typeOf(sqlStatement.getSource());
        switch (sourceType) {
            case AUTO:
                HintManagerHolder.get().setReadwriteSplittingAuto();
                break;
            case WRITE:
                HintManagerHolder.get().setWriteRouteOnly();
                break;
            default:
                break;
        }
        return new UpdateResponseHeader(null);
    }
}