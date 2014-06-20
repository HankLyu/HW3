

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

public class TocHW3 {
	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws JSONException, IOException {
		// TODO 自動產生的方法 Stub
		URL url =new URL(args[0]);
		URLConnection connect = url.openConnection();
		InputStreamReader isr = new InputStreamReader(connect.getInputStream(), "UTF-8");
		JSONArray data = new JSONArray(new JSONTokener(isr));
		int count=0,sum=0,year;
		year=Integer.parseInt(args[3]);
		for(int i=0;i<data.length();i++){
			JSONObject tmp=data.getJSONObject(i);
			if(tmp.getString("鄉鎮市區").equals(args[1]) 
				&& tmp.getString("土地區段位置或建物區門牌").contains(args[2])
				&& tmp.getInt("交易年月")/100 >= year){
				count++;
				sum+=tmp.getInt("總價元");
			}
		}//for each data
		System.out.println(sum/count);
	}
}
