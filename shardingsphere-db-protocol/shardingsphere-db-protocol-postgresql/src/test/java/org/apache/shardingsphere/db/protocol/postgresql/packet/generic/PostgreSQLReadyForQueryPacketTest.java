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

package org.apache.shardingsphere.db.protocol.postgresql.packet.generic;

import io.netty.buffer.ByteBuf;
import org.apache.shardingsphere.db.protocol.postgresql.packet.ByteBufTestUtils;
import org.apache.shardingsphere.db.protocol.postgresql.packet.identifier.PostgreSQLMessagePacketType;
import org.apache.shardingsphere.db.protocol.postgresql.payload.PostgreSQLPacketPayload;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class PostgreSQLReadyForQueryPacketTest {
    
    @Test
    public void assertReadWriteWithInTransaction() {
        ByteBuf byteBuf = ByteBufTestUtils.createByteBuf(1);
        PostgreSQLPacketPayload payload = new PostgreSQLPacketPayload(byteBuf, StandardCharsets.UTF_8);
        PostgreSQLReadyForQueryPacket packet = new PostgreSQLReadyForQueryPacket(true);
        assertThat(packet.getIdentifier(), is(PostgreSQLMessagePacketType.READY_FOR_QUERY));
        packet.write(payload);
        assertThat(byteBuf.writerIndex(), is(1));
        assertThat(byteBuf.readByte(), is((byte) 'T'));
    }
    
    @Test
    public void assertReadWriteWithNotInTransaction() {
        ByteBuf byteBuf = ByteBufTestUtils.createByteBuf(1);
        PostgreSQLPacketPayload payload = new PostgreSQLPacketPayload(byteBuf, StandardCharsets.UTF_8);
        PostgreSQLReadyForQueryPacket packet = new PostgreSQLReadyForQueryPacket(false);
        assertThat(packet.getIdentifier(), is(PostgreSQLMessagePacketType.READY_FOR_QUERY));
        packet.write(payload);
        assertThat(byteBuf.writerIndex(), is(1));
        assertThat(byteBuf.readByte(), is((byte) 'I'));
    }
}
