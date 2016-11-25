package ir.ounegh.kikojast;
import java.util.*;

public class Locator
{
	int id;
	double lat;
	double lon;
	long time;
	String sender;

	public Locator(int id, double lat, double lon, long time, String sender)
	{
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.time = time;
		this.sender = sender;
	}
	public Locator(){
		
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setLat(double lat)
	{
		this.lat = lat;
	}

	public double getLat()
	{
		return lat;
	}

	public void setLon(double lon)
	{
		this.lon = lon;
	}

	public double getLon()
	{
		return lon;
	}

	public void setTime(long time)
	{
		this.time = time;
	}

	public long getTime()
	{
		return time;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}

	public String getSender()
	{
		return sender;
	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		return getId()+"\n"+getLat()+",,"+getLon()+"\n"+getSender()+" "+new Date(getTime()*1000).toLocaleString();
	}

	
	
}
