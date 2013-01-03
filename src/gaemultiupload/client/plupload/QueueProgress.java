package gaemultiupload.client.plupload;

import com.google.gwt.core.client.JavaScriptObject;

public final class QueueProgress extends JavaScriptObject {
	protected QueueProgress() {
	}

	public native int getFailed() /*-{
		return this.failed;
	}-*/;

	public native int getLoaded() /*-{
		return this.loaded;
	}-*/;

	public native int getPercent() /*-{
		return this.percent;
	}-*/;

	public native int getQueued() /*-{
		return this.queued;
	}-*/;

	public native int getSize() /*-{
		return this.size;
	}-*/;

	public native int getUploaded() /*-{
		return this.uploaded;
	}-*/;

}