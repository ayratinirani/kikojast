package ir.ounegh.kikojast;

import android.app.*;
import android.os.*;
import android.net.*;
import android.content.*;
import android.app.usage.*;
import android.widget.*;
import java.net.*;
import java.io.*;
import android.graphics.*;
import android.view.View.*;
import android.view.*;
import java.util.*;
import android.widget.AdapterView.*;
import org.w3c.dom.ls.*;

public class MainActivity extends Activity 
{ List<Locator>ls;
	String result="";
	Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			JsonLocator jl=new JsonLocator(msg.getData().getString("ok"),MainActivity.this);
			ls=jl.getLocations();
			//StringBuffer h= new StringBuffer();
			//TextView tv=(TextView) findViewById(R.id.mainTextView);
			
			//tv.setText(msg.getData().getString("ok"));
			
			
			//Toast.makeText(MainActivity.this,(String)msg.getData().get("ok"),1).show();
			ArrayAdapter madapter=new ArrayAdapter<Locator>(MainActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1, ls);
			mlist=(ListView) findViewById(R.id.mainListView);
			mlist.setAdapter(madapter);
			mlist.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
					{
						Locator m=ls.get(0);
						// TODO: Implement this method
						//Intent intent= new Intent("android.action.ACTION_VIEW");
						//intent.setData(URI ("geo:90.0,80.65,q=havij"));
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+String.valueOf(m.getLat())+","+m.getLon()+"?q="+m.getLat()+","+m.getLon()+"("+m.getSender()+")+z=6"));
						startActivity(intent);
					}
				});
			
		}
	};
	
private ProgressDialog progressDialog;
	
	Button b1 ,b2;
	EditText e;
	ListView mlist;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		e=(EditText) findViewById(R.id.mainEditText);
	
		b2=(Button) findViewById(R.id.secButton);
		b2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Intent i= new Intent(MainActivity.this,MyMocation.class);
					startActivity(i);
				}
				
			
			
		}
		);
		b1 = (Button) findViewById(R.id.mainButton);

		b1.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					String q="";
					if(e.getText().length()==0){
     
					}else{
					q="?q="+e.getText().toString();
					}
					result="";
					downloadImage("http://localhost:8080/locator/index.php/kikojast/"+q);
					
				}
		});
	}
		private void downloadImage(final String urlStr) {
			progressDialog=ProgressDialog.show(MainActivity.this,"در حال اتصال","دریافت داده ها از سرور");
			new Thread(){

				public void run(){
					
					Message msg = Message.obtain();
					msg.what = 1;
					Bundle bu=new Bundle();
					bu.putString("ok",getsite(urlStr));
					msg.setData(bu);
					messageHandler.sendMessage(msg);
				}

				private String getsite(String urlStr)
				{
					// TODO: Implement this method
					try
					{
						URL url=new URL(urlStr);
						try
						{
							HttpURLConnection connectin=(HttpURLConnection) url.openConnection();
							connectin.connect();
							InputStream input= connectin.getInputStream();
							BufferedReader b= new BufferedReader(new InputStreamReader(input));
							String line;
							
							while((line=b.readLine())!=null){
								result+=line+"\n";
							}
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
					catch (MalformedURLException e)
					{
						e.printStackTrace();
					}
					progressDialog.dismiss();
					return result;

				}

			}.start();
			
		}
	
	}

	
	

