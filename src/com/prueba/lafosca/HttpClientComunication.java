package com.prueba.lafosca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import android.util.Base64;
import android.util.Log;


public class HttpClientComunication {

	// Variables de control
	private static String tokenAuth = "";
	private static String authentication = "";
	private static String flagGlobal = "";
	private static String stateGlobal = "";
	private static String happinessGlobal = "";
	private static String dirtinessGlobal = "";
	private static String userNameGlobal = "";
	private static String passwordGlobal = "";
	private static List<String> ageGlobal = new ArrayList<String>();
	private static List<String> nameGlobal = new ArrayList<String>();
	
	// Metodo post para creacion de usuario
	public static boolean postData(String usernameAux, String passwordAux) throws JSONException {
		// Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://lafosca-beach.herokuapp.com/api/v1/users");
        JSONObject json = new JSONObject();
        JSONObject json2 = new JSONObject();
        
        /*String username = "Adrian";
        String password = "raizensylar87";*/
        
        String username = usernameAux;
        String password = passwordAux;
 
        try {
            
        	// Creamos el JSON data:        	
        	json.put("username", username);
        	json.put("password", password);

            json2.put("user", json);
            //JSONArray postjson=new JSONArray();
           // postjson.put("users", json);
 
            // Headers:
            httppost.setHeader("Accept", "application/json");
			httppost.setHeader("Content-Type", "application/json; charset=utf-8");
			
			 StringEntity se = new StringEntity(json2.toString(),"UTF-8");
			 se.setContentEncoding("UTF-8");
		     se.setContentType("application/json; charset=UTF-8");

		     httppost.setEntity(se);
		        
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
 
            if(response.getStatusLine().getStatusCode() == 201) {
            	// LLamada correcta
            	
            	// Actualizamos username y password
            	userNameGlobal = username;
                passwordGlobal = password;
                 
            	BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
     			String jsonread = reader.readLine();
     			//JSONTokener tokener = new JSONTokener(json);
     			
     			JSONObject object = (JSONObject) new JSONTokener(jsonread).nextValue();
     			
     			String id = object.getString("id");
     			
     			String token = object.getString("authentication_token");
     			// Actualizamos el token
     			tokenAuth = token;
     			String usernameJson = object.getString("username");
     			
     			// Actualizamos el basic authentication
     			String credentials = username + ":" + password;  
     			String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
     			authentication = "Basic " + base64EncodedCredentials;
     			
     			return true;
            }
            else {
            	//Error
            	return false;
            }
           
			
			/*JSONArray locations = object.getJSONArray("kids");
			for(int i = 0; i < locations.length(); i++) {
				String a = locations.getString(i);
				object = (JSONObject) new JSONTokener(a).nextValue();
				JSONArray locations2 = object.getJSONArray("name");
			}*/
 
        }catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        
        return false;
	}
	
	// Metodo post clean
	public static boolean postClean(String usernameAux, String passwordAux) throws JSONException {
		// Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://lafosca-beach.herokuapp.com/api/v1/clean");
        JSONObject json = new JSONObject();
        JSONObject json2 = new JSONObject();
        
        String username = usernameAux;
        String password = passwordAux;
 
        try {
            
        	// Creamos el JSON data:        	
        	json.put("username", username);
        	json.put("password", password);

            json2.put("user", json);
            //JSONArray postjson=new JSONArray();
           // postjson.put("users", json);
 
            // Headers:
            httppost.setHeader("Accept", "application/json");
			httppost.setHeader("Content-Type", "application/json; charset=utf-8");
			httppost.setHeader("Authorization", "Token token=" + tokenAuth);
			
			 StringEntity se = new StringEntity(json2.toString(),"UTF-8");
			 se.setContentEncoding("UTF-8");
		     se.setContentType("application/json; charset=UTF-8");

		     httppost.setEntity(se);
		        
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
 
            if(response.getStatusLine().getStatusCode() == 201) {
            	return true;            	
            }
            else {
            	//Error
            	return false;
            }
 
        }catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        
        return false;
	}
	
	// Post Nivea not working
	public static boolean postNivea(String usernameAux, String passwordAux) throws JSONException {
		// Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://lafosca-beach.herokuapp.com/api/v1/nivea-rain");
        JSONObject json = new JSONObject();
        JSONObject json2 = new JSONObject();
        
        String username = usernameAux;
        String password = passwordAux;
 
        try {
            
        	// Creamos el JSON data:        	
        	json.put("username", username);
        	json.put("password", password);

            json2.put("user", json);
            //JSONArray postjson=new JSONArray();
           // postjson.put("users", json);
 
            // Post the data:
            httppost.setHeader("Accept", "application/json");
			httppost.setHeader("Content-Type", "application/json; charset=utf-8");
			httppost.setHeader("Authorization", "Token token=" + tokenAuth);
			
			 StringEntity se = new StringEntity(json2.toString(),"UTF-8");
			 se.setContentEncoding("UTF-8");
		     se.setContentType("application/json; charset=UTF-8");

		     httppost.setEntity(se);
		        
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
 
            if(response.getStatusLine().getStatusCode() == 201) {
            	
            	return true;
            }
            else {
            	
            	return false;
            }
 
        }catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        
        return false;
	}
	
	public static boolean getStateMethod() {
		
		try
		{
			StringBuilder builder = new StringBuilder();
			HttpClient httpclient = new DefaultHttpClient();
			
			HttpGet httpget = new HttpGet("http://lafosca-beach.herokuapp.com/api/v1/state");
			
			httpget.setHeader("Accept", "application/json");
			httpget.setHeader("Content-Type", "application/json; charset=utf-8");
			httpget.setHeader("Authorization", "Token token=" + tokenAuth);
			HttpResponse response = httpclient.execute(httpget);
			
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				
				//InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				String json = reader.readLine();
				//JSONTokener tokener = new JSONTokener(json);
				
				JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
				String state = object.getString("state");
				if(state.equals("open")) {
					String id = object.getString("id");
					
					stateGlobal = state;
					String flag = object.getString("flag");
					flagGlobal = flag;
					String happiness = object.getString("happiness");
					happinessGlobal = happiness;
					String dirtiness = object.getString("dirtiness");
					dirtinessGlobal = dirtiness;
					
					JSONArray locations = object.getJSONArray("kids");
					
					List<String> arrayKidsAge = new ArrayList<String>();
					List<String> arrayKidsName = new ArrayList<String>();
					
					for(int i = 0; i < locations.length(); i++) {
						
						String a = locations.getString(i);
						JSONObject object2 = (JSONObject) new JSONTokener(a).nextValue();
						String p = object2.getString("age");
						String n = object2.getString("name");
						arrayKidsAge.add(p);
						
						arrayKidsName.add(n);
					}
					
					ageGlobal = arrayKidsAge;
					nameGlobal = arrayKidsName;
				}
				else {
					stateGlobal = state;
				}
				
				
				return true;
			}
			else {				
				return false;
			}

		} catch (Exception e) {
			Log.e("Net", "Error in network call", e);
		}
		
		return false;
	}
	
	public static boolean getUser() {
		
		try
		{
			StringBuilder builder = new StringBuilder();
			HttpClient httpclient = new DefaultHttpClient();
			
			HttpGet httpget = new HttpGet("http://lafosca-beach.herokuapp.com/api/v1/user");
			
			httpget.setHeader("Accept", "application/json");
			httpget.setHeader("Content-Type", "application/json; charset=utf-8");
			httpget.setHeader("Authorization", authentication);
			HttpResponse response = httpclient.execute(httpget);
			
			if(response.getStatusLine().getStatusCode() == 200) {
				
				HttpEntity entity = response.getEntity();
				
				//InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				String json = reader.readLine();
				//JSONTokener tokener = new JSONTokener(json);
				
				JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
				String id = object.getString("id");
				String token = object.getString("authentication_token");
				tokenAuth = token;
				
				return true;
			}
			else {
				return false;
			}
			
			
			
		} catch (Exception e) {
			Log.e("Net", "Error in network call", e);
		}
		
		return false;
	}

	public static boolean putFlag() throws JSONException {
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPut httpput = new HttpPut("http://lafosca-beach.herokuapp.com/api/v1/flag");
	    JSONObject json = new JSONObject();
	
	    try {
	        // JSON data:
	    	
	    	json.put("flag", "1");
	
	        // Post the data:
	    	httpput.setHeader("Accept", "application/json");
	    	httpput.setHeader("Content-Type", "application/json; charset=utf-8");
	    	httpput.setHeader("Authorization", "Token token=" + tokenAuth);
			
			 StringEntity se = new StringEntity(json.toString(),"UTF-8");
			 se.setContentEncoding("UTF-8");
		     se.setContentType("application/json; charset=UTF-8");
	
		     httpput.setEntity(se);
		        
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httpput);
	        
	        if(response.getStatusLine().getStatusCode() == 204) {
	        	return true;
	        }
	        else {
	        	return false;	        	
	        }
	
	    }catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
		return false;
	}
	
	public static boolean putOpen() throws JSONException {
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPut httpput = new HttpPut("http://lafosca-beach.herokuapp.com/api/v1/open");
	    JSONObject json = new JSONObject();
	
	    try {
	        // JSON data:
	    	
	    	//json.put("flag", "1");
	
	        // Post the data:
	    	httpput.setHeader("Accept", "application/json");
	    	httpput.setHeader("Content-Type", "application/json; charset=utf-8");
	    	httpput.setHeader("Authorization", "Token token=" + tokenAuth);
			
			 //StringEntity se = new StringEntity(json.toString(),"UTF-8");
			// se.setContentEncoding("UTF-8");
		    // se.setContentType("application/json; charset=UTF-8");
	
		    // httpput.setEntity(se);
		        
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httpput);
	        
	        if(response.getStatusLine().getStatusCode() == 200) {
	        	return true;
	        }
	        else {	        	
	        	return false;
	        }	
	
	    }catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	    
	    return false;
	}
	
	public static boolean putClose() throws JSONException {
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPut httpput = new HttpPut("http://lafosca-beach.herokuapp.com/api/v1/close");
	    JSONObject json = new JSONObject();
	
	    try {
	        // JSON data:
	    	
	    	//json.put("flag", "1");
	
	        // Post the data:
	    	httpput.setHeader("Accept", "application/json");
	    	httpput.setHeader("Content-Type", "application/json; charset=utf-8");
	    	httpput.setHeader("Authorization", "Token token=" + tokenAuth);
			
			// StringEntity se = new StringEntity(json.toString(),"UTF-8");
			 //se.setContentEncoding("UTF-8");
		    // se.setContentType("application/json; charset=UTF-8");
	
		    // httpput.setEntity(se);
		        
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httpput);
	
	        if(response.getStatusLine().getStatusCode() == 200) {
	        	return true;
	        }
	        else {	        	
	        	return false;
	        }

	    }catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	    
	    return false;
	}
	
	public static String getFlag() {
		return flagGlobal;
	}
	
	public static String getState() {
		return stateGlobal;
	}
	
	public static String getHappiness() {
		return happinessGlobal;
	}
	
	public static String getDirtiness() {
		return dirtinessGlobal;
	}
	
	public static String getUserName() {
		return userNameGlobal;
	}
	
	public static String getPassword() {
		return passwordGlobal;
	}
	
	public static String getArrayAgePos(int i) {
		return ageGlobal.get(i);
	}
	
	public static String getArrayNamePos(int i) {
		return nameGlobal.get(i);
	}
	
	public static int getLengthAgeArray() {
		return ageGlobal.size();
	}
	
	public static int getLengthNameArray() {
		return nameGlobal.size();
	}
}
