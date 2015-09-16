/**
 */
package org.talend.component.ui.model.genericMetadata.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.talend.component.ui.model.genericMetadata.GenericConnection;
import org.talend.component.ui.model.genericMetadata.GenericConnectionItem;
import org.talend.component.ui.model.genericMetadata.GenericMetadataFactory;
import org.talend.component.ui.model.genericMetadata.GenericMetadataPackage;

import org.talend.core.model.metadata.builder.connection.ConnectionPackage;

import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GenericMetadataPackageImpl extends EPackageImpl implements GenericMetadataPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass genericConnectionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass genericConnectionItemEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.talend.component.ui.model.genericMetadata.GenericMetadataPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private GenericMetadataPackageImpl() {
        super(eNS_URI, GenericMetadataFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link GenericMetadataPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static GenericMetadataPackage init() {
        if (isInited) return (GenericMetadataPackage)EPackage.Registry.INSTANCE.getEPackage(GenericMetadataPackage.eNS_URI);

        // Obtain or create and register package
        GenericMetadataPackageImpl theGenericMetadataPackage = (GenericMetadataPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GenericMetadataPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GenericMetadataPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        PropertiesPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theGenericMetadataPackage.createPackageContents();

        // Initialize created meta-data
        theGenericMetadataPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theGenericMetadataPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(GenericMetadataPackage.eNS_URI, theGenericMetadataPackage);
        return theGenericMetadataPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGenericConnection() {
        return genericConnectionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenericConnection_Parameters() {
        return (EAttribute)genericConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGenericConnectionItem() {
        return genericConnectionItemEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GenericMetadataFactory getGenericMetadataFactory() {
        return (GenericMetadataFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        genericConnectionEClass = createEClass(GENERIC_CONNECTION);
        createEAttribute(genericConnectionEClass, GENERIC_CONNECTION__PARAMETERS);

        genericConnectionItemEClass = createEClass(GENERIC_CONNECTION_ITEM);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        ConnectionPackage theConnectionPackage = (ConnectionPackage)EPackage.Registry.INSTANCE.getEPackage(ConnectionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        PropertiesPackage thePropertiesPackage = (PropertiesPackage)EPackage.Registry.INSTANCE.getEPackage(PropertiesPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        genericConnectionEClass.getESuperTypes().add(theConnectionPackage.getConnection());
        genericConnectionItemEClass.getESuperTypes().add(thePropertiesPackage.getConnectionItem());

        // Initialize classes, features, and operations; add parameters
        initEClass(genericConnectionEClass, GenericConnection.class, "GenericConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGenericConnection_Parameters(), theEcorePackage.getEString(), "parameters", null, 0, 1, GenericConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(genericConnectionItemEClass, GenericConnectionItem.class, "GenericConnectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //GenericMetadataPackageImpl
