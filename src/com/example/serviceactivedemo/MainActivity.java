package com.example.serviceactivedemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceactivedemo.ListActiveNameAdapter.OnActiveListClick;

public class MainActivity extends Activity implements OnClickListener,OnActiveListClick {
	
	String apkPath ;
	String apkDir = "_Dir" ;
	
	TextView txt;
	Button btn_launcher,btn_reFash;
	EditText edt_input;
	ListView list,listActiveName;
	
	String packageName;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		packageName= this.getPackageName();
		apkPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+packageName+apkDir;
		
		txt = (TextView) findViewById(R.id.label);
		edt_input = (EditText) findViewById(R.id.input);
		btn_launcher = (Button) findViewById(R.id.launcher);
		btn_reFash = (Button) findViewById(R.id.reFlash);
		btn_launcher.setOnClickListener(this);
		btn_reFash.setOnClickListener(this);
		list = (ListView) findViewById(R.id.list);
		listActiveName = (ListView) findViewById(R.id.listActiveName);
		
		txt.setText("工作目录:"+apkPath);
		
		File f = new File(apkPath);
		if(!f.exists()){
			f.mkdirs();
		}

	}
	

	public void loadAPKNameList(){
		List<String> list = new ActiveActionManager().getAllActiveFile(apkPath);
		if(list!=null && list.size()>0){
			ListActiveNameAdapter listActiveNameAdapter = new ListActiveNameAdapter(this	,list);
			listActiveName.setAdapter(listActiveNameAdapter);
			listActiveNameAdapter.setOnActiveListClick(this);
		}
	}

	@Override
	protected void onStart() {
	    super.onStart();
		loadAPKNameList();

	}

	public static PackageInfo getPackageInfo(Context context, String apkFilepath) {
		PackageInfo pkgInfo = null;
		File f = new File(apkFilepath);
		if(f.exists()){
			PackageManager pm = context.getPackageManager();
			try {
				pkgInfo = pm.getPackageArchiveInfo(f.getAbsolutePath(), PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        
        return pkgInfo;
    }


	@Override
    public void onClick(View v) {
		if(v == btn_launcher){
			
		}

		if(v == btn_reFash){
			loadAPKNameList();
		}
	    
    }


	@Override
	public void onActiveNameApkClick(String path) {
		List<String> serviceNames  = new ArrayList<String>();
		PackageInfo info = getPackageInfo(this, path);
		if(info != null){

			ServiceInfo []  services = info.services;
			if(services!=null){
				for (int i = 0; i < services.length; i++) {
					String name = services[i].packageName+"/"+services[i].name;
					serviceNames.add(name);
				}
			}
		}

		ListAdapter la = new ListAdapter(this, serviceNames);
		list.setAdapter(la);
	}
}
