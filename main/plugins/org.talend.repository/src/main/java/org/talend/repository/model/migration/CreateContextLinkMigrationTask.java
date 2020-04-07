// ============================================================================
//
// Copyright (C) 2006-2019 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.model.migration;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.runtime.model.emf.EmfHelper;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.repository.utils.ContextLinkService;

public class CreateContextLinkMigrationTask extends UnifyPasswordEncryption4ItemMigrationTask {

    @Override
    public List<ERepositoryObjectType> getTypes() {
        List<ERepositoryObjectType> toReturn = new ArrayList<ERepositoryObjectType>();
        toReturn.addAll(ERepositoryObjectType.getAllTypesOfProcess());
        toReturn.addAll(ERepositoryObjectType.getAllTypesOfProcess2());
        toReturn.addAll(ERepositoryObjectType.getAllTypesOfTestContainer());
        toReturn.addAll(getAllMetaDataType());
        return toReturn;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.core.model.migration.AbstractItemMigrationTask#execute(org .talend.core.model.properties.Item)
     */
    @Override
    public ExecutionResult execute(Item item) {
        boolean modified = false;
        try {
            if (item instanceof ProcessItem) {
                ProcessItem processItem = (ProcessItem) item;
                modified = updateProcessItem(item, processItem.getProcess());
            } else if (item instanceof JobletProcessItem) {
                JobletProcessItem jobletItem = (JobletProcessItem) item;
                modified = updateProcessItem(item, jobletItem.getJobletProcess());
            } else if (item instanceof ConnectionItem) {
                modified = updateConnectionItem((ConnectionItem) item);
            }
        } catch (Exception ex) {
            ExceptionHandler.process(ex);
            return ExecutionResult.FAILURE;
        }

        if (modified) {
            try {
                factory.save(item, true);
                return ExecutionResult.SUCCESS_NO_ALERT;
            } catch (Exception ex) {
                ExceptionHandler.process(ex);
                return ExecutionResult.FAILURE;
            }
        }
        return ExecutionResult.NOTHING_TO_DO;
    }

    private boolean updateProcessItem(Item item, ProcessType processType) throws Exception {
        EmfHelper.visitChilds(processType);
        return ContextLinkService.getInstance().saveContextLink(processType, item.getProperty().getId());
    }

    private boolean updateConnectionItem(ConnectionItem connectionItem) throws Exception {
        if (connectionItem != null && connectionItem.getConnection() != null && connectionItem.getConnection().isContextMode()) {
            return ContextLinkService.getInstance().saveContextLink(connectionItem.getConnection(),
                    connectionItem.getProperty().getId());
        }
        return false;
    }

    private List<ERepositoryObjectType> getAllMetaDataType() {
        List<ERepositoryObjectType> list = new ArrayList<ERepositoryObjectType>();
        ERepositoryObjectType[] allTypes = (ERepositoryObjectType[]) ERepositoryObjectType.values();
        for (ERepositoryObjectType object : allTypes) {
            if (object.isChildTypeOf(ERepositoryObjectType.METADATA)) {
                list.add(object);
            }
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.core.model.migration.IProjectMigrationTask#getOrder()
     */
    @Override
    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2020, 04, 02, 12, 0, 0);
        return gc.getTime();
    }
}
