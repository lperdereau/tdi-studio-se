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
import org.talend.core.model.context.ContextUtils;
import org.talend.core.model.process.ProcessUtils;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.cwm.helper.ResourceHelper;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.impl.ContextParameterTypeImpl;

public class UpgradeProcessRepositoryContextIdMigrationTask extends UnifyPasswordEncryption4ItemMigrationTask {

    @Override
    public List<ERepositoryObjectType> getTypes() {
        List<ERepositoryObjectType> toReturn = new ArrayList<ERepositoryObjectType>();
        toReturn.addAll(ERepositoryObjectType.getAllTypesOfProcess());
        toReturn.addAll(ERepositoryObjectType.getAllTypesOfProcess2());
        toReturn.addAll(ERepositoryObjectType.getAllTypesOfTestContainer());
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
        boolean modified = false;
        String repositoryContextId = getRepositoryContextId(processType);
        if (!ProcessUtils.isComplexRepositorContextId(repositoryContextId)) {
            ContextItem contextItem = ContextUtils.getContextItemById2(repositoryContextId);
            for (Object object : processType.getContext()) {
                if (object instanceof ContextType) {
                    ContextType jobContextType = (ContextType) object;
                    ContextType contextType = ContextUtils.getContextTypeByName(contextItem.getContext(),
                            jobContextType.getName());
                    if (contextType != null) {
                        for (Object obj : jobContextType.getContextParameter()) {
                            if (obj instanceof ContextParameterTypeImpl) {
                                ContextParameterTypeImpl jobParam = (ContextParameterTypeImpl) obj;
                                ContextParameterType contextParam = ContextUtils.getContextParameterTypeByName(contextType,
                                        jobParam.getName());
                                jobParam.setRepositoryContextId(ProcessUtils.getComplexRepositorContextId(repositoryContextId,
                                        ResourceHelper.getUUID(contextParam)));
                                modified = true;
                            }
                        }
                    }
                }

            }
        }
        return modified;
    }

    private String getRepositoryContextId(ProcessType processType) {
        if (processType.getRepositoryContextId() != null) {
            return processType.getRepositoryContextId();
        }
        for (Object obj : processType.getContext()) {
            if (obj instanceof ContextType) {
                ContextType contextType = (ContextType) obj;
                for (Object o : contextType.getContextParameter()) {
                    if (o instanceof ContextParameterType) {
                        ContextParameterType param = (ContextParameterType) o;
                        if (param.getRepositoryContextId() != null) {
                            return param.getRepositoryContextId();
                        }
                    }
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.core.model.migration.IProjectMigrationTask#getOrder()
     */
    @Override
    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2020, 03, 22, 12, 0, 0);
        return gc.getTime();
    }
}
