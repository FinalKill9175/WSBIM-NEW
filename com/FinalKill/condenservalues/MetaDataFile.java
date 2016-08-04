package com.FinalKill.condenservalues;

import java.util.List;

public class MetaDataFile {
	private String version;
	private String[] urls;
	protected MetaDataFile(String version, List<String> urls){
		String[] u = new String[urls.size()];
		for(int i = 0; i < urls.size(); i++){
			String str = urls.get(i);
			u[i] = str;
		}
		
		this.version = version;
		this.urls = u;
	}
	public String getVersion() {
		return version;
	}
	public String[] getUrls() {
		return urls;
	}
	
}
