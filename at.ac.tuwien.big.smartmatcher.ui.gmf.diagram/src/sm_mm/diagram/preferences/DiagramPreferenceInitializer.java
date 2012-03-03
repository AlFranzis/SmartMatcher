package sm_mm.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @generated
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();
		sm_mm.diagram.preferences.DiagramGeneralPreferencePage
				.initDefaults(store);
		sm_mm.diagram.preferences.DiagramAppearancePreferencePage
				.initDefaults(store);
		sm_mm.diagram.preferences.DiagramConnectionsPreferencePage
				.initDefaults(store);
		sm_mm.diagram.preferences.DiagramPrintingPreferencePage
				.initDefaults(store);
		sm_mm.diagram.preferences.DiagramRulersAndGridPreferencePage
				.initDefaults(store);

	}

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return sm_mm.diagram.part.Sm_mm_1DiagramEditorPlugin.getInstance()
				.getPreferenceStore();
	}
}
