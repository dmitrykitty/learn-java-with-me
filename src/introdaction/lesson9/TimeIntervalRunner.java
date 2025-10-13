package introdaction.lesson9;

public class TimeIntervalRunner {
    void main(){
        TimeInterval ti = new TimeInterval(15, 30, 56);
        ti.printTime();
        System.out.println(ti.getTotalSeconds());

        TimeInterval ti2 = new TimeInterval(1000);
        ti2.printTime();
        System.out.println(ti2.getTotalSeconds());
    }
}
