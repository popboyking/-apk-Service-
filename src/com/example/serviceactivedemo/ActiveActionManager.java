package com.example.serviceactivedemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ActiveActionManager {


	public ActiveActionManager() {
    }


	public void save(){


	}



	public List<String> getAllActiveFile(String path){

		List<String> names = null;

		File file = new File(path);

		if(file.exists()){
			File [] nameArray = file.listFiles();

			if(nameArray!=null && nameArray.length>0){
				names = new ArrayList<>();
				for (int i = 0; i <nameArray.length ; i++) {
					if(nameArray[i].getName().toLowerCase().contains(".apk")){
						names.add(nameArray[i].getAbsolutePath());
					}

				}
			}
		}
		return names;

	}


}
