
public class GenericClass <P1, P2>{
    public void display(P1 var1, P2 var2) {
        System.out.println("Name:"+var1+", ID:"+var2);
    }

    public static void main(String[] args) {
        GenericClass<String,Integer> obj1 = new GenericClass<String,Integer>();
        obj1.display("Michael", 204);
        System.out.println();
        obj1.display("Micky", 205);
        System.out.println();
        obj1.display("McKay", 206);
    }
}
