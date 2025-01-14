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

package org.apache.shardingsphere.infra.config.datasource.props;

import com.google.common.base.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.shardingsphere.infra.config.datasource.pool.metadata.DataSourcePoolMetaDataFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * Data source properties.
 */
@Getter
public final class DataSourceProperties {
    
    public static final String CUSTOM_POOL_PROPS_KEY = "customPoolProps";
    
    private final String dataSourceClassName;
    
    @Getter(AccessLevel.NONE)
    private final Map<String, String> propertySynonyms;
    
    private final Map<String, Object> standardProperties;
    
    private final Map<String, Object> localProperties;
    
    private final Properties customPoolProps = new Properties();
    
    public DataSourceProperties(final String dataSourceClassName, final Map<String, Object> props) {
        this.dataSourceClassName = dataSourceClassName;
        propertySynonyms = DataSourcePoolMetaDataFactory.newInstance(dataSourceClassName).getPropertySynonyms();
        standardProperties = buildStandardProperties(props);
        localProperties = buildLocalProperties(props);
    }
    
    private Map<String, Object> buildLocalProperties(final Map<String, Object> props) {
        Map<String, Object> result = new LinkedHashMap<>(props);
        for (Entry<String, String> entry : propertySynonyms.entrySet()) {
            String standardPropertyName = entry.getKey();
            String synonymsPropertyName = entry.getValue();
            if (props.containsKey(standardPropertyName)) {
                result.put(synonymsPropertyName, props.get(standardPropertyName));
                result.remove(standardPropertyName);
            }
        }
        return result;
    }
    
    private Map<String, Object> buildStandardProperties(final Map<String, Object> props) {
        Map<String, Object> result = new LinkedHashMap<>(props);
        for (Entry<String, String> entry : propertySynonyms.entrySet()) {
            String standardPropertyName = entry.getKey();
            String synonymsPropertyName = entry.getValue();
            if (props.containsKey(synonymsPropertyName)) {
                result.put(standardPropertyName, props.get(synonymsPropertyName));
                result.remove(synonymsPropertyName);
            }
        }
        return result;
    }
    
    /**
     * Get all properties.
     * 
     * @return all properties
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Map<String, Object> getAllProperties() {
        Map<String, Object> result = new HashMap<>(localProperties);
        result.putAll((Map) customPoolProps);
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        return this == obj || null != obj && getClass() == obj.getClass() && equalsByProperties((DataSourceProperties) obj);
    }
    
    private boolean equalsByProperties(final DataSourceProperties dataSourceProperties) {
        if (!dataSourceClassName.equals(dataSourceProperties.dataSourceClassName)) {
            return false;
        }
        for (Entry<String, Object> entry : localProperties.entrySet()) {
            if (!dataSourceProperties.localProperties.containsKey(entry.getKey())) {
                continue;
            }
            if (!String.valueOf(entry.getValue()).equals(String.valueOf(dataSourceProperties.localProperties.get(entry.getKey())))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry<String, Object> entry : localProperties.entrySet()) {
            stringBuilder.append(entry.getKey()).append(entry.getValue());
        }
        return Objects.hashCode(dataSourceClassName, stringBuilder.toString());
    }
}
