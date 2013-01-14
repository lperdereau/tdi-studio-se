// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.view.di.model;

import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class StandardProcessRepositoryType {

    public static final String PROCESS_STANDARD = "PROCESS_STANDARD"; //$NON-NLS-1$

    public static final ERepositoryObjectType standardProcessType = ERepositoryObjectType.valueOf(ERepositoryObjectType.class,
            PROCESS_STANDARD);
}
