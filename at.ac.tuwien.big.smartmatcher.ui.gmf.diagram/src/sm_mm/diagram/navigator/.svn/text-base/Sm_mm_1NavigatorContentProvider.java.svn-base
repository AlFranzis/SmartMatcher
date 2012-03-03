package sm_mm.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * @generated
 */
public class Sm_mm_1NavigatorContentProvider implements ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	private Viewer myViewer;

	/**
	 * @generated
	 */
	private AdapterFactoryEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	private WorkspaceSynchronizer myWorkspaceSynchronizer;

	/**
	 * @generated
	 */
	private Runnable myViewerRefreshRunnable;

	/**
	 * @generated
	 */
	public Sm_mm_1NavigatorContentProvider() {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
				.createEditingDomain();
		myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
			public Object get(Object key) {
				if (!containsKey(key)) {
					put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		myViewerRefreshRunnable = new Runnable() {
			public void run() {
				if (myViewer != null) {
					myViewer.refresh();
				}
			}
		};
		myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain,
				new WorkspaceSynchronizer.Delegate() {
					public void dispose() {
					}

					public boolean handleResourceChanged(final Resource resource) {
						for (Iterator it = myEditingDomain.getResourceSet()
								.getResources().iterator(); it.hasNext();) {
							Resource nextResource = (Resource) it.next();
							nextResource.unload();
						}
						if (myViewer != null) {
							myViewer.getControl().getDisplay().asyncExec(
									myViewerRefreshRunnable);
						}
						return true;
					}

					public boolean handleResourceDeleted(Resource resource) {
						for (Iterator it = myEditingDomain.getResourceSet()
								.getResources().iterator(); it.hasNext();) {
							Resource nextResource = (Resource) it.next();
							nextResource.unload();
						}
						if (myViewer != null) {
							myViewer.getControl().getDisplay().asyncExec(
									myViewerRefreshRunnable);
						}
						return true;
					}

					public boolean handleResourceMoved(Resource resource,
							final URI newURI) {
						for (Iterator it = myEditingDomain.getResourceSet()
								.getResources().iterator(); it.hasNext();) {
							Resource nextResource = (Resource) it.next();
							nextResource.unload();
						}
						if (myViewer != null) {
							myViewer.getControl().getDisplay().asyncExec(
									myViewerRefreshRunnable);
						}
						return true;
					}
				});
	}

	/**
	 * @generated
	 */
	public void dispose() {
		myWorkspaceSynchronizer.dispose();
		myWorkspaceSynchronizer = null;
		myViewerRefreshRunnable = null;
		for (Iterator it = myEditingDomain.getResourceSet().getResources()
				.iterator(); it.hasNext();) {
			Resource resource = (Resource) it.next();
			resource.unload();
		}
		((TransactionalEditingDomain) myEditingDomain).dispose();
		myEditingDomain = null;
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myViewer = viewer;
	}

	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath()
					.toString(), true);
			Resource resource = myEditingDomain.getResourceSet().getResource(
					fileURI, true);
			Collection result = new ArrayList();
			result.addAll(createNavigatorItems(selectViewsByType(resource
					.getContents(),
					sm_mm.diagram.edit.parts.MappingModelEditPart.MODEL_ID),
					file, false));
			return result.toArray();
		}

		if (parentElement instanceof sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup) {
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup group = (sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof sm_mm.diagram.navigator.Sm_mm_1NavigatorItem) {
			sm_mm.diagram.navigator.Sm_mm_1NavigatorItem navigatorItem = (sm_mm.diagram.navigator.Sm_mm_1NavigatorItem) parentElement;
			if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
				return EMPTY_ARRAY;
			}
			return getViewChildren(navigatorItem.getView(), parentElement);
		}

		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Object[] getViewChildren(View view, Object parentElement) {
		switch (sm_mm.diagram.part.Sm_mm_1VisualIDRegistry.getVisualID(view)) {

		case sm_mm.diagram.edit.parts.MappingModelEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup links = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_MappingModel_1000_links,
					"icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getChildrenByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			if (!links.isEmpty()) {
				result.add(links);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup incominglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_Class_2001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup outgoinglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_Class_2001_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getChildrenByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassAttributesEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup outgoinglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2A_2002_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup incominglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2A_2002_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup outgoinglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_C2C_2003_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup incominglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_C2C_2003_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup incominglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_R2R_2004_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup outgoinglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_R2R_2004_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup incominglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2C_2005_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup outgoinglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2C_2005_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup incominglinks = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_Attribute_3001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.C2CLhsEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_C2CLhs_4001_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_C2CLhs_4001_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.C2CRhsEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_C2CRhs_4002_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_C2CRhs_4002_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.A2ALhsEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2ALhs_4003_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2ALhs_4003_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.A2ARhsEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2ARhs_4004_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2ARhs_4004_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.ContextOperatorContextMappingsEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_ContextOperatorContextMappings_4005_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_ContextOperatorContextMappings_4005_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.C2CEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2AEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.ReferenceEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_Reference_4006_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_Reference_4006_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.R2RLhsEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_R2RLhs_4007_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.R2RRhsEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_R2RRhs_4008_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.R2REditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.ClassSupertypesEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_ClassSupertypes_4009_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_ClassSupertypes_4009_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.A2CLhsAttributeEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2CLhsAttribute_4010_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2CLhsAttribute_4010_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.A2CRhsAttributeEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2CRhsAttribute_4011_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2CRhsAttribute_4011_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.AttributeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.A2CRhsClassEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup target = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2CRhsClass_4012_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2CRhsClass_4012_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.ClassEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case sm_mm.diagram.edit.parts.A2CRhsReferenceEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup source = new sm_mm.diagram.navigator.Sm_mm_1NavigatorGroup(
					sm_mm.diagram.part.Messages.NavigatorGroupName_A2CRhsReference_4013_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
							.getType(sm_mm.diagram.edit.parts.A2CEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}
		}
		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Collection getLinksSourceByType(Collection edges, String type) {
		Collection result = new ArrayList();
		for (Iterator it = edges.iterator(); it.hasNext();) {
			Edge nextEdge = (Edge) it.next();
			View nextEdgeSource = nextEdge.getSource();
			if (type.equals(nextEdgeSource.getType())
					&& isOwnView(nextEdgeSource)) {
				result.add(nextEdgeSource);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getLinksTargetByType(Collection edges, String type) {
		Collection result = new ArrayList();
		for (Iterator it = edges.iterator(); it.hasNext();) {
			Edge nextEdge = (Edge) it.next();
			View nextEdgeTarget = nextEdge.getTarget();
			if (type.equals(nextEdgeTarget.getType())
					&& isOwnView(nextEdgeTarget)) {
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getOutgoingLinksByType(Collection nodes, String type) {
		Collection result = new ArrayList();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			View nextNode = (View) it.next();
			result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getIncomingLinksByType(Collection nodes, String type) {
		Collection result = new ArrayList();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			View nextNode = (View) it.next();
			result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getChildrenByType(Collection nodes, String type) {
		Collection result = new ArrayList();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			View nextNode = (View) it.next();
			result.addAll(selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getDiagramLinksByType(Collection diagrams, String type) {
		Collection result = new ArrayList();
		for (Iterator it = diagrams.iterator(); it.hasNext();) {
			Diagram nextDiagram = (Diagram) it.next();
			result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection selectViewsByType(Collection views, String type) {
		Collection result = new ArrayList();
		for (Iterator it = views.iterator(); it.hasNext();) {
			View nextView = (View) it.next();
			if (type.equals(nextView.getType()) && isOwnView(nextView)) {
				result.add(nextView);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return sm_mm.diagram.edit.parts.MappingModelEditPart.MODEL_ID
				.equals(sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
						.getModelID(view));
	}

	/**
	 * @generated
	 */
	private Collection createNavigatorItems(Collection views, Object parent,
			boolean isLeafs) {
		Collection result = new ArrayList();
		for (Iterator it = views.iterator(); it.hasNext();) {
			result.add(new sm_mm.diagram.navigator.Sm_mm_1NavigatorItem(
					(View) it.next(), parent, isLeafs));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		if (element instanceof sm_mm.diagram.navigator.Sm_mm_1AbstractNavigatorItem) {
			sm_mm.diagram.navigator.Sm_mm_1AbstractNavigatorItem abstractNavigatorItem = (sm_mm.diagram.navigator.Sm_mm_1AbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		return element instanceof IFile || getChildren(element).length > 0;
	}

}
