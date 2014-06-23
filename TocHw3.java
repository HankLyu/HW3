import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

/*	parse url json from 內政部房屋交易
 * 	for each json object, check its area , road and year
 * 	if this object satisfy this condition
 * 	add its price of this deal to sum and count+1
 */ 
public class TocHw3 {
	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws JSONException, IOException {
		// TODO 自動產生的方法 Stub
		URL url =new URL(args[0]);	//get json from url
		URLConnection connect = url.openConnection();
		InputStreamReader isr = new InputStreamReader(connect.getInputStream(), "UTF-8");
		JSONArray data = new JSONArray(new JSONTokener(isr));	//save it to json array
		int count=0,sum=0,year;	//count->count num, sum->sum of price of all object, year->condition year
		year=Integer.parseInt(args[3]);
		for(int i=0;i<data.length();i++){
			JSONObject tmp=data.getJSONObject(i);	//get object from json array
			if(tmp.getString("鄉鎮市區").equals(args[1]) 
				&& tmp.getString("土地區段位置或建物區門牌").contains(args[2])
				&& tmp.getInt("交易年月")/100 >= year){	//交易年月 is year + month-> year/100
				count++;
				sum+=tmp.getInt("總價元");
			}
		}//for each data
		System.out.println(sum/count);
	}
}
