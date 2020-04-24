/*
 *  Represents an "empty" game piece. It's type is "Empty" and show is a space character.
 */
 public class EmptyPiece extends GamePiece {
	  
   public EmptyPiece() {
       super();
   } 
   
   public String getType(){
      return "Empty";
   } 
   
   public String show(){
	 return " ";
   } 
}