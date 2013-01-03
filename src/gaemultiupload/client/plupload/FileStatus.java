package gaemultiupload.client.plupload;

public enum FileStatus {
	QUEUED(1), UPLOADING(2), FAILED(4), DONE(5);

	private int n;

	private FileStatus(int n) {
		this.n = n;
	}

	public static FileStatus fromIntValue(int n) {
		for (FileStatus s : FileStatus.values())
			if (s.n == n)
				return s;
		return null;
	}
}