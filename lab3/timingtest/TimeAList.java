package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        int[] NList={1000,2000,4000,8000,16000,32000,64000,128000};
        AList<Integer> Ns=new AList<>();
        AList<Double> times=new AList<>();
        for (int i:NList){
            Ns.addLast(i);
            AList<Integer> alist=new AList<Integer>();
            Stopwatch sw=new Stopwatch();
            for (int ii=0;ii<i;ii++){
                alist.addLast(ii);
            }
            double timeInSeconds=sw.elapsedTime();
            times.addLast(timeInSeconds);
        }
        printTimingTable(Ns,times, Ns);
    }
}
