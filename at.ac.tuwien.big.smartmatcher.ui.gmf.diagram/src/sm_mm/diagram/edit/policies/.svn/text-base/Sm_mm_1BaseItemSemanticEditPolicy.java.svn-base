package sm_mm.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class Sm_mm_1BaseItemSemanticEditPolicy extends SemanticEditPolicy {

	/**
	 * Extended request data key to hold editpart visual id.
	 * @generated
	 */
	public static final String VISUAL_ID_KEY = "visual_id"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final IElementType myElementType;

	/**
	 * @generated
	 */
	protected Sm_mm_1BaseItemSemanticEditPolicy(IElementType elementType) {
		myElementType = elementType;
	}

	/**
	 * Extended request data key to hold editpart visual id.
	 * Add visual id of edited editpart to extended data of the request
	 * so command switch can decide what kind of diagram element is being edited.
	 * It is done in those cases when it's not possible to deduce diagram
	 * element kind from domain element.
	 * 
	 * @generated
	 */
	public Command getCommand(Request request) {
		if (request instanceof ReconnectRequest) {
			Object view = ((ReconnectRequest) request).getConnectionEditPart()
					.getModel();
			if (view instanceof View) {
				Integer id = new Integer(
						sm_mm.diagram.part.Sm_mm_1VisualIDRegistry
								.getVisualID((View) view));
				request.getExtendedData().put(VISUAL_ID_KEY, id);
			}
		}
		return super.getCommand(request);
	}

	/**
	 * Returns visual id from request parameters.
	 * @generated
	 */
	protected int getVisualID(IEditCommandRequest request) {
		Object id = request.getParameter(VISUAL_ID_KEY);
		return id instanceof Integer ? ((Integer) id).intValue() : -1;
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommand(IEditCommandRequest request) {
		IEditCommandRequest completedRequest = completeRequest(request);
		Command semanticCommand = getSemanticCommandSwitch(completedRequest);
		semanticCommand = getEditHelperCommand(completedRequest,
				semanticCommand);
		if (completedRequest instanceof DestroyRequest) {
			DestroyRequest destroyRequest = (DestroyRequest) completedRequest;
			return shouldProceed(destroyRequest) ? addDeleteViewCommand(
					semanticCommand, destroyRequest) : null;
		}
		return semanticCommand;
	}

	/**
	 * @generated
	 */
	protected Command addDeleteViewCommand(Command mainCommand,
			DestroyRequest completedRequest) {
		Command deleteViewCommand = getGEFWrapper(new DeleteCommand(
				getEditingDomain(), (View) getHost().getModel()));
		return mainCommand == null ? deleteViewCommand : mainCommand
				.chain(deleteViewCommand);
	}

	/**
	 * @generated
	 */
	private Command getEditHelperCommand(IEditCommandRequest request,
			Command editPolicyCommand) {
		if (editPolicyCommand != null) {
			ICommand command = editPolicyCommand instanceof ICommandProxy ? ((ICommandProxy) editPolicyCommand)
					.getICommand()
					: new CommandProxy(editPolicyCommand);
			request
					.setParameter(
							sm_mm.diagram.edit.helpers.Sm_mm_1BaseEditHelper.EDIT_POLICY_COMMAND,
							command);
		}
		IElementType requestContextElementType = getContextElementType(request);
		request
				.setParameter(
						sm_mm.diagram.edit.helpers.Sm_mm_1BaseEditHelper.CONTEXT_ELEMENT_TYPE,
						requestContextElementType);
		ICommand command = requestContextElementType.getEditCommand(request);
		request
				.setParameter(
						sm_mm.diagram.edit.helpers.Sm_mm_1BaseEditHelper.EDIT_POLICY_COMMAND,
						null);
		request
				.setParameter(
						sm_mm.diagram.edit.helpers.Sm_mm_1BaseEditHelper.CONTEXT_ELEMENT_TYPE,
						null);
		if (command != null) {
			if (!(command instanceof CompositeTransactionalCommand)) {
				command = new CompositeTransactionalCommand(getEditingDomain(),
						command.getLabel()).compose(command);
			}
			return new ICommandProxy(command);
		}
		return editPolicyCommand;
	}

	/**
	 * @generated
	 */
	private IElementType getContextElementType(IEditCommandRequest request) {
		IElementType requestContextElementType = sm_mm.diagram.providers.Sm_mm_1ElementTypes
				.getElementType(getVisualID(request));
		return requestContextElementType != null ? requestContextElementType
				: myElementType;
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommandSwitch(IEditCommandRequest req) {
		if (req instanceof CreateRelationshipRequest) {
			return getCreateRelationshipCommand((CreateRelationshipRequest) req);
		} else if (req instanceof CreateElementRequest) {
			return getCreateCommand((CreateElementRequest) req);
		} else if (req instanceof ConfigureRequest) {
			return getConfigureCommand((ConfigureRequest) req);
		} else if (req instanceof DestroyElementRequest) {
			return getDestroyElementCommand((DestroyElementRequest) req);
		} else if (req instanceof DestroyReferenceRequest) {
			return getDestroyReferenceCommand((DestroyReferenceRequest) req);
		} else if (req instanceof DuplicateElementsRequest) {
			return getDuplicateCommand((DuplicateElementsRequest) req);
		} else if (req instanceof GetEditContextRequest) {
			return getEditContextCommand((GetEditContextRequest) req);
		} else if (req instanceof MoveRequest) {
			return getMoveCommand((MoveRequest) req);
		} else if (req instanceof ReorientReferenceRelationshipRequest) {
			return getReorientReferenceRelationshipCommand((ReorientReferenceRelationshipRequest) req);
		} else if (req instanceof ReorientRelationshipRequest) {
			return getReorientRelationshipCommand((ReorientRelationshipRequest) req);
		} else if (req instanceof SetRequest) {
			return getSetCommand((SetRequest) req);
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getConfigureCommand(ConfigureRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getSetCommand(SetRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getEditContextCommand(GetEditContextRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getMoveCommand(MoveRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected final Command getGEFWrapper(ICommand cmd) {
		return new ICommandProxy(cmd);
	}

	/**
	 * Returns editing domain from the host edit part.
	 * @generated
	 */
	protected TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart) getHost()).getEditingDomain();
	}

	/**
	 * Clean all shortcuts to the host element from the same diagram
	 * @generated
	 */
	protected void addDestroyShortcutsCommand(ICompositeCommand cmd, View view) {
		assert view.getEAnnotation("Shortcut") == null; //$NON-NLS-1$
		for (Iterator it = view.getDiagram().getChildren().iterator(); it
				.hasNext();) {
			View nextView = (View) it.next();
			if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) { //$NON-NLS-1$
				continue;
			}
			cmd.add(new DeleteCommand(getEditingDomain(), nextView));
		}
	}

	/**
	 * @generated
	 */
	public static class LinkConstraints {

		/**
		 * @generated
		 */
		public static boolean canCreateC2CLhs_4001(sm_mm.C2C source,
				sm_mm.Class target) {
			if (source != null) {
				if (source.getLhs() != null) {
					return false;
				}
			}

			return canExistC2CLhs_4001(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateC2CRhs_4002(sm_mm.C2C source,
				sm_mm.Class target) {
			if (source != null) {
				if (source.getRhs() != null) {
					return false;
				}
			}

			return canExistC2CRhs_4002(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateA2ALhs_4003(sm_mm.A2A source,
				sm_mm.Attribute target) {
			if (source != null) {
				if (source.getLhs() != null) {
					return false;
				}
			}

			return canExistA2ALhs_4003(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateA2ARhs_4004(sm_mm.A2A source,
				sm_mm.Attribute target) {
			if (source != null) {
				if (source.getRhs() != null) {
					return false;
				}
			}

			return canExistA2ARhs_4004(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateContextOperatorContextMappings_4005(
				sm_mm.ContextOperator source, sm_mm.Operator target) {
			if (source != null) {
				if (source.getContextMappings().contains(target)) {
					return false;
				}
			}
			if (target != null && (target.getParents().contains(target))) {
				return false;
			}

			return canExistContextOperatorContextMappings_4005(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateReference_4006(
				sm_mm.MappingModel container, sm_mm.Class source,
				sm_mm.Class target) {
			return canExistReference_4006(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateR2RLhs_4007(sm_mm.R2R source,
				sm_mm.Reference target) {
			if (source != null) {
				if (source.getLhs() != null) {
					return false;
				}
			}

			return canExistR2RLhs_4007(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateR2RRhs_4008(sm_mm.R2R source,
				sm_mm.Reference target) {
			if (source != null) {
				if (source.getRhs() != null) {
					return false;
				}
			}

			return canExistR2RRhs_4008(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateClassSupertypes_4009(sm_mm.Class source,
				sm_mm.Class target) {
			if (source != null) {
				if (source.getSupertypes().contains(target)) {
					return false;
				}
			}

			return canExistClassSupertypes_4009(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateA2CLhsAttribute_4010(sm_mm.A2C source,
				sm_mm.Attribute target) {
			if (source != null) {
				if (source.getLhsAttribute() != null) {
					return false;
				}
			}

			return canExistA2CLhsAttribute_4010(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateA2CRhsAttribute_4011(sm_mm.A2C source,
				sm_mm.Attribute target) {
			if (source != null) {
				if (source.getRhsAttribute() != null) {
					return false;
				}
			}

			return canExistA2CRhsAttribute_4011(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateA2CRhsClass_4012(sm_mm.A2C source,
				sm_mm.Class target) {
			if (source != null) {
				if (source.getRhsClass() != null) {
					return false;
				}
			}

			return canExistA2CRhsClass_4012(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateA2CRhsReference_4013(sm_mm.A2C source,
				sm_mm.Reference target) {
			if (source != null) {
				if (source.getRhsReference() != null) {
					return false;
				}
			}

			return canExistA2CRhsReference_4013(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canExistC2CLhs_4001(sm_mm.C2C source,
				sm_mm.Class target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistC2CRhs_4002(sm_mm.C2C source,
				sm_mm.Class target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistA2ALhs_4003(sm_mm.A2A source,
				sm_mm.Attribute target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistA2ARhs_4004(sm_mm.A2A source,
				sm_mm.Attribute target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistContextOperatorContextMappings_4005(
				sm_mm.ContextOperator source, sm_mm.Operator target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistReference_4006(
				sm_mm.MappingModel container, sm_mm.Class source,
				sm_mm.Class target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistR2RLhs_4007(sm_mm.R2R source,
				sm_mm.Reference target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistR2RRhs_4008(sm_mm.R2R source,
				sm_mm.Reference target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistClassSupertypes_4009(sm_mm.Class source,
				sm_mm.Class target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistA2CLhsAttribute_4010(sm_mm.A2C source,
				sm_mm.Attribute target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistA2CRhsAttribute_4011(sm_mm.A2C source,
				sm_mm.Attribute target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistA2CRhsClass_4012(sm_mm.A2C source,
				sm_mm.Class target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistA2CRhsReference_4013(sm_mm.A2C source,
				sm_mm.Reference target) {
			return true;
		}
	}

}
