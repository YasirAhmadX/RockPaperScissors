import java.util.Scanner;
import java.util.Random;

public class Main{  //Environment
    public static void main(String[] args) {
        System.out.println("Rock Paper Scissors Game");
		Agent bot = new Agent(5,5,5);
        Judge j = new Judge();
		
		System.out.println("Initial Tree:");
		bot.tree();
        
        char[] plays = {'P','R','S'};
        for(int i=0;i<100;i++)
        {          
            System.out.println("\nPlay "+(i+1));
            Random r = new Random();
            char A = plays[r.nextInt(0,3)];
            char B = bot.play(A);
            int result = j.verdict(A,B);
            System.out.println("JUDGE -> "+A+" vs "+B+": "+result);
            if(result == -1){
                System.out.println("Punish bot");
                bot.punish(A,B);
            }
            else if(result == 1){
                System.out.println("Reward bot");
                bot.reward(A,B);
            }
            
            System.out.println("Tree after "+i+" evolution:");
            bot.tree();
        }
    }
}

class Matchbox{
    int RockBeads;
    int PaperBeads;
    int SciBeads;
    int totalBeads;

    public void setBeads(int RockBeads,int PaperBeads,int SciBeads){
        this.RockBeads = RockBeads;
        this.PaperBeads = PaperBeads;
        this.SciBeads = SciBeads;
        this.totalBeads = RockBeads + PaperBeads + SciBeads;
    }

    public void remove(char bead){
        
        if(bead == 'R' && this.RockBeads>0){
            this.RockBeads--;
            this.totalBeads--;
        }
        else if(bead == 'P' && this.PaperBeads>0){
            this.PaperBeads--;
            this.totalBeads--;

        }
        else if(bead == 'S' && this.SciBeads>0){
            this.SciBeads--;
            this.totalBeads--;
        }
    }

    public void add(char bead){
        if(bead == 'R'){
            this.RockBeads++;
        }
        else if(bead == 'P'){
            this.PaperBeads++;

        }
        else if(bead == 'S'){
            this.SciBeads++;
        }
        this.totalBeads++;
    }

}

class Agent{
    Matchbox RockTree = new Matchbox(); //= {'P','P','P','R','R','R','S','S','S'};
    Matchbox PaperTree = new Matchbox();// = {'P','P','P','R','R','R','S','S','S'};
    Matchbox SciTree = new Matchbox(); // = {'P','P','P','R','R','R','S','S','S'};
    

    public Agent(int RockTree,int PaperTree,int SciTree){
        this.RockTree.setBeads(RockTree,PaperTree,SciTree);
        this.PaperTree.setBeads(RockTree,PaperTree,SciTree);
        this.SciTree.setBeads(RockTree,PaperTree,SciTree);

    }

    public char play(char opponentChoice){
        Random rand = new Random();
        char response;
        Matchbox Tree;
        if(opponentChoice == 'R'){
            Tree = RockTree;
        }
        else if(opponentChoice == 'P'){
            Tree = PaperTree;
        }
        else{
            Tree = SciTree;
        }

        //Suppose the tree is arranged as array [r,r,r,p,p,s,s,s,s] then picking randomly
        int bead = rand.nextInt(Tree.totalBeads);
        if(bead <= Tree.RockBeads){
            response = 'R';
        }
        else if(bead <= Tree.RockBeads + Tree.PaperBeads){
            response = 'P';
        }
        else{
            response = 'S';
        }
        return response;
    }

    public void tree(){
        Matchbox Tree  = this.RockTree;
        System.out.println("==Rock Tree report==");
        System.out.println("Probability of Rock: "+((double)(Tree.RockBeads)/Tree.totalBeads));
        System.out.println("Probability of Paper: "+((double)(Tree.PaperBeads)/Tree.totalBeads));
        System.out.println("Probability of Scissor: "+((double)(Tree.SciBeads)/Tree.totalBeads));

        Tree  = this.PaperTree;
        System.out.println("==Paper Tree report==");
        System.out.println("Probability of Rock: "+((double)(Tree.RockBeads)/Tree.totalBeads));
        System.out.println("Probability of Paper: "+((double)(Tree.PaperBeads)/Tree.totalBeads));
        System.out.println("Probability of Scissor: "+((double)(Tree.SciBeads)/Tree.totalBeads));

        Tree  = this.SciTree;
        System.out.println("==Scissors Tree report==");
        System.out.println("Probability of Rock: "+((double)(Tree.RockBeads)/Tree.totalBeads));
        System.out.println("Probability of Paper: "+((double)(Tree.PaperBeads)/Tree.totalBeads));
        System.out.println("Probability of Scissor: "+((double)(Tree.SciBeads)/Tree.totalBeads));

        
    }

	public void reward(char tree,char option){
        Matchbox Tree;
		if( tree == 'R'){
            Tree = this.RockTree;
		}
		else if(tree == 'P'){
			Tree = this.PaperTree;
		}
		else{
            Tree = this.SciTree;
		}

        Tree.add(option);
	}
    
	public void punish(char tree,char option){
		Matchbox Tree;
		if( tree == 'R'){
            Tree = this.RockTree;
		}
		else if(tree == 'P'){
			Tree = this.PaperTree;
		}
		else{
            Tree = this.SciTree;
		}

        Tree.remove(option);
	}
}

class Judge{
    public int verdict(char A,char B){
        //-1 if A wins, 0 if draw, 1 if B wins
        if(A==B){
            return 0;
        }
        else if((A=='R' && B=='S')||(A=='S' && B=='P')||(A=='P' && B=='R')){
            return -1;
        }
        else{
            return 1;
        }
    }
}
