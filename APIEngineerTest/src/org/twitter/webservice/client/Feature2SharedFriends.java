package org.twitter.webservice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
 
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
 
 
public class Feature2SharedFriends {
 
	  static String AccessToken = "766783816891453440-dhc5L6Y7HjQ48Gez2uquiyAldNLxmdD";
	  static String AccessSecret = "Gqc4yxc8eZcQVmxbKiK5S8pMBFNGOKk73OhgD0i03dACT";
	  static String ConsumerKey = "klzF4qfaNqYBp73SLQyIKH6BR";
	  static String ConsumerSecret = "0H4fo2qf4HC3VWATEE0e6XXzAefSGk7AmsvNsKDzya9Mwjuif9";
	  
	  static int test_back=0;
	  
 
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Screen Name of the first Twitter User: ");
		String screenNameUser1  = scan.next();
		
		System.out.println("Enter the Screen Name of the second Twitter User: ");
		String screenNameUser2  = scan.next();
		
		Set<String> set1 = getFollowersList(screenNameUser1);
		Set<String> set2 = getFollowersList(screenNameUser2);
		List<String> newSet = new ArrayList<String>();
		
		for (String string : set2) {
			if(!set1.add(string)){
				newSet.add(string);
			}
		}
		List<List<String>>  lists = getPages(newSet, 10);
		
		boolean flag = true;
		System.out.println("No of shared friends: "+newSet.size());
		System.out.println();
		int next_back_page = 0;
		List<String> finalList = new ArrayList<String>();
		do{
			
			 if(next_back_page >=lists.size()){
		    	   System.out.println("End of the List");
		    	   flag=false;
		    	   break;
		      }
			
			finalList = (List<String>) lists.get(next_back_page);
			
			for(String str:finalList){
				 System.out.println(str);
			}

			next_back_page = getPagination(next_back_page);
	      
		}while(flag);
	}
	
	private static Set<String> getFollowersList(String screen_name) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
		
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer( ConsumerKey, ConsumerSecret);
 
        consumer.setTokenWithSecret(AccessToken, AccessSecret);
        String next_cursor = "-1";
        String cursor ="-1";
        Set<String> list =new HashSet<String>();
        
        do{
        
        	HttpGet request = new HttpGet("https://api.twitter.com/1.1/followers/ids.json?screen_name="+screen_name+"&skip_status=true&include_user_entities=false&count=200&cursor="+cursor);
        
        	consumer.sign(request);
 
        	HttpClient client = new DefaultHttpClient();
        	HttpResponse response = client.execute(request);
        	HttpEntity entity = response.getEntity();
        	String result = null;
        	if (entity != null) {
        		InputStream instream = entity.getContent();
        		result = convertStreamToString(instream);
        		JSONObject myObject = new JSONObject(result);
        		System.out.println("RESPONSE: " + result);
        			if(myObject.get("next_cursor")!=null && myObject.get("previous_cursor")!=null){
            			next_cursor = (String) myObject.get("next_cursor").toString();
            			JSONArray jsonArray = (JSONArray) myObject.get("ids");
            			String obj ;
            			for(int i=0;i<jsonArray.length();i++){
            				obj = (String) jsonArray.get(i).toString();
            				list.add(obj);
            			}
            		}
        		
        		try {
					instream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	cursor = next_cursor;
        	
        }while(!"0".equals(next_cursor));
        return list;
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
	
	public static <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
	    if (c == null)
	        return Collections.emptyList();
	    List<T> list = new ArrayList<T>(c);
	    if (pageSize == null || pageSize <= 0 || pageSize > list.size())
	        pageSize = list.size();
	    int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
	    List<List<T>> pages = new ArrayList<List<T>>(numPages);
	    for (int pageNum = 0; pageNum < numPages;)
	        pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
	    return pages;
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


