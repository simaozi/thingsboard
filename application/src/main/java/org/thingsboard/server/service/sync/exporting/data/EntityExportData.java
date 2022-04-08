/**
 * Copyright © 2016-2022 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.service.sync.exporting.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.thingsboard.server.common.data.Customer;
import org.thingsboard.server.common.data.Dashboard;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceProfile;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.ExportableEntity;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.relation.EntityRelation;
import org.thingsboard.server.common.data.rule.RuleChain;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "entityType", defaultImpl = EntityExportData.class, visible = true)
@JsonSubTypes({
        @Type(name = "DEVICE", value = DeviceExportData.class),
        @Type(name = "RULE_CHAIN", value = RuleChainExportData.class)
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EntityExportData<E extends ExportableEntity<? extends EntityId>> {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "entityType", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
    @JsonSubTypes({
            @Type(name = "DEVICE", value = Device.class),
            @Type(name = "RULE_CHAIN", value = RuleChain.class),
            @Type(name = "DEVICE_PROFILE", value = DeviceProfile.class),
            @Type(name = "ASSET", value = Asset.class),
            @Type(name = "DASHBOARD", value = Dashboard.class),
            @Type(name = "CUSTOMER", value = Customer.class)
    })
    private E entity;
    private EntityType entityType;

    private List<EntityRelation> inboundRelations;
    private List<EntityRelation> outboundRelations;

}
