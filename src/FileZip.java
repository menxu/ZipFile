

import java.io.*;   
import java.util.*;   
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
      
    /**  
	 * added by czl  
	 * 压缩文件，支持中文路径及中文文件名  
	 * compressFileName(指定压缩后的文件及路径):path+"/tempfile/test.zip"  
	 * args(所要压缩的文件):String args[]={path+"/tempfile/download_123.txt",path+"/tempfile/download_456.txt",path+"/tempfile/download_789.txt"}  
	 */  
    public class FileZip{        
    	    public static void main(String[] args) {
    	    	String fileName = "E:" + "//";
    	    			//"c://mydoc//aa.doc"
    	    	String[] strs = {fileName+"android.wmv"};
    	    	createZip(fileName+"ZhuanHua.zip",strs);
    	    	unZip(fileName+"ZhuanHua.zip",fileName+"zhuanhuafile.wmv",0);	
    	    	Unzip(fileName+"ZhuanHua.zip",fileName+"zhuanhuafile2/");
			}
            public static boolean createZip(String compressFileName,String args[]){   
                boolean flag = false;   
                try{           
                    byte b[] = new byte[512];      
                    ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(compressFileName));      
                    for(int i = 0; i < args.length; i++){      
                       InputStream in = new FileInputStream(args[i]);    
                       File file=new File(args[i]);   
                       String filename = file.getName();//取得文件名   
    //                 ZipEntry e = new ZipEntry(args[i].replace(File.separatorChar,'/')); //压缩后带路径    
                       ZipEntry e = new ZipEntry(filename);         //压缩后不带路径   
                       zout.putNextEntry(e);      
                       int len=0;   
                       while((len=in.read(b)) != -1){   
                          zout.write(b,0,len);   
                       }   
                       zout.closeEntry();               
                     }   
                     zout.close();   
                     flag = true;   
                }catch(Exception e){   
                    e.printStackTrace();   
                }   
                return flag;   
            }   
            /**  
             * added by czl   
             * 解zip压缩文件，支持中文路径及中文文件名  
             * @param zipFile  
             * @param outFilePath  
             * @param mode  
             * 参数一为源zip文件的完整路径，参数二为解压缩后存放的文件夹 ,参数三为文件的完整名称
             */  
            public static boolean unZip(String zipFile,String outFilePath,int mode){   
                boolean flag = false;   
                try{   
                  File file = new File(zipFile);   
                  String fileName = file.getName();   
                  if(mode == 1)   
                  {   
                      outFilePath += File.separator;  //文件当前路径下   
                  }else{   
                      outFilePath += File.separator+fileName.substring(0,fileName.length()-4)+File.separator;   
                  }   
                  System.out.println("outFilePath= " + outFilePath);
                  File tmpFileDir = new File(outFilePath);   
                  tmpFileDir.mkdirs();   
                     
                  ZipFile zf = new ZipFile(zipFile);   
                  FileOutputStream fos;   
                     
                  byte[] buf = new byte[1024];   
                  
                  for(Enumeration em = zf.entries(); em.hasMoreElements();){   
                       ZipEntry ze = (ZipEntry) em.nextElement();   
                       if(ze.isDirectory())   
                       {   
                           continue;   
                       }   
                       DataInputStream dis = new DataInputStream(zf.getInputStream(ze) );   
                       String currentFileName = ze.getName();   
                       int dex = currentFileName.lastIndexOf('/');   
                       String currentoutFilePath = outFilePath;   
                       if(dex > 0)   
                       {   
                            currentoutFilePath += currentFileName.substring(0,dex)+File.separator;   
                            File currentFileDir = new File(currentoutFilePath);   
                            currentFileDir.mkdirs();                       
                       }                  
                       fos = new FileOutputStream(outFilePath + ze.getName ( ));   
                       int readLen = 0;   
                       while((readLen = dis.read(buf,0,1024)) > 0 )   
                       {   
                        fos.write(buf , 0 ,readLen);   
                       }   
                       dis.close();   
                       fos.close();                   
                   }   
                   flag = true;   
                 }catch(Exception e){   
                     e.printStackTrace();   
                 }   
                 return flag;   
            }   
            //可以在Android上任何版本中使用，Unzip这个静态方法比较简单，参数一为源zip文件的完整路径，参数二为解压缩后存放的文件夹
            private static void Unzip(String zipFile, String targetDir) {
            	   int BUFFER = 4096; //这里缓冲区我们使用4KB，
            	   String strEntry; //保存每个zip的条目名称
            	   try {
            	    BufferedOutputStream dest = null; //缓冲输出流
            	    FileInputStream fis = new FileInputStream(zipFile);
            	    ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            	    ZipEntry entry; //每个zip条目的实例

            	    while ((entry = zis.getNextEntry()) != null) {

            	     try {
//            	       Log.i("Unzip: ","="+ entry);
            	      int count;
            	      byte data[] = new byte[BUFFER];
            	      strEntry = entry.getName();

            	      File entryFile = new File(targetDir + strEntry);
            	      File entryDir = new File(entryFile.getParent());
            	      if (!entryDir.exists()) {
            	       entryDir.mkdirs();
            	      }
            	      FileOutputStream fos = new FileOutputStream(entryFile);
            	      dest = new BufferedOutputStream(fos, BUFFER);
            	      while ((count = zis.read(data, 0, BUFFER)) != -1) {
            	       dest.write(data, 0, count);
            	      }
            	      dest.flush();
            	      dest.close();
            	     } catch (Exception ex) {
            	      ex.printStackTrace();
            	     }
            	    }
            	    zis.close();
            	   } catch (Exception cwj) {
            	    cwj.printStackTrace();
            	   }
            	  }
    }
