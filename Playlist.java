public class Playlist{
  public Song first;
  public Song last;
  public Playlist(){
    first=null;
  }
  public boolean isEmpty(){
    return(first==null);  
  }
  public void insertFirst(String s){
    Song x=new Song(s);
    if(isEmpty())
      last=x;
    x.next=first;
    first=x;
  }
  public void insertLast(String s){
    Song x=new Song(s);
    if(isEmpty())
      first=x;
  
  }
  public Song playSong(){
    if(first.next==null){
      System.out.println("You have reached the end of the Playlist");
      last=null;
    }
    Song temp=first;
    first=first.next;
    return temp;
  }
  public void displayList()throws Exception{
    Song current=first;
    int counter = 1;
    while(current!=null){
      System.out.print(counter+". ");
      current.displayNode();
      current=current.next;
      counter++;
    }
  }
}