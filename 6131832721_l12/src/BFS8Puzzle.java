import java.util.ArrayList;

public class BFS8Puzzle {
	
	public class Node {
		private State s;
		private String ans;
		public Node(State s,String ans) {
			this.s = s;
			this.ans = ans;
		}
		public State getState() {
			return this.s;
		}
		public String getAnswer() {
			return ans;
		}
	}
	//1 8 3  1 2 3      //183456720
	//4 5 6  4 5 6      //123456780
	//7 2 0  7 8 0      //coordinate-->X=2,Y=0
	public class State {
		private String s;
		private int size;
		private int currentBlank;
		public int distance() {
			int manhattanBoy=0;
			String goalState ="123456780";
			for(int i=0;i<size*size;i++) {
				int miniman=0;
				String currentNum = s.substring(i,i+1); //skip the 0 one
				if(Integer.parseInt(currentNum)!=0) {
					int startPos=s.indexOf(currentNum);
					int goalPos=goalState.indexOf(currentNum);
					int x = Math.abs(startPos-goalPos)%size;
					int y = Math.abs(startPos-goalPos)/size;
					miniman = x+y;
					manhattanBoy+=miniman;
				}
			}
			return manhattanBoy;
		}
		public State(String s,int size) {
			this.s = s;
			this.size = size;
			currentBlank = s.indexOf("0");
		}
		public boolean canMoveUp() {
			if(currentBlank < size) 
				return false;
			return true;
		}
		public boolean canMoveDown() {
			if(size*size-currentBlank > size) 
				return true;
			return false;
		}
		public boolean canMoveLeft() {
			if(currentBlank % size == 0)
				return false;
			return true;
		}
		public boolean canMoveRight() {
			if((currentBlank+1) % size == 0) 
				return false;
			return true;
		}
		private void swap(int i,int j) {
			String p1 = s.substring(i,i+1);
			String p2 = s.substring(j,j+1);
			s = s.substring(0,i)+p2+s.substring(i+1,j)+p1+s.substring(j+1);
		}
		public void moveUp() {
			swap(currentBlank-size,currentBlank);
			currentBlank -= size;
		}
		public void moveRight() {
			swap(currentBlank,currentBlank+1);
			currentBlank++;
		}
		public void moveLeft() {
			swap(currentBlank-1,currentBlank);
			currentBlank--;
		}
		public void moveDown() {
			swap(currentBlank,currentBlank+size);
			currentBlank += size;
		}
		public void show() {
			for(int i=0;i<size;i++) {
				for(int j=0;j<size;j++) {
					System.out.print(s.substring(i*size+j,i*size+j+1)+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		public String getString() {
			return s;
		}
	}
	
	public class Fringe {
		ArrayList<Node> f = new ArrayList<Node>();
		private int numadd=0;
		private int numrem=0;
		public int getNumAdded() {
			return numadd;
		}
		public int getNumRemoved() {
			return numrem;
		}
		public void add(Node n) { //change add& remove to priorityQ
			numadd++;
			f.add(n);
			int fIndex=f.size()-1;
			while(true) {
				if(fIndex-1==-1) break;
				if(f.get(fIndex).getState().distance()<f.get(fIndex-1).getState().distance()) {
					Node n1 =f.get(fIndex);
					Node n2 =f.get(fIndex-1);
					Node temp =n1;
					n1=n2;
					n2=temp;
					fIndex--;
				} else {
					break;
				}
			}
		}
		public Node removeFront() { //remove shortest distance
			numrem++;
			int dis=100;
			int index=0;
			Node shortestDis = null;
			for(int i=0;i<f.size();i++) {
				if(f.get(i).getState().distance()<dis) {
					dis=f.get(i).getState().distance();
					shortestDis = f.get(i);
					index=i;
				}
			}
			f.remove(index);
			return shortestDis;
		}
		public boolean isEmpty() {
			return f.isEmpty();
		}
		public void showAll() {
			for(int i=0;i<f.size();i++) {
				f.get(i).getState().show();
			}
		}
	}
	
	public static void insertAll(Fringe f,State s,String ans,int size) {
		BFS8Puzzle t = new BFS8Puzzle();
		if(s.canMoveUp()) {
			State tempState = t.new State(s.getString(),size);
			tempState.moveUp();
			f.add(t.new Node(tempState,ans+"U"));
		}
		if(s.canMoveDown()) {
			State tempState = t.new State(s.getString(),size);
			tempState.moveDown();
			f.add(t.new Node(tempState,ans+"D"));
		}
		if(s.canMoveRight()) {
			State tempState = t.new State(s.getString(),size);
			tempState.moveRight();
			f.add(t.new Node(tempState,ans+"R"));
		}	
		if(s.canMoveLeft()) {
			State tempState = t.new State(s.getString(),size);
			tempState.moveLeft();
			f.add(t.new Node(tempState,ans+"L"));
		}	
	}
	public static void main(String[] args) {
		BFS8Puzzle t = new BFS8Puzzle();
		int size=3;
		State s = t.new State("152043786",size);
		Fringe f = t.new Fringe();
		f.add(t.new Node(s,""));
		while(true) {
			if(f.isEmpty()) {
				System.out.println("Fail");
				break;
			}
			Node front =  f.removeFront();
			if(front.getState().getString().equals("123456780")) {
				System.out.println(front.getAnswer());
				break;
			}
			insertAll(f,front.getState(),front.getAnswer(),size);
		}
		System.out.println("Added Nodes: "+f.getNumAdded());
		System.out.println("Removed Nodes: "+f.getNumRemoved());
		System.out.println("Moved Needed: "+s.distance());
	}
}
