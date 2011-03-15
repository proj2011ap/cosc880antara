package twitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.*;

public class TUTwitterFetcher3 {
	private static String user;
    private static BufferedReader reader;
    private static Twitter twitter;
    private static Vector feeds;
    private static Vector tweetsVector;
    private static String results;
        
    public static void main(String[] args) {
       user = null;       
       feeds = new Vector();
       tweetsVector = new Vector();
       twitter = new TwitterFactory().getInstance();
                
       //  Check to make sure only a single file argument was provided
           if (args.length > 1)
                   System.out.println ("Too many arguments.");
           else if (args.length > 0){
                   readFile(args[0]);              
                   for(int i = 0; i < 5; i++){ //do I need to change this loop iteration?
                           fetchUserTweets();
                           try {
                                   System.out.println("Sleeping iteration" + i);
                                   Thread.sleep(43200000);//sleeps for 12 hrs
                           } catch (InterruptedException e) {
                                   e.printStackTrace();
                           }
                   }
           
                   try {
                           reader.close();
                           } catch (IOException e) {
                                   e.printStackTrace();
                                   }    
                           }
           }

        /**
         * 
         */     
     private static void fetchUserTweets() {
                for (int i = 0; i < feeds.size(); i++){
                        
                        fetchTweets((TUTwitterFeed) feeds.get(i));
                        writeFile();
                     // tweetsVector.clear();
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Showing next @TU users home timeline...wait few moments....");
                        try {
                                Thread.sleep(30000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }

     private static void readFile(String file){
                String input = null;
                //int inputNo = 0;
                
                try{
                        reader = new BufferedReader (new FileReader (file));
                        
                        input = reader.readLine();
                        
                        while (input != null){
                                processFeed(input);
                                input = reader.readLine();                              
                        }
                        //inputNo++;
                }
                catch (Exception e){
                        System.err.println ("Error reading from file " + file + ": " + e.getMessage());
                }       
        }

        
     private static void processFeed(String input){
                TUTwitterFeed newFeed; 
                String[] feedInformation = null;
                
                feedInformation = input.split(" ");
                
                String feedName = feedInformation[0];
                //String feedURL = feedInformation[1];
                user = feedName;
                                
                newFeed = new TUTwitterFeed(feedName);
                
                feeds.add(newFeed);

        }

 
     private static void writeFile(){
                try{
                        
                        Date currentTime = new Date();
                //        String outputFilename = user + "_" + currentTime.toString().replaceAll(" ", 
                  //                                               "_").replaceAll(":", "") + ".txt"; 
                 
                        FileWriter fstream = new FileWriter("outputFile");
                        BufferedWriter out = new BufferedWriter(fstream);
                        
                       // out.write(results);     
                        //out.write("\n");
                        
                        out.write("Current updates for all the TU users"+" "+currentTime.toString());
                        out.write("\n");
                        out.write("\n");
                        for (int i = 0; i < tweetsVector.size(); i++){
                                out.write(tweetsVector.get(i).toString() + "\n");
                                out.write("************************************************************************\n");
                        }
                    //    out.write(results);     
                        out.write("\n");
                        out.write("\n");                
                        out.close();
                        
                }catch (Exception e){//Catch exception if any
                        System.err.println("Error: " + e.getMessage());
                }
        } 
        
        
     public static void fetchTweets(TUTwitterFeed feed) {
   
        
        Twitter twitter = new TwitterFactory().getInstance();
        List<Status> statuses = null;
                try {
                        statuses = twitter.getUserTimeline(feed.getUser());
                } catch (TwitterException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
        user = feed.getUser().replaceAll(" ", "").replaceAll(",", "");
    
                      
        System.out.println("Getting tweets for " + user + "...\n");
                for (Status status : statuses) {
                    results = ("@" + status.getUser().getScreenName() + " - " + 
                                            status.getText()+ ".  " + status.getCreatedAt().toString());
                   // results = results + ....;
                    tweetsVector.addElement(results);
                }                
               
           }           
        
    }




