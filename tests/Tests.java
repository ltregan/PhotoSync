import java.io.File;
import java.io.FileWriter;
import java.util.Date;


public class Tests  {
	
	
	public static void main(String[] args) throws Exception {
		
		Date now = new Date();
    	File src = new File("c:/temp/tests/src"+now.getTime() );
    	src.mkdirs();
    	
    	CreateFile(src, "A.jpg");
    	CreateFile(src, "B.jpg");
    	
    	File dst = new File("c:/temp/tests/dest"+now.getTime() );
    	dst.mkdirs();
    	CreateFile(dst, "A.CR2");
    	dst = new File(dst, "sub");
    	dst.mkdirs();
    	CreateFile(dst, "B.ARW");
    	
    	String s = dst.getParent();
    	photosync.Main.main( new String[] {"c:/temp/tests/src"+now.getTime(), s} );
    }
	
	
	
	static private void CreateFile(File dir, String name) throws Exception{
    	File f = new File(dir, name);
    	FileWriter w = new FileWriter(f);
    	try{
    		w.append("X");
    	} finally {
    		w.close();
    	}
	}
	

}
