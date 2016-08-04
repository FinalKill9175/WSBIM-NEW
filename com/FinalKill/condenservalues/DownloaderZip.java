package com.FinalKill.condenservalues;

import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import org.apache.logging.log4j.core.appender.FileManager;

import com.sun.naming.internal.ResourceManager;

public class DownloaderZip {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String download_link;
	public static String f_name_1;
	public static boolean done = false;
	private static int id;
	/**
	 * @return the done
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * @param done the done to set
	 */
	public static void setDone(boolean done) {
		DownloaderZip.done = done;
	}

	private static JDialog frm;
	protected static JProgressBar current;
    public DownloaderZip(int id, final String site, final String filename, String filename_2)
    {
    	DownloaderZip.id = id;
    	download_link = site;
    	f_name_1 = filename;
    	
    	try{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	}
    	catch(Exception e){
    		;
    	}
    
    	if(CondenserItemValues.needsToUpdateCondenserValues){
    		
    	
    
     frm=new JDialog();
    frm.setTitle("Condenser Values API : Downloading: "+filename_2);
    frm.getContentPane().setLayout(null);
     current = new JProgressBar(0, 100);
    current.setBounds(10, 74, 364, 50);

    
  
    frm.getContentPane().add(current);
    
    JLabel lblFileUrl = new JLabel("File URL:" + site);
    lblFileUrl.setBounds(10, 11, 364, 14);
    frm.getContentPane().add(lblFileUrl);
    
    JLabel lblFileName = new JLabel("File Name:" + filename);
    lblFileName.setBounds(10, 36, 364, 14);
    frm.getContentPane().add(lblFileName);
    frm.setVisible(true);
    frm.setSize(400, 200);
    frm.setLocationRelativeTo(null);
 
    	}
 start();
    
   
   

   
}
    
    public static void start(){
    	new SwingWorker<Object, Object>(){

    		@Override
    		protected Object doInBackground() throws Exception {
    			 try {
    				 if(CondenserItemValues.needsToUpdateCondenserValues){
    			        URL url=new URL(download_link);
    			        HttpURLConnection connection =
    			            (HttpURLConnection) url.openConnection();
    			        int filesize = connection.getContentLength();
    			        setDone(false);
    			        float totalDataRead=0;
    			            java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
    			            java.io.FileOutputStream fos = new java.io.FileOutputStream(f_name_1);
    			            java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
    			            byte[] data = new byte[1024];
    			            int i=0;
    			            while((i=in.read(data,0,1024))>=0)
    			            {
    			            totalDataRead=totalDataRead+i;
    			            bout.write(data,0,i);
    			            float Percent=(totalDataRead*100)/filesize;
    			            current.setValue((int)Percent);
    			            }  
     
    			            bout.close();
    			            in.close();
    			            frm.dispose();
    			           
    			            setDone(true);
    			            onFinished();
    				 }
    				 else{
    					 setDone(true);
    					 onFinished();
    				 }
    			    }
    			    catch(Exception e)
    			    {
    			         javax.swing.JOptionPane.showMessageDialog((java.awt.Component)
    			                 null,e.getMessage(), "Error",
    			                 javax.swing.JOptionPane.ERROR_MESSAGE);
    			         e.printStackTrace();
    			    }
    			 return null;
    		}
        	
        }.execute();
    	
    }
    
    public static void onFinished(){
    	if(id<CondenserItemValues.filesToDownload.size()-1){
    		FileZip zipFile = CondenserItemValues.filesToDownload.get(id+1);
    		if(zipFile !=null){
    			new DownloaderZip(id+1, zipFile.url, CondenserItemValues.valuesFolder.getPath()+"//"+zipFile.name+".zip",zipFile.name+".zip");
    		}
    	}
    	else{
    		try {
				CondenserItemValues.extractZips();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
}
