import java.util.regex.Pattern;

public class RegularExpressionExample2 {
    public static void main(String[] args) {

        //if the regular expression accepts 6 characters starting with m or s
        System.out.println(Pattern.matches("[MS][a-z]{5}","Munir"));
        System.out.println(Pattern.matches("[MS][a-z]{5}","Samed"));
        System.out.println(Pattern.matches("[MS][a-z]{5}","Occay"));
        System.out.println();

        //check if x occurs ones or not at all
        System.out.println(Pattern.matches("[xyz]+","x"));
        System.out.println(Pattern.matches("[xyz]+","xxx"));
        System.out.println(Pattern.matches("[xyz]+","xyyzz"));
        System.out.println();

        //[x]+, x occurs ones or more times
        System.out.println(Pattern.matches("[xyz]+","x"));
        System.out.println(Pattern.matches("[xyz]+","xxxxx"));
        System.out.println(Pattern.matches("[xyz]+","xyyzz"));
        System.out.println();

        //[x]*, x occurs 0 or more times
        System.out.println(Pattern.matches("[xyz]*","x"));
        System.out.println();

        //"\d" , if the character sequence consist of any digit
        System.out.println(Pattern.matches("\\d","1"));
        System.out.println(Pattern.matches("\\d","1a"));
        System.out.println();

        //"\\D" check for any character
        System.out.println(Pattern.matches("\\D","1"));
        System.out.println(Pattern.matches("\\D","2a"));
        System.out.println(Pattern.matches("\\D","7"));
        System.out.println();

        // to match length of characters
        System.out.println(Pattern.matches("[a-zA-z0-9]{6}", "franca"));

    }
}
