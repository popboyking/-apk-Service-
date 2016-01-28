package com.example.serviceactivedemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListActiveNameAdapter extends BaseAdapter{


	public OnActiveListClick onActiveListClick;

	public static final String Name = "启动服务:  ";
	Context mContext;
	List<String> serviceName;
	StartServiceTask serviceTask;

	public void setOnActiveListClick( OnActiveListClick onClick){
		onActiveListClick = onClick;
	}

	public ListActiveNameAdapter(Context context, List<String> names) {
		mContext = context;
		serviceName = names;
		serviceTask = new StartServiceTask();
    }
	
	@Override
    public int getCount() {
	    return serviceName.size();
    }

	@Override
    public Object getItem(int position) {
	    return null;
    }

	@Override
    public long getItemId(int position) {
	    return 0;
    }

	@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
		Holder hoder;
		if(convertView!=null){
			hoder = (Holder) convertView.getTag();
		}else{
			hoder = new Holder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);
			hoder.tv = (TextView) convertView.findViewById(R.id.serviceName);
			convertView.setTag(hoder);
		}
		
		final String name =serviceName.get(position);
		final String tempName = name.substring(name.lastIndexOf("/")+1,name.length());
		
		hoder.tv.setText(tempName);
		hoder.tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				onActiveListClick.onActiveNameApkClick(name);

			}
		});
		
		return convertView;
    }
	
	class Holder{
		TextView tv;
		
	}


	public interface  OnActiveListClick{
		void onActiveNameApkClick(String path);
	}

}
