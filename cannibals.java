
public class cannibals {
	
	private int id;
	int countCannibals = 0;
	public cannibals(int id){
		this.id = id;
		this.countCannibals++;
	}
	
	public int getid(){
		return id;
	}

	public boolean action(){
		System.out.println("EAT");
		return false;
	}
	public int countValue() {
        return countCannibals;
    }
}
