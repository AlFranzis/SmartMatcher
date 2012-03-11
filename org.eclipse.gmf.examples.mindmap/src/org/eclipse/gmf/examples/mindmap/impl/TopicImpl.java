/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.examples.mindmap.impl;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.eclipse.gmf.examples.mindmap.MindmapPackage;
import org.eclipse.gmf.examples.mindmap.Priority;
import org.eclipse.gmf.examples.mindmap.Resource;
import org.eclipse.gmf.examples.mindmap.Topic;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Topic</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.examples.mindmap.impl.TopicImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link org.eclipse.gmf.examples.mindmap.impl.TopicImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.eclipse.gmf.examples.mindmap.impl.TopicImpl#getEndDate <em>End Date</em>}</li>
 *   <li>{@link org.eclipse.gmf.examples.mindmap.impl.TopicImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.examples.mindmap.impl.TopicImpl#getPercentComplete <em>Percent Complete</em>}</li>
 *   <li>{@link org.eclipse.gmf.examples.mindmap.impl.TopicImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.eclipse.gmf.examples.mindmap.impl.TopicImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.eclipse.gmf.examples.mindmap.impl.TopicImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link org.eclipse.gmf.examples.mindmap.impl.TopicImpl#getSubtopics <em>Subtopics</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TopicImpl extends CDOObjectImpl implements Topic {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TopicImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MindmapPackage.Literals.TOPIC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<org.eclipse.gmf.examples.mindmap.Thread> getComments() {
		return (EList<org.eclipse.gmf.examples.mindmap.Thread>)eGet(MindmapPackage.Literals.TOPIC__COMMENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getDuration() {
		return (Float)eGet(MindmapPackage.Literals.TOPIC__DURATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(float newDuration) {
		eSet(MindmapPackage.Literals.TOPIC__DURATION, newDuration);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDuration() {
		eUnset(MindmapPackage.Literals.TOPIC__DURATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDuration() {
		return eIsSet(MindmapPackage.Literals.TOPIC__DURATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XMLGregorianCalendar getEndDate() {
		return (XMLGregorianCalendar)eGet(MindmapPackage.Literals.TOPIC__END_DATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndDate(XMLGregorianCalendar newEndDate) {
		eSet(MindmapPackage.Literals.TOPIC__END_DATE, newEndDate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)eGet(MindmapPackage.Literals.TOPIC__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(MindmapPackage.Literals.TOPIC__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPercentComplete() {
		return (Float)eGet(MindmapPackage.Literals.TOPIC__PERCENT_COMPLETE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPercentComplete(float newPercentComplete) {
		eSet(MindmapPackage.Literals.TOPIC__PERCENT_COMPLETE, newPercentComplete);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPercentComplete() {
		eUnset(MindmapPackage.Literals.TOPIC__PERCENT_COMPLETE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPercentComplete() {
		return eIsSet(MindmapPackage.Literals.TOPIC__PERCENT_COMPLETE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Priority getPriority() {
		return (Priority)eGet(MindmapPackage.Literals.TOPIC__PRIORITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(Priority newPriority) {
		eSet(MindmapPackage.Literals.TOPIC__PRIORITY, newPriority);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPriority() {
		eUnset(MindmapPackage.Literals.TOPIC__PRIORITY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPriority() {
		return eIsSet(MindmapPackage.Literals.TOPIC__PRIORITY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Resource> getResources() {
		return (EList<Resource>)eGet(MindmapPackage.Literals.TOPIC__RESOURCES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XMLGregorianCalendar getStartDate() {
		return (XMLGregorianCalendar)eGet(MindmapPackage.Literals.TOPIC__START_DATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartDate(XMLGregorianCalendar newStartDate) {
		eSet(MindmapPackage.Literals.TOPIC__START_DATE, newStartDate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Topic> getSubtopics() {
		return (EList<Topic>)eGet(MindmapPackage.Literals.TOPIC__SUBTOPICS, true);
	}

} //TopicImpl
