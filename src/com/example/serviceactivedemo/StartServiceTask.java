package com.example.serviceactivedemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

public class StartServiceTask {
	public boolean excute(String strServiceName){
		Runtime tun = Runtime.getRuntime();
		Process pro = null;
		try {
			pro = tun.exec("am startservice --user 0 -n "+strServiceName);
			InputStream is = pro.getInputStream();
			BufferedReader br = new BufferedReader( new InputStreamReader(is));
			
			String str = "";
			StringBuilder sb = new StringBuilder();
			while ((str =br.readLine())!= null ) {
				sb.append(str);
				
            }
			String str1 = sb.toString();
			Log.e("ServiceActive", str1);
        }
        catch (IOException e) {
	       Log.e("ServiceActive","startError:"+ e.getMessage());
	        e.printStackTrace();
	        return false;
        }
	
		return true;
	}
}
