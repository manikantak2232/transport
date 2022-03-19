package com.pixelbox.service;

import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.pixelbox.exceptions.TransportException;

import java.sql.SQLException;
import java.io.ByteArrayInputStream;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Path("/image")
public class ImageService {
	
		@POST
	    @Path("/uploadToAwsTest")
	    @Produces(MediaType.APPLICATION_JSON)
	    public static synchronized HashMap<String, Object> uploadToAwsTest1(HashMap <String, Object> requestParameters)    throws TransportException, SQLException {

	        HashMap<String, Object> responseParameters = new HashMap<String, Object>();
	        
	        String errorMessage = "";
	        responseParameters.put("result", "false");
	        String content_type = "";
	        
	        String imageString64BitEncoded = requestParameters.get("image_bytes_string").toString();        
	        String image_type = requestParameters.get("image_type").toString();
	        String bucket = requestParameters.get("bucket").toString();
	        String folder = requestParameters.get("folder").toString();
	        String primary_id = requestParameters.get("primary_id").toString();
	        
	        try {                                    
	            byte[] imageBytes = Base64.getDecoder().decode(imageString64BitEncoded.getBytes());
	            
	            if(image_type.equalsIgnoreCase("jpg")){
	                content_type = "image/jpeg";
	            } else {
	                content_type = "image/"+image_type;
	            }
	            
	            // watch the video at https://www.youtube.com/watch?v=ozWgWgYxQ_g to create AWS Credentials
	            
	            AWSCredentials credentials = new BasicAWSCredentials(
	            "AKIAJ4Z4MDN35WA6L4KA", 
	            "GtT93/tpAHfeNsA7HtxqFoa8e0DI23JyUdsfNjB3");
	            
	            // create a client connection based on credentials
	            AmazonS3 s3client = new AmazonS3Client(credentials);
	                        
	            // upload file to folder and set it to public
	            String fileName = folder + "/" + primary_id + "." + image_type;
	            
	            InputStream stream = new ByteArrayInputStream(imageBytes);
	            ObjectMetadata meta = new ObjectMetadata();
	            meta.setContentLength(imageBytes.length);
	            meta.setContentType("content_type");
	            s3client.putObject(new PutObjectRequest(bucket, fileName, stream, meta));
	            s3client.setObjectAcl(bucket, fileName, CannedAccessControlList.PublicRead);
	            
	            String uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+bucket+"/"+fileName;
	            
	            responseParameters.put("result", "true");
	            responseParameters.put("message", "File saved on Amazon Space");
	            responseParameters.put("uploaded_image_url", uploaded_image_url);
	        
	        } catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        return responseParameters;
	    }
		
		public static synchronized HashMap<String, Object> uploadToAws(String imageString64BitEncoded,
				String image_type,
				String bucket,
				String folder,
				String primary_id)    throws TransportException, SQLException {

	        HashMap<String, Object> responseParameters = new HashMap<String, Object>();
	        
	        String errorMessage = "";
	        responseParameters.put("result", "false");
	        String content_type = "";
	        
	        try {                                    
	            byte[] imageBytes = Base64.getDecoder().decode(imageString64BitEncoded.getBytes());
	            
	            if(image_type.equalsIgnoreCase("jpg")){
	                content_type = "image/jpeg";
	            } else {
	                content_type = "image/"+image_type;
	            }
	            
	            // watch the video at https://www.youtube.com/watch?v=ozWgWgYxQ_g to create AWS Credentials
	            
	            AWSCredentials credentials = new BasicAWSCredentials(
	            "AKIAJ4Z4MDN35WA6L4KA", 
	            "GtT93/tpAHfeNsA7HtxqFoa8e0DI23JyUdsfNjB3");
	            
	            // create a client connection based on credentials
	            AmazonS3 s3client = new AmazonS3Client(credentials);
	                        
	            // upload file to folder and set it to public
	            String fileName = folder + "/" + primary_id + "." + image_type;
	            
	            InputStream stream = new ByteArrayInputStream(imageBytes);
	            ObjectMetadata meta = new ObjectMetadata();
	            meta.setContentLength(imageBytes.length);
	            meta.setContentType("content_type");
	            s3client.putObject(new PutObjectRequest(bucket, fileName, stream, meta));
	            s3client.setObjectAcl(bucket, fileName, CannedAccessControlList.PublicRead);
	            
	            String uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+bucket+"/"+fileName;
	            
	            responseParameters.put("result", "true");
	            responseParameters.put("message", "File saved on Amazon Space");
	            responseParameters.put("uploaded_image_url", uploaded_image_url);
	        
	        } catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        return responseParameters;
	    }
		
		public static synchronized HashMap<String, Object> uploadToAwsInvoice(String imageString64BitEncoded,
				String image_type,
				String bucket,
				String folder,
				int primary_id)    throws TransportException, SQLException {

	        HashMap<String, Object> responseParameters = new HashMap<String, Object>();
	        
	        String errorMessage = "";
	        responseParameters.put("result", "false");
	        String content_type = "";
	        
	        try {                                    
	            byte[] imageBytes = Base64.getDecoder().decode(imageString64BitEncoded.getBytes());
	            
	            if(image_type.equalsIgnoreCase("jpg")){
	                content_type = "image/jpeg";
	            } else {
	                content_type = "image/"+image_type;
	            }
	            
	            // watch the video at https://www.youtube.com/watch?v=ozWgWgYxQ_g to create AWS Credentials
	            
	            AWSCredentials credentials = new BasicAWSCredentials(
	            "AKIAJ4Z4MDN35WA6L4KA", 
	            "GtT93/tpAHfeNsA7HtxqFoa8e0DI23JyUdsfNjB3");
	            
	            // create a client connection based on credentials
	            AmazonS3 s3client = new AmazonS3Client(credentials);
	                        
	            // upload file to folder and set it to public
	            String fileName = folder + "/" + primary_id + "." + image_type;
	            
	            InputStream stream = new ByteArrayInputStream(imageBytes);
	            ObjectMetadata meta = new ObjectMetadata();
	            meta.setContentLength(imageBytes.length);
	            meta.setContentType("content_type");
	            s3client.putObject(new PutObjectRequest(bucket, fileName, stream, meta));
	            s3client.setObjectAcl(bucket, fileName, CannedAccessControlList.PublicRead);
	            
	            String uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+bucket+"/"+fileName;
	            
	            responseParameters.put("result", "true");
	            responseParameters.put("message", "File saved on Amazon Space");
	            responseParameters.put("uploaded_image_url", uploaded_image_url);
	        
	        } catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        return responseParameters;
	    }
		
		public static synchronized HashMap<String, Object> uploadToAwsWorkImage(String imageString64BitEncoded,
				String image_type,
				String bucket,
				String folder,
				String primary_id)    throws SQLException {

				HashMap<String, Object> responseParameters = new HashMap<String, Object>();

				String errorMessage = "";
				responseParameters.put("result", "false");
				String content_type = "";

				try {                                    
				byte[] imageBytes = Base64.getDecoder().decode(imageString64BitEncoded.getBytes());

				if(image_type.equalsIgnoreCase("jpg")){
				    content_type = "image/jpeg";
				} else {
				    content_type = "image/"+image_type;
				}

				// watch the video at https://www.youtube.com/watch?v=ozWgWgYxQ_g to create AWS Credentials

				AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAJ4Z4MDN35WA6L4KA", 
				"GtT93/tpAHfeNsA7HtxqFoa8e0DI23JyUdsfNjB3");

				// create a client connection based on credentials
				AmazonS3 s3client = new AmazonS3Client(credentials);
				            
				// upload file to folder and set it to public
				String fileName = folder + "/" + primary_id + "." + image_type;

				InputStream stream = new ByteArrayInputStream(imageBytes);
				ObjectMetadata meta = new ObjectMetadata();
				meta.setContentLength(imageBytes.length);
				meta.setContentType("content_type");
				s3client.putObject(new PutObjectRequest(bucket, fileName, stream, meta));
				s3client.setObjectAcl(bucket, fileName, CannedAccessControlList.PublicRead);

				String uploaded_image_url = "https://s3.ap-south-1.amazonaws.com/"+bucket+"/"+fileName;

				responseParameters.put("result", "true");
				responseParameters.put("message", "File saved on Amazon Space");
				responseParameters.put("uploaded_image_url", uploaded_image_url);

				} catch(Exception e){
				e.printStackTrace();
				}

				return responseParameters;
				}


}
