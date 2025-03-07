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

package org.apache.shardingsphere.infra.executor.sql.federate.schema.table.generator;

import lombok.RequiredArgsConstructor;
import org.apache.calcite.DataContext;
import org.apache.calcite.rex.RexNode;
import org.apache.shardingsphere.sql.parser.sql.common.constant.QuoteCharacter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Federate execution sql generator.
 */
@RequiredArgsConstructor
public final class FederateExecutionSQLGenerator {
    
    private final DataContext root;
    
    private final List<RexNode> filters;
    
    private final int[] projects;
    
    private final List<String> columnNames;
    
    private final QuoteCharacter quoteCharacter;
    
    /**
     * Generate sql.
     *
     * @param table table
     * @return sql
     */
    public String generate(final String table) {
        // TODO generate sql with filters
        return String.format("SELECT %s FROM %s", getQuotedColumnNames(), quoteCharacter.wrap(table));
    }
    
    private String getQuotedColumnNames() {
        Collection<String> actualColumnNames = null == projects ? columnNames : Arrays.stream(projects).mapToObj(columnNames::get).collect(Collectors.toList());
        return actualColumnNames.stream().map(quoteCharacter::wrap).collect(Collectors.joining(", "));
    }
}
