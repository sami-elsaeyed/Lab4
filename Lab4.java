/*Sami Elsaeyed 10/25/2020
 * This Program inputs weekly data from a yearly quarter of Top 200 Artists
 * By implementing a linked list the data is sorted and the unique ones are played in a playlist in ascending order
 * The songs that are played are recorded in a different linked list and can be viewed by entering 'H'
 * Entering any key plays the next song and pressing 'E' exits the program.
 */
import java.util.*;
import java.io.*;  
public class Lab4 {
  public static void main(String[]args)throws Exception{
    //String array of all the file names
    String[] quarter=new String[]{"MusicData0103.csv","MusicData0110.csv","MusicData0117.csv","MusicData0124.csv",
                                  "MusicData1031.csv","MusicData0207.csv","MusicData0214.csv","MusicData0221.csv",
                                  "MusicData0228.csv","MusicData0306.csv","MusicData0313.csv","MusicData0320.csv","MusicData0327.csv"};
    //'Playlist' is implemented using a Linked List and Song is Node in that list
    Playlist fullQuarter=new Playlist();
    Playlist week=new Playlist();
    //Loop through files turning Strings from files into Playlists and combining all weekly lists into one list for the Quarter
    for(int i=0;i<quarter.length;i++){
      week=queue(quarter[i],week);
      concat(fullQuarter,week);
    }
    //Methods for sorting, finding unique elements and the user interactive music player method
    Song yo=sort(fullQuarter.first);
    fullQuarter=unique(yo);
    player(fullQuarter);  
  }
  //Method scans CSV file and enters data into a 2D Array, column with song names are entered into a Linked List
  public static Playlist queue(String s,Playlist week)throws Exception{
    File file = new File(s);
    Scanner scan= new Scanner(file,"UTF-8").useDelimiter("\\s*,\\s*");
    String h;
    Playlist part=new Playlist();
    String [][] data=new String[200][4];
    //Nested 'for' loop to fill in array 
    for(int i=0;i<200;i++){
      for(int k=0;k<4;k++){
        data[i][k]=scan.next();
      }  
      h=scan.nextLine(); 
      part.insertFirst(data[i][1]); 
    }
    return part;
  }
  //Method combines each Linked list into a single one
  public static void concat(Playlist full,Playlist part){
    if(full.first==null){
      full.first=part.first;
      full.last=part.last;
    }
    else{
      full.last.next=part.first;
      full.last=part.last;
    }
  }
  //Recursive Method implements Merge Sort 
  public static Song sort (Song t){
    //Base case
    if(t==null||t.next==null){
      return t;}
    //Method finds midpoint and splits list
    Song mid=findMid(t);
    //Recursively Sort
    Song left=sort(t);
    Song right=sort(mid);
    // Merge sorted halves
    Song sorted=merge(left,right);
    return sorted;  
    
    }
  public static Song findMid(Song t){
    //If list has one or no elements return
    if(t==null||t.next==null)
      return t;
    //Slow/fast where slow references the middle when fast reaches the end
    Song slow=t;
    Song fast=t;
    while(fast.next!=null&&fast.next.next!=null){
      slow=slow.next;
      fast=fast.next.next;
    }
    //Making two halves by setting the element after the middle to null and a new list starting after middle
    Song mn=slow.next;
    slow.next=null;
    //Return first node of second half
    return mn;
  }
  //Recursively Sorts and Merges sorted halves in descending order
  public static Song merge(Song left,Song right){
    Song result=null;
    //Base case
    if(left==null)
      return right;
    else if(right==null)
      return left;
    //Compare, enter larger into new list then recursively find next element
    if(left.track.compareTo(right.track)>0){
      result=left;
      result.next=merge(left.next,right);
    }
    else{
      result=right;
      result.next=merge(left,right.next);
    }
    return result;
  }
  public static Playlist unique(Song all)throws Exception{
    Playlist uni = new Playlist();
    while(all.next!=null){
      //Only unique elements are entered into final list
      if(all.track.compareTo(all.next.track)==0)
        all=all.next;
      else{
        uni.insertFirst(all.track);
        all=all.next;
        }
    }
    return uni; 
  }
  //User interactive metod using prompts and scanner  
  public static void player(Playlist h)throws Exception{
    Playlist history=new Playlist();
    Song playing=h.playSong();
    Scanner scan = new Scanner(System.in);
    //Enter daata for x to enter while loop`
    System.out.println("Press Any Button to start Playlist");
    Character x = scan.next().charAt(0);
    while(x!='E'&&x!='e'){
      if(x=='H'||x=='h'){
        //Display player history most recent first
        System.out.print("Your most recent song:");
        history.displayList();
      }
      //Play Song and add it to history prompt for next function
      else{
      history.insertFirst(playing.track);
      System.out.println(playing.track+" is now playing");}
      playing=playing.next;
      System.out.println("Press Any Button to play next song press E to exit");
       x = scan.next().charAt(0);
    }
    return;
  }
}
  
