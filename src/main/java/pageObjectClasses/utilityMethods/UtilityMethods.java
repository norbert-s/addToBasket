package pageObjectClasses.utilityMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilityMethods {
    private static final Logger logger = (Logger) LogManager.getLogger(UtilityMethods.class);
    public static HashMap addValuesAndIndexesToMap(List<WebElement> t){
        HashMap<Double,Integer> map = new HashMap<>();
        int j=0;
        for(WebElement i:t){
            map.put(Double.parseDouble(i.getText()),j);
            j++;
        }
        return map;
    }

    public static HashMap sortingMap(HashMap<Double,Integer>map){
        HashMap sorted =map.entrySet()
                .stream()
                .sorted((i1, i2)
                        -> i2.getKey().compareTo(
                        i1.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return sorted;
    }

    public static void creatingFinalLists(List<Double> finalPrice, List<Integer> finalIndex, HashMap<Double,Integer>sorted){
        logger.debug("ordered list");
        sorted.forEach((k,v)->{
            finalPrice.add(k);
            finalIndex.add(v);
            logger.debug("price:"+k+", index:"+v);
        });
    }

}
