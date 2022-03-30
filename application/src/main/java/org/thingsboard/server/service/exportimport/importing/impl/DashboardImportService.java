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
package org.thingsboard.server.service.exportimport.importing.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thingsboard.server.common.data.Dashboard;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.id.DashboardId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.dao.dashboard.DashboardService;
import org.thingsboard.server.queue.util.TbCoreComponent;
import org.thingsboard.server.service.exportimport.exporting.data.DashboardExportData;

@Service
@TbCoreComponent
@RequiredArgsConstructor
public class DashboardImportService extends AbstractEntityImportService<DashboardId, Dashboard, DashboardExportData> {

    private final DashboardService dashboardService;

    @Override
    protected void setLinkedEntitiesIds(TenantId tenantId, Dashboard dashboard, IdProvider<Dashboard> idProvider) {
//        if (existingDashboard == null) {
//            dashboard.setAssignedCustomers(null); // FIXME [viacheslav]: need to assign dashboard to customers ?
//        } else {
//            dashboard.setAssignedCustomers(existingDashboard.getAssignedCustomers());
//        }
    }

    @Override
    protected Dashboard saveEntity(TenantId tenantId, Dashboard dashboard, Dashboard existingDashboard, DashboardExportData exportData) {
        return dashboardService.saveDashboard(dashboard);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.DASHBOARD;
    }

}
