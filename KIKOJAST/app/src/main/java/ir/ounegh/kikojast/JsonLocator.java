package ir.ounegh.kikojast;
import java.util.*;
import org.json.*;
import android.widget.*;
import android.content.*;

public class JsonLocator
{
	String input;
	Context context;
	List<Locator>locates=new ArrayList<>();
	public JsonLocator(String in,Context c){
		this.input=in;
		this.context=c;
	}
	public List<Locator> getLocations(){
		try
		{
			//JSONObject result=new JSONObject(input);
			//Toast.makeText(context,result.get("locations"),1).show();
			//JSONObject loc=result.getJSONObject("locations");
			JSONArray array = new JSONArray(input);
			//Toast.makeText(context,".ارایهععععع"+array.get(0),1).show();
			for(int i=0;i<array.length();i++){
				JSONObject obj=array.getJSONObject(i);
				
				Locator l= new Locator();
				l.setId(obj.getInt(("id")));
				l.setLat(obj.getDouble(("lat")));
				l.setLon(obj.getDouble(("lon")));
				l.setSender(obj.getString(("sender")));
				l.setTime(obj.getInt("time"));
				locates.add(l);
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
		}
		return locates;
	}
	
	
}
