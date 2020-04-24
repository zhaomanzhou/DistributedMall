/*
 *  Abstract superclass for the Troll Game. Maintains an amount of life points.
 *  Subclasses must implement the abstract methods getType and show.
 */
 public abstract class GamePiece {
   
   private int lifePoints;
   
   public GamePiece() {
     lifePoints = 0;
   } 
	  
   public GamePiece(int initPoints) {
     lifePoints = initPoints;
   } 

  /*   
   * Adds the argument "amt" to the lifePoints.
  */
   public void updateLifePoints(double amt){
     if(lifePoints > 0)
       lifePoints += amt;
   }
   
   /* Get the current life points */
   public int getLifePoints(){
      return lifePoints;
   }
   
  /* Return true if lifeLevel is greater than zero, false otherwise. */
   public boolean isAlive(){
     return (lifePoints>0);
   }
   
  /* Abstract methods: subclasses must provide an implementation. */
   public abstract String getType();
   
   public abstract String show();
   
}
