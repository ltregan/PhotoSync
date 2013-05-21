package photosync;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			String sources[] = null;
			if( args.length == 1){
				sources = new String[] { "E:", "F:", "G:", "H:", "I:" };
			}else if( args.length == 2){
				sources = new String[] { args[1] };
			}else
				throw new UserException("Use target directory as command line");
			File dest = new File(args[0]);
			List<String> names = ListFiles( dest ) ;
			for( String source: sources){
				File drive = new File(source);
				if( ! drive.isDirectory() )
					continue;
				Copy( new File(source), dest, names);
			}
			System.in.read();
		} catch(UserException x){
			System.out.println( x.getMessage() );
		}catch(Exception x){
			x.printStackTrace();
		}
	}
	
	static List<String> ListFiles(File source) throws Exception{
		List<String> result = new  ArrayList<String>();
		if( ! source.isDirectory() )
			throw new IllegalArgumentException("\""+source.getCanonicalPath()+"\" not a directory");
		File files[] = source.listFiles();
		for( File f: files){
			if( f.isDirectory() )
				continue;
			String toUp = f.getName().toUpperCase();
			if( ! toUp.endsWith(".JPG"))
				continue;
			String name = f.getName();
			name = name.substring(0, name.length()-4);
			result.add( name );
		}
		return result;
	}
	
	static void Copy( File source, File dest, List<String> matches) throws Exception {
		if( ! source.isDirectory() )
			throw new RuntimeException("\""+source.getCanonicalPath()+"\" not a directory.");
		if( ! dest.isDirectory() )
			throw new RuntimeException("\""+source.getCanonicalPath()+"\" not a directory.");
		File[] files = source.listFiles();
		for( File file: files){
			if( file.isDirectory() ){
				Copy( file, dest, matches);
			} else {
				String name = FileUtils.ExtractName(file.getName());
				if( matches.indexOf(name) < 0)
					continue;
				File destFile = new File( dest, file.getName() );
				System.out.println(file+"->"+destFile);
				FileUtils.CopyFile(file, destFile );
			}
		}
	}
	

}
