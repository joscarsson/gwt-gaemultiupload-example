package gaemultiupload.client.plupload;

import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class PluploadBuilder {
	private JavaScriptObject settings = JavaScriptObject.createObject();
	private PluploadListener listener = null;

	public PluploadBuilder() {
		init();
	}

	private native void init() /*-{
		this.@gaemultiupload.client.plupload.PluploadBuilder::settings['multipart'] = false;
		this.@gaemultiupload.client.plupload.PluploadBuilder::settings['multipart_params'] = {};
		this.@gaemultiupload.client.plupload.PluploadBuilder::settings['headers'] = {};
	}-*/;

	public PluploadBuilder runtime(String runtime) {
		addRuntime(runtime);
		return this;
	}

	public PluploadBuilder runtimes(String runtimes) {
		set("runtimes", runtimes);
		return this;
	}

	public PluploadBuilder flashUrl(String url) {
		set("flash_swf_url", url);
		return this;
	}

	public PluploadBuilder silverlightUrl(String url) {
		set("silverlight_xap_url", url);
		return this;
	}

	public PluploadBuilder uploadUrl(String uploadUrl) {
		set("url", uploadUrl);
		return this;
	}

	public PluploadBuilder filter(String title, String extensions) {
		addFilters(title, extensions);
		return this;
	}

	public PluploadBuilder browseButton(String browseButtonId) {
		set("browse_button", browseButtonId);
		return this;
	}

	public PluploadBuilder dragAndDropTarget(String targetId) {
		set("drop_element", targetId);
		return this;
	}

	public PluploadBuilder container(String elementId) {
		set("container", elementId);
		return this;
	}

	public PluploadBuilder multipart(boolean multipart) {
		set("multipart", multipart);
		return this;
	}

	public PluploadBuilder uniqueNames(boolean uniqueNames) {
		set("unique_names", uniqueNames);
		return this;
	}

	public PluploadBuilder multipartParam(String p, JavaScriptObject value) {
		addMultipartParam(p, value);
		return this;
	}

	public PluploadBuilder requiredFeatures(String features) {
		set("required_features", features);
		return this;
	}
	

	public PluploadBuilder useQueryString(boolean b) {
		set("use_query_string", b);
		return this;
	}

	public PluploadBuilder multipartParams(Map<String, JavaScriptObject> params) {
		for (Entry<String, JavaScriptObject> e : params.entrySet())
			addMultipartParam(e.getKey(), e.getValue());
		return this;
	}

	public PluploadBuilder maxFileSize(String size) {
		set("max_file_size", size);
		return this;
	}

	public PluploadBuilder chunk(String size) {
		set("chunk_size", size);
		return this;
	}

	public PluploadBuilder listener(PluploadListener listener) {
		this.listener = listener;
		return this;
	}

	public PluploadBuilder header(Map<String, String> params) {
		for (Entry<String, String> e : params.entrySet())
			addHeader(e.getKey(), e.getValue());
		return this;
	}

	public PluploadBuilder header(String name, String value) {
		addHeader(name, value);
		return this;
	}

	public Plupload create() {
		Plupload uploader = Plupload.create(settings);
		if (listener != null)
			bindListener(uploader, listener);
		return uploader;
	}

	private void bindListener(Plupload uploader, final PluploadListener listener) {
		uploader.bind("Init", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onInit(pl, getString(p, "runtime"));
			}
		}));

		uploader.bind("PostInit", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.postInit(pl);
			}
		}));

		uploader.bind("FilesAdded", createFunc(new Callback() {
			@SuppressWarnings("rawtypes")
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onFilesAdded(pl,
						Plupload.asList((JsArray) p.cast(), File.class));
			}
		}));

		uploader.bind("FilesRemoved", createFunc(new Callback() {
			@SuppressWarnings("rawtypes")
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onFilesRemoved(pl,
						Plupload.asList((JsArray) p.cast(), File.class));
			}
		}));

		uploader.bind("QueueChanged", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onQueueChanged(pl);
			}
		}));

		uploader.bind("Refresh", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onRefresh(pl);
			}
		}));

		uploader.bind("StateChanged", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onStateChanged(pl);
			}
		}));

		uploader.bind("BeforeUpload", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onBeforeUpload(pl, (File) p.cast());
			}
		}));

		uploader.bind("UploadFile", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onFileUpload(pl, (File) p.cast());
			}
		}));

		uploader.bind("UploadProgress", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onFileUploadProgress(pl, (File) p.cast());
			}
		}));

		uploader.bind("FileUploaded", createFunc(new Callback2() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p,
					JavaScriptObject r) {
				listener.onFileUploaded(pl, (File) p.cast(), r);
			}
		}));

		uploader.bind("ChunkUploaded", createFunc(new Callback2() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p,
					JavaScriptObject r) {
				listener.onChunkUploaded(pl, (File) p.cast(), r);
			}
		}));

		uploader.bind("Error", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject e) {
				listener.onError(pl, e);
			}
		}));
	}

	private native JavaScriptObject createFunc(Callback callback) /*-{
		return function(uploader, p) {
			@gaemultiupload.client.plupload.PluploadBuilder::fireCallback(Lgaemultiupload/client/plupload/PluploadBuilder$Callback;Lgaemultiupload/client/plupload/Plupload;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, uploader, p);
		};
	}-*/;

	private native JavaScriptObject createFunc(Callback2 callback) /*-{
		return function(uploader, p, r) {
			@gaemultiupload.client.plupload.PluploadBuilder::fireCallback(Lgaemultiupload/client/plupload/PluploadBuilder$Callback2;Lgaemultiupload/client/plupload/Plupload;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, uploader, p, r);
		};
	}-*/;

	private native void set(String name, String value) /*-{
		this.@gaemultiupload.client.plupload.PluploadBuilder::settings[name] = value;
	}-*/;

	private native void set(String name, boolean value) /*-{
		this.@gaemultiupload.client.plupload.PluploadBuilder::settings[name] = value;
	}-*/;

	private native void addRuntime(String runtime) /*-{
		if (!this.@gaemultiupload.client.plupload.PluploadBuilder::settings['runtimes'])
			this.@gaemultiupload.client.plupload.PluploadBuilder::settings['runtimes'] = '';

		if (this.@gaemultiupload.client.plupload.PluploadBuilder::settings['runtimes'].length > 0)
			this.@gaemultiupload.client.plupload.PluploadBuilder::settings['runtimes'] = this.@gaemultiupload.client.plupload.PluploadBuilder::settings['runtimes'] + ",";

		this.@gaemultiupload.client.plupload.PluploadBuilder::settings['runtimes'] = this.@gaemultiupload.client.plupload.PluploadBuilder::settings['runtimes'] + runtime;
	}-*/;

	private native void addFilters(String title, String extensions) /*-{
		if (!this.@gaemultiupload.client.plupload.PluploadBuilder::settings['filters'])
			this.@gaemultiupload.client.plupload.PluploadBuilder::settings['filters'] = [];
		this.@gaemultiupload.client.plupload.PluploadBuilder::settings['filters'].push({title:title, extensions: extensions});
	}-*/;

	private native void addMultipartParam(String p, JavaScriptObject v) /*-{
		this.@gaemultiupload.client.plupload.PluploadBuilder::settings['multipart_params'][p] = v;
	}-*/;

	private native void addHeader(String p, String v) /*-{
		this.@gaemultiupload.client.plupload.PluploadBuilder::settings['headers'][p] = v;
	}-*/;

	private static void fireCallback(Callback cb, Plupload pl,
			JavaScriptObject p) {
		cb.onCallback(pl, p);
	}

	private static void fireCallback(Callback2 cb, Plupload pl,
			JavaScriptObject p, JavaScriptObject r) {
		cb.onCallback(pl, p, r);
	}

	public interface Callback {
		void onCallback(Plupload pl, JavaScriptObject p);
	}

	public interface Callback2 {
		void onCallback(Plupload pl, JavaScriptObject p, JavaScriptObject r);
	}

	protected native String getString(JavaScriptObject p, String name) /*-{
		return p[name];
	}-*/;

	public JavaScriptObject getSettings() {
		return settings;
	}
}