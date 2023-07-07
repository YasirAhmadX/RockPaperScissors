import java.util.Scanner;
import java.util.Random;

public class Main{  //Environment
    public static void main(String[] args) {
        System.out.println("Rock Paper Scissors Game");
		Agent bot = new Agent();
		Judge judge = new Judge();
		
		System.out.println("Initial Tree:");
		bot.tree();
		
		System.out.println("\nPlay ");
		char A = 'P';
		char B = bot.play(A);
		int result = judge.response(A,B);
		System.out.println("JUDGE -> "+A+" vs "+B+": "+result);
		if(result == 1){
			System.out.println("Punish bot");
			bot.punish(A,B);
		}
		else if(result == -1){
			System.out.println("Reward bot");
			bot.reward(A,B);
		}
		
		System.out.println("Tree after evolution:");
		bot.tree();
		
		
		
    }
}

class Agent{
    static char[] RockTree = {'P','P','P','R','R','R','S','S','S'};
    static char[] PaperTree = {'P','P','P','R','R','R','S','S','S'};
    static char[] SciTree = {'P','P','P','R','R','R','S','S','S'};

    public  static char play(char opponentChoice){
        Random rand = new Random();
        if(opponentChoice == 'R'){
            char response = RockTree[rand.nextInt(RockTree.length)];
			while(response == 'X'){
				response = RockTree[rand.nextInt(RockTree.length)];
			}
			return response;
        }
        else if(opponentChoice == 'P'){
			char response = PaperTree[rand.nextInt(PaperTree.length)];
			while(response == 'X'){
				response = PaperTree[rand.nextInt(PaperTree.length)];
			}
			return response;
        }
        else{
            char response = SciTree[rand.nextInt(SciTree.length)];
			while(response == 'X'){
				response = SciTree[rand.nextInt(SciTree.length)];
			}
			return response;
        }
    }

    public void tree(){
		float countR,countP,countS,countTotal;
		countR=0;
		countP=0;
		countS=0;
		countTotal=0;
		
		System.out.println("\nRock Tree:");
		for(char c:RockTree){
			System.out.print(c+" ");
			countTotal++;
			if(c == 'R'){
				countR++;
			}
			else if(c == 'P'){
				countP++;
			}
			else if(c == 'S'){
				countS++;
			}
			else{
				countTotal--; //As X is never drawn so reduce total count(dont count X)
			}
		}
		System.out.printf("\nProbabilities:\nRock: %f\tPaper: %f\tScissors: %f\n",(countR/countTotal),(countP/countTotal),(countS/countTotal));
		
		countR=0;
		countP=0;
		countS=0;
		countTotal=0;
		
		System.out.println("\nPaper Tree");
		for(char c:PaperTree){
			System.out.print(c+" ");
			countTotal++;
			if(c == 'R'){
				countR++;
			}
			else if(c == 'P'){
				countP++;
			}
			else if(c == 'S'){
				countS++;
			}
			else{
				countTotal--; //As X is never drawn so reduce total count(dont count X)
			}
		}
		System.out.printf("\nProbabilities:\nRock: %f\tPaper: %f\tScissors: %f\n",(countR/countTotal),(countP/countTotal),(countS/countTotal));
		
		countR=0;
		countP=0;
		countS=0;
		countTotal=0;
		
		System.out.println("\nScissors Tree: ");
		for(char c:SciTree){
			System.out.print(c+" ");
			countTotal++;
			if(c == 'R'){
				countR++;
			}
			else if(c == 'P'){
				countP++;
			}
			else if(c == 'S'){
				countS++;
			}
			else{
				countTotal--; //As X is never drawn so reduce total count(dont count X)
			}
		}
		System.out.printf("\nProbabilities:\nRock: %f\tPaper: %f\tScissors: %f\n",(countR/countTotal),(countP/countTotal),(countS/countTotal));
    }
	
	public void reward(char Tree,char option){
		if(Tree == 'R'){
			for(int i=0;i<9;i++){
				if(RockTree[i] == 'X'){
					RockTree[i] = option;
					break;
				}
			}
		}
		else if(Tree == 'P'){
			for(int i=0;i<9;i++){
				if(PaperTree[i] == 'X'){
					PaperTree[i] = option;
					break;
				}
			}
		}
		else{
			for(int i=0;i<9;i++){
				if(SciTree[i] == 'X'){
					SciTree[i] = option;
					break;
				}
			}
		}
	}
    
	public void punish(char Tree,char option){
		if(Tree == 'R'){
			for(int i=0;i<9;i++){
				if(RockTree[i] == option){
					RockTree[i] = 'X';
					break;
				}
			}
		}
		else if(Tree == 'P'){
			for(int i=0;i<9;i++){
				if(PaperTree[i] == option){
					PaperTree[i] = 'X';
					break;
				}
			}
		}
		else{
			for(int i=0;i<9;i++){
				if(SciTree[i] == option){
					SciTree[i] = 'X';
					break;
				}
			}
		}
	}
}

class Judge{
    public static int response(char A,char B){
        if(A==B){
            return 0;
        }
        else if((A=='R' && B=='S')||(A=='S' && B=='P')||(A=='P' && B=='S')){
            return -1;
        }
        else{
            return 1;
        }
    }
}
