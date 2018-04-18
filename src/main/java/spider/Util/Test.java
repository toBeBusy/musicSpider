package spider.Util;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public <T> List<T> getType(T type){

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Integer> test = new Test().getType("");
    }
}
