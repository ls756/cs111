
import java.util.List;

public class ls756_UnoPlayer implements UnoPlayer {

    public int play(List<Card> hand, Card upCard, Color calledColor, GameState state) {

        UnoPlayer.Color colorOfUpCard = upCard.getColor();
		int numOfUpCard = upCard.getNumber();
		UnoPlayer.Rank rankOfUpCard = upCard.getRank();
		int[] arr = state.getNumCardsInHandsOfUpcomingPlayers();
		UnoPlayer.Color[] arrC = state.getMostRecentColorCalledByUpcomingPlayers();
		int[] arrS = state.getTotalScoreOfUpcomingPlayers();
		List<Card> used= state.getPlayedCards();
		
		int cardRed = 0;
		int cardGreen = 0;
		int cardBlue = 0;
		int cardYellow = 0;
		
		//the most recent color in played cards
		UnoPlayer.Color maxColor = Color.NONE;		
		
		int maxNum = 0;
		int cardZero = 0;
		int cardOne = 0;
		int cardTwo = 0;
		int cardThree = 0;
		int cardFour = 0;
		int cardFive = 0;
		int cardSix = 0;
		int cardSeven = 0;
		int cardEight = 0;
		int cardNine = 0;
		
		//the most recent number in played cards
		int maxNumber = 0;		
		
		int maxNumb = 0;
		
		
		for(int j=0; j<used.size(); j++){
			if(used.get(j).getColor()==Color.RED){
				cardRed++;}
			if(used.get(j).getColor()==Color.GREEN){
				cardGreen++;}
			if(used.get(j).getColor()==Color.BLUE){
				cardBlue++;}
			if(used.get(j).getColor()==Color.YELLOW){
				cardYellow++;}
		}
		
		maxNum = max(cardRed, cardGreen, cardBlue, cardYellow);
		if(cardRed==maxNum){
		maxColor=Color.RED;}
		if(cardGreen==maxNum){
		maxColor=Color.GREEN;}
		if(cardBlue==maxNum){
		maxColor=Color.BLUE;}
		if(cardYellow==maxNum){
		maxColor=Color.YELLOW;}
		
		for(int k=0; k<used.size(); k++){
			if(used.get(k).getNumber()==0){
				cardZero++;}
			if(used.get(k).getNumber()==1){
				cardOne++;}
			if(used.get(k).getNumber()==2){
				cardTwo++;}
			if(used.get(k).getNumber()==3){
				cardThree++;}
			if(used.get(k).getNumber()==4){
				cardFour++;}
			if(used.get(k).getNumber()==5){
				cardFive++;}
			if(used.get(k).getNumber()==6){
				cardSix++;}
			if(used.get(k).getNumber()==7){
				cardSeven++;}
			if(used.get(k).getNumber()==8){
				cardEight++;}
			if(used.get(k).getNumber()==9){
				cardNine++;}
		}
		
		maxNumb = getMax(cardZero, cardOne, cardTwo, cardThree, cardFour, cardFive, cardSix, cardSeven, cardEight, cardNine);
		if(cardZero==maxNumb){
		maxNumber=0;}
		if(cardOne==maxNumb){
		maxNumber=1;}
		if(cardTwo==maxNumb){
		maxNumber=2;}
		if(cardThree==maxNumb){
		maxNumber=3;}
		if(cardFour==maxNumb){
		maxNumber=4;}
		if(cardFive==maxNumb){
		maxNumber=5;}
		if(cardSix==maxNumb){
		maxNumber=6;}
		if(cardSeven==maxNumb){
		maxNumber=7;}
		if(cardEight==maxNumb){
		maxNumber=8;}
		if(cardNine==maxNumb){
		maxNumber=9;}
		
		
		for(int i=0; i<hand.size(); i++){
			
			if(hand.get(i).getRank()!=Rank.NUMBER){
			//play special cards first, because they have high scores
				if(hand.get(i).getColor()==colorOfUpCard){
					if(hand.get(i).getRank()==Rank.REVERSE){
					//if my next player has less cards than my last player or he has higher total score and I have reverse card, play it
						if(arr[0]<arr[2] || arrS[0]>arrS[2]){
							return i;}
					}
					else{
					return i;}
				}
				
				if(hand.get(i).getRank()==rankOfUpCard){
					return i;}
			}
				
			else{
				//if I have card with same number of up card and it is not the most recent color of cards of next player or it is the most recent color or number in played cards, play it, because there's less possible that other players have this kind of card
				
				if(hand.get(i).getNumber()==numOfUpCard){
					if(hand.get(i).getColor()!=arrC[0]){
						return i;}	
					else if(hand.get(i).getNumber()==maxNumber || hand.get(i).getColor()==maxColor){
						return i;}
				}
				else if(hand.get(i).getColor()==colorOfUpCard){
					if(hand.get(i).getNumber()==maxNumber || hand.get(i).getColor()==maxColor){				
						return i;}
					
				}
				
			}
		}
		
		for(int u=0; u<hand.size(); u++){
			//after check, I don't have the most recent color or number card, recheck and play a available card
			if(hand.get(u).getRank()==Rank.NUMBER){
			
				if(hand.get(u).getColor()==colorOfUpCard){			
					return u;}
				else if(hand.get(u).getNumber()==numOfUpCard){
					return u;}
			}
			
			//if I don't have available card but wild card, play it
			if(hand.get(u).getRank()==Rank.WILD || hand.get(u).getRank()==Rank.WILD_D4){
				return u;}
		}
		return -1;
	}
    
    public Color callColor(List<Card> hand) {
		
		
		
        for(int i=0; i<hand.size(); i++){
			
			if(hand.get(i).getColor()==Color.RED)
				return Color.RED;
			if(hand.get(i).getColor()==Color.YELLOW)
				return Color.YELLOW;
			if(hand.get(i).getColor()==Color.GREEN)
				return Color.GREEN;
			if(hand.get(i).getColor()==Color.BLUE)
				return Color.BLUE;	
						
		}			
			
        return Color.BLUE;
    }
	
	public static int max(int a, int b, int c, int d){
 
		if(a>=b && a>=c && a>=d){
		return a;}
		if(b>=a && b>=c && b>=d){
		return b;}
		if(c>=a && c>=b && c>=d){
		return c;}
		if(d>=a && d>=b && d>=c){
		return d;}
		
		return 0;
	}

	public static int getMax(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j){
		
		if(a>=b && a>=c && a>=d && a>=e && a>=f && a>=g && a>=h && a>=i && a>=j){
		return a;}
		if(b>=a && b>=c && b>=d && b>=e && b>=f && b>=g && b>=h && b>=i && b>=j){
		return b;}
		if(c>=a && c>=b && c>=d && c>=e && c>=f && c>=g && c>=h && c>=i && c>=j){
		return c;}
		if(d>=a && d>=b && d>=c && d>=e && d>=f && d>=g && d>=h && d>=i && d>=j){
		return d;}
		if(e>=a && e>=b && e>=c && e>=d && e>=f && e>=g && e>=h && e>=i && e>=j){
		return e;}
		if(f>=a && f>=b && f>=c && f>=d && f>=e && f>=g && f>=h && f>=i && f>=j){
		return f;}
		if(g>=a && g>=b && g>=c && g>=d && g>=e && g>=f && g>=h && g>=i && g>=j){
		return g;}
		if(h>=a && h>=b && h>=c && h>=d && h>=e && h>=f && h>=g && h>=i && h>=j){
		return h;}
		if(i>=a && i>=b && i>=c && i>=d && i>=e && i>=f && i>=g && i>=h && i>=j){
		return i;}
		if(j>=a && j>=b && j>=c && j>=d && j>=e && j>=f && j>=g && j>=h && j>=i){
		return j;}
		
		return 0;
	}
}
