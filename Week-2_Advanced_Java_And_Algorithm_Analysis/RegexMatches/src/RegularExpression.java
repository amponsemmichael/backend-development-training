import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegularExpression {
    public static void main(String[] args) {
        Pattern p = Pattern.compile(".m");
        Matcher m = p.matcher(".am");
        Boolean c = m.matches();
        System.out.println(c);
// note that lines 7 and 8 can be simplified as line 11
        boolean b = Pattern.matches(".m", "am");
        System.out.println(b);
        boolean d = Pattern.matches(".m", ".am");
        System.out.println(d);

        System.out.println(Pattern.matches("[amn]","[acd]"));
        System.out.println(Pattern.matches("[^amn]","[c]"));//compare any character leaving a,m or n
        System.out.println(Pattern.matches("[a-zA-S]","[T]"));
        System.out.println(Pattern.matches("[a-zA-S]","[P]"));
        System.out.println(Pattern.matches("[MS][a-z]{5}","Monica"));
        System.out.println(Pattern.matches("[xyz]?","x"));
        System.out.println(Pattern.matches("[xyz]+","x"));
        System.out.println(Pattern.matches("[xyz]*","xyza"));
        System.out.println(Pattern.matches("\\d","1"));
        System.out.println(Pattern.matches("\\D","1"));

    }
}
