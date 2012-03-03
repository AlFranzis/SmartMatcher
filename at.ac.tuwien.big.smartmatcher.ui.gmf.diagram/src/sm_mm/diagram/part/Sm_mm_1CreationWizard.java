package sm_mm.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @generated
 */
public class Sm_mm_1CreationWizard extends Wizard implements INewWizard {

	/**
	 * @generated
	 */
	private IWorkbench workbench;

	/**
	 * @generated
	 */
	protected IStructuredSelection selection;

	/**
	 * @generated
	 */
	protected sm_mm.diagram.part.Sm_mm_1CreationWizardPage diagramModelFilePage;

	/**
	 * @generated
	 */
	protected sm_mm.diagram.part.Sm_mm_1CreationWizardPage domainModelFilePage;

	/**
	 * @generated
	 */
	protected Resource diagram;

	/**
	 * @generated
	 */
	private boolean openNewlyCreatedDiagramEditor = true;

	/**
	 * @generated
	 */
	public IWorkbench getWorkbench() {
		return workbench;
	}

	/**
	 * @generated
	 */
	public IStructuredSelection getSelection() {
		return selection;
	}

	/**
	 * @generated
	 */
	public final Resource getDiagram() {
		return diagram;
	}

	/**
	 * @generated
	 */
	public final boolean isOpenNewlyCreatedDiagramEditor() {
		return openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void setOpenNewlyCreatedDiagramEditor(
			boolean openNewlyCreatedDiagramEditor) {
		this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle(sm_mm.diagram.part.Messages.Sm_mm_1CreationWizardTitle);
		setDefaultPageImageDescriptor(sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
				.getBundledImageDescriptor("icons/wizban/NewSm_mmWizard.gif")); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/**
	 * @generated
	 */
	public void addPages() {
		diagramModelFilePage = new sm_mm.diagram.part.Sm_mm_1CreationWizardPage(
				"DiagramModelFile", getSelection(), "sm_mm_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelFilePage
				.setTitle(sm_mm.diagram.part.Messages.Sm_mm_1CreationWizard_DiagramModelFilePageTitle);
		diagramModelFilePage
				.setDescription(sm_mm.diagram.part.Messages.Sm_mm_1CreationWizard_DiagramModelFilePageDescription);
		addPage(diagramModelFilePage);

		domainModelFilePage = new sm_mm.diagram.part.Sm_mm_1CreationWizardPage(
				"DomainModelFile", getSelection(), "sm_mm") { //$NON-NLS-1$ //$NON-NLS-2$

			public void setVisible(boolean visible) {
				if (visible) {
					String fileName = diagramModelFilePage.getFileName();
					fileName = fileName.substring(0, fileName.length()
							- ".sm_mm_diagram".length()); //$NON-NLS-1$
					setFileName(sm_mm.diagram.part.Sm_mm_1DiagramEditorUtil
							.getUniqueFileName(getContainerFullPath(),
									fileName, "sm_mm")); //$NON-NLS-1$
				}
				super.setVisible(visible);
			}
		};
		domainModelFilePage
				.setTitle(sm_mm.diagram.part.Messages.Sm_mm_1CreationWizard_DomainModelFilePageTitle);
		domainModelFilePage
				.setDescription(sm_mm.diagram.part.Messages.Sm_mm_1CreationWizard_DomainModelFilePageDescription);
		addPage(domainModelFilePage);
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

			protected void execute(IProgressMonitor monitor)
					throws CoreException, InterruptedException {
				diagram = sm_mm.diagram.part.Sm_mm_1DiagramEditorUtil
						.createDiagram(diagramModelFilePage.getURI(),
								domainModelFilePage.getURI(), monitor);
				if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
					try {
						sm_mm.diagram.part.Sm_mm_1DiagramEditorUtil
								.openDiagram(diagram);
					} catch (PartInitException e) {
						ErrorDialog
								.openError(
										getContainer().getShell(),
										sm_mm.diagram.part.Messages.Sm_mm_1CreationWizardOpenEditorError,
										null, e.getStatus());
					}
				}
			}
		};
		try {
			getContainer().run(false, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof CoreException) {
				ErrorDialog
						.openError(
								getContainer().getShell(),
								sm_mm.diagram.part.Messages.Sm_mm_1CreationWizardCreationError,
								null, ((CoreException) e.getTargetException())
										.getStatus());
			} else {
				sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin
						.getInstance()
						.logError(
								"Error creating diagram", e.getTargetException()); //$NON-NLS-1$
			}
			return false;
		}
		return diagram != null;
	}
}
