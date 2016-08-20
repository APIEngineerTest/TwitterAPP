package org.twitter.webservice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
 
 
public class Feature1UserTweets {
 
	  static String AccessToken = "766783816891453440-dhc5L6Y7HjQ48Gez2uquiyAldNLxmdD";
	  static String AccessSecret = "Gqc4yxc8eZcQVmxbKiK5S8pMBFNGOKk73OhgD0i03dACT";
	  static String ConsumerKey = "klzF4qfaNqYBp73SLQyIKH6BR";
	  static String ConsumerSecret = "0H4fo2qf4HC3VWATEE0e6XXzAefSGk7AmsvNsKDzya9Mwjuif9";
	  
	  static int test_back=0;
 
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
                ConsumerKey,
                ConsumerSecret);
 
        consumer.setTokenWithSecret(AccessToken, AccessSecret);

        int length = 0;
        int next_back_page = 1;
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter valid User Name of the twitter account: ");
        String screenName = scan.next();
        System.out.println("Enter last no. of posts (x) of "+screenName+" you want to view.");
        int posts = scan.nextInt();
        do{
        
        	HttpGet request = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="+screenName+"&count="+posts+"&trim_user=true&exclude_replies=true&contributor_details=false&include_rts=false&page="+next_back_page);
        
        	consumer.sign(request);
 
        	HttpClient client = new DefaultHttpClient();
        	HttpResponse response = client.execute(request);
        	HttpEntity entity = response.getEntity();
        	String result = null;
        	if (entity != null) {

        		InputStream instream = entity.getContent();
        		result = convertStreamToString(instream);
        		System.out.println(result);
        		JSONArray myObject = new JSONArray(result);
        		//System.out.println("RESPONSE: " + result);
        		length = myObject.length();
        		System.out.println(myObject.length());
        		JSONObject obj;
        		for(int i=0;i<myObject.length();i++){
        			 obj = (JSONObject) myObject.get(i);
        			 System.out.println(obj.get("text"));
        		}
        		
                next_back_page = getPagination(next_back_page);
        		
        		System.out.println(myObject.toString());
        		instream.close();
        	}
        	
        }while(length !=0);
        
	}
	
	private static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
	
	private static int getPagination(int next_back_page){
		
		 Scanner scan = new Scanner(System.in);
		 String next_back_char = "f";
		System.out.println("");
    	System.out.println("==================================================================");
    	System.out.println("please enter F or f for forward , any key for backword x for exit.");
    	System.out.println("==================================================================");
    	System.out.println("");
        next_back_char = scan.nextLine();
        int cursor = next_back_page;
    	if("f".equalsIgnoreCase(next_back_char)){
    		cursor = next_back_page+1;
    		test_back += 1;
    	}else if("x".equalsIgnoreCase(next_back_char)){
    		cursor=0;
    		scan.close();
    		System.exit(0);
    	}else{
    		if(test_back>0){
    			cursor = next_back_page-1;
    			test_back -=1;
    		}else{
    			System.out.println("You are not allowed to move back at this point please enter F or f for forward , x for exit.  !!!");
    			next_back_char = scan.nextLine();
    			if("x".equalsIgnoreCase(next_back_char)){
            		cursor=0;
            		scan.close();
            		System.exit(0);
    			}else if("f".equalsIgnoreCase(next_back_char)){
    				test_back +=1;
    				cursor = next_back_page+1;
    			}
    		}
    	}
    	return cursor;
	}
}

