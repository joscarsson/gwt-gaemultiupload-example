package gaemultiupload.client.plupload;

import com.google.gwt.core.client.JavaScriptObject;

public final class File extends JavaScriptObject {
	protected File() {
	}

	public native String getId() /*-{
		return this.id;
	}-*/;

	public native int getLoaded() /*-{
		return this.loaded;
	}-*/;

	public native String getName() /*-{
		return this.name;
	}-*/;

	public native int getPercent() /*-{
		return this.percent;
	}-*/;

	public native int getSize() /*-{
		return this.size;
	}-*/;

	public final FileStatus getStatus() {
		return FileStatus.fromIntValue(getStatusValue());
	}

	private native int getStatusValue() /*-{
		return this.status;
	}-*/;

	public static File create(String id, String name, int size, int loaded) {
		File f = JavaScriptObject.createObject().cast();
		f.set(id, name, size, loaded);
		return f;
	}

	private native void set(String id, String name, int size, int loaded) /*-{
		this.id = id;
		this.name = name;
		this.size = size;
		this.loaded = loaded;
		this.percent = (loaded / size) * 100;
	}-*/;
}