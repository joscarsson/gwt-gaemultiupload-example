package gaemultiupload.server;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
	public class BlobstoreUploadFinishedServlet extends HttpServlet {
		private static BlobstoreService blobstore = BlobstoreServiceFactory.getBlobstoreService();
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        Map<String, List<BlobKey>> blobs = blobstore.getUploads(req);
	        List<BlobKey> blobKeyList = blobs.get("file");
	        
	        if (blobKeyList.size() == 0)
	        	return;
	        
	        BlobKey blobKey = blobKeyList.get(0);
	        
	        System.out.println("File with blobkey " + blobKey.getKeyString() + " was saved in blobstore. Extra value passed: " + req.getParameter("extravalue"));
		}
	}

