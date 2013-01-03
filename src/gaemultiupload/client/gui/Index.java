package gaemultiupload.client.gui;

import gaemultiupload.client.plupload.File;
import gaemultiupload.client.plupload.Plupload;
import gaemultiupload.client.plupload.PluploadBuilder;
import gaemultiupload.client.plupload.PluploadListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class Index extends Composite implements PluploadListener {
	private static IndexUiBinder uiBinder = GWT.create(IndexUiBinder.class);
	interface IndexUiBinder extends UiBinder<Widget, Index> {}

	@UiField Button btnBrowse;
	@UiField CellTable<File> tblFiles; 
	
	private Plupload plupload;
	private ListDataProvider<File> tblFilesDataProvider;
	
	public Index() {
		initWidget(uiBinder.createAndBindUi(this));
		
		tblFilesDataProvider = new ListDataProvider<File>(new ArrayList<File>());
		tblFilesDataProvider.addDataDisplay(tblFiles);
		initColumns();
		
		btnBrowse.getElement().setId("btn-browse");
		PluploadBuilder builder = new PluploadBuilder();
		builder.runtime("html5");
		builder.useQueryString(false);
		builder.multipart(true);
		builder.browseButton(btnBrowse.getElement().getId());
		builder.listener(this);
		plupload = builder.create();
		plupload.init();
	}
	
	private void initColumns() {
		tblFiles.addColumn(new Column<File, String>(new TextCell()) {
			@Override
			public String getValue(File object) {
				return object.getName();
			}
		}, "Name");
		
		tblFiles.addColumn(new Column<File, String>(new TextCell()) {
			@Override
			public String getValue(File object) {
				return object.getSize() + " B";
			}
		}, "Size");
		tblFiles.setColumnWidth(1, "100px");
		
		tblFiles.addColumn(new Column<File, String>(new TextCell()) {
			@Override
			public String getValue(File object) {
				return object.getPercent() + " %";
			}
		}, "Progress");
		tblFiles.setColumnWidth(2, "100px");
	}
	
	@UiHandler("btnStart")
	void btnStart_Click(ClickEvent event) {
		plupload.start();
	}

	@Override
	public void onInit(Plupload p, String runtime) {
		System.out.println("Initialized Plupload with runtime " + runtime + ".");
	}

	@Override
	public void postInit(Plupload p) {
		
	}

	@Override
	public void onFilesAdded(Plupload p, List<File> files) {
		tblFilesDataProvider.getList().addAll(files);
	}

	@Override
	public void onFilesRemoved(Plupload p, List<File> files) {
		
	}

	@Override
	public void onQueueChanged(Plupload p) {
		
	}

	@Override
	public void onRefresh(Plupload p) {
		
	}

	@Override
	public void onStateChanged(Plupload p) {
		
	}

	@Override
	public void onBeforeUpload(Plupload pl, File cast) {
		pl.setExtraValue(System.currentTimeMillis() + " is unique.");
		pl.fetchNewUploadUrl();
	}

	@Override
	public void onFileUpload(Plupload p, File file) {
		
	}

	@Override
	public void onFileUploadProgress(Plupload p, File file) {
		tblFilesDataProvider.refresh();
	}

	@Override
	public void onFileUploaded(Plupload p, File file, JavaScriptObject response) {
		
	}

	@Override
	public void onChunkUploaded(Plupload p, File file, JavaScriptObject response) {
		
	}

	@Override
	public void onError(Plupload p, JavaScriptObject error) {
		
	}
}
