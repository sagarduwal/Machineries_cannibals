public class machineries {
    int mid;
    int countMachineries = 0;
    machineries(int id) {
        this.mid = id;
        this.countMachineries ++;
    }

    public int getId() {
        return mid;
    }

    public boolean action() {
        System.out.println("Do Nothing");
        return false;
    }

    public int countValue() {
        return countMachineries;
    }
}