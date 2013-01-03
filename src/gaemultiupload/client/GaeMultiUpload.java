package gaemultiupload.client;

import gaemultiupload.client.gui.Index;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class GaeMultiUpload implements EntryPoint {
	public void onModuleLoad() {
		RootLayoutPanel.get().add(new Index());
	}
}
