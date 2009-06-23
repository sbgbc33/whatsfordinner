

import java.io.File;

public class CompareDirs {
	
	private String src = "c:/oracle";
	private String dest = "c:/ftp";
	
	private void printError(String msg, String src, String dest) {
		System.err.println(msg + src + " : " +dest);
	}
	
	private boolean compareFiles(File src, File dest) {
		
		boolean returnValue = true;
		
		if ( ! doBothExist(src, dest) ) {
			return false;
		}
		
		if ( ! src.getName().equals(dest.getName()) ) {
			printError("Filesname NOT EQUAL ", src.getName(), dest.getName());
			returnValue = false;
		}
		
		if ( src.length() != dest.length() ) {
			printError("File sizes NOT EQUAL ", src.getName(), dest.getName());
			returnValue = false;
		}
		
		if ( src.lastModified() != dest.lastModified() ) {
			printError("File sizes NOT EQUAL ", src.lastModified(), dest.lastModified());
			returnValue = false;
		}
		
		return returnValue;
	}
	
	private boolean doBothExist(File src, File dest) {
		if ( src.exists() ) {
			if ( dest.exists() ) {
				// both files exist;
				return true;
			} else {
				printError("Dest File does not exist", src.getName(), dest.getName());
				return false;
			}
		} else {
			if ( dest.exists() ) {
				printError("Src File does not exist", src.getName(), dest.getName());
				return false;
			} else {
				// both files exist;
				printError("Both Files do not exist", src.getName(), dest.getName());
				return false;
			}
		}
	}
	
	private boolean areBothDirs(File src, File dest) {
		if ( src.isDirectory()) {
			if ( dest.isDirectory() ) {
				// both files exist;
				return true;
			} else {
				printError("Dest File is not a directory", src.getName(), dest.getName());
				return false;
			}
		} else {
			if ( dest.isDirectory() ) {
				printError("Src File is not a directory", src.getName(), dest.getName());
				return false;
			} else {
				// both files exist;
				printError("Both Files are not a directory", src.getName(), dest.getName());
				return false;
			}
		}
	}
	
	private boolean shouldContinueOnMisMatch = true;
	
	private boolean compareDirs(File src, File dest) {
		boolean returnValue = true;
		for(File fSrc : src.listFiles()) {
			File fDest = new File(dest.getAbsolutePath() + fSrc.getName());
			if ( ! doBothExist(fSrc, fDest) && !shouldContinueOnMisMatch) {
				returnValue = false;
				break;
			}
			
			if ( areBothDirs(fSrc, fDest)) {
				compareDirs(fSrc, fDest);
			} else {
				if ( !compareFiles(fSrc, fDest) && !shouldContinueOnMisMatch) {
					returnValue = false;
					break;
				}
			}
		}
		
		return returnValue;
	}
	
	private void printError(String msg, long lastModified, long lastModified2) {
		printError(msg, String.valueOf(lastModified), String.valueOf(lastModified2));
	}

	private void compare() {
		
		File s = new File(src);
		File d = new File(dest);
		
		boolean areBothDirs = false;
		boolean areBothFiles = false;
		boolean isError = false;
		
		if ( s.isDirectory() ) {
			if ( d.isDirectory() ) {
				areBothDirs = true;
			} else {
				isError = true;
			}
		} else {
			if ( d.isDirectory() ) {
				isError = true;
			} else {
				areBothFiles = true;
			}
		}
		
		if ( areBothFiles ) {
			compareFiles(s,d);
		} else if ( areBothDirs ) {
			compareDirs(s,d);
		} else {
			System.err.println("error : Both need to be files or both need to be dirs. " + s + " : " + d);
		}
		
	}
	public static void main(String args[]) {
		CompareDirs c = new CompareDirs();
		c.compare();
	}

}
