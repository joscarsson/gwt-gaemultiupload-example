package gaemultiupload.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Index extends Composite {
	private static IndexUiBinder uiBinder = GWT.create(IndexUiBinder.class);
	interface IndexUiBinder extends UiBinder<Widget, Index> {}

	public Index() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
