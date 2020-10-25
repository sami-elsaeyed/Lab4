public class Song{
  public String track;
  public Song next;
  public Song (String s){
    track=s;
  }
  public void displayNode(){
  System.out.println(track);
  }

}