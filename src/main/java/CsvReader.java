import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xumepa on 11/21/17.
 */
public class CsvReader {

    private int minPaintingPerAuthor = 150;

    public List<Painting> parse(String filePath) {
        Stream<String> lines = null;
        List<Painting> returnPaintings = null;
        try {
            lines = Files.lines(Paths.get(filePath));

            List<Painting> allPaintings = lines.map(mapToItem).collect(Collectors.toList());

            returnPaintings = filterCommonAuthors(allPaintings);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(lines != null) {
                lines.close();
            }
        }
        return returnPaintings;
    }

    private List<Painting> filterCommonAuthors(List<Painting> allPaintings) {

        List<String> a = allPaintings.parallelStream().filter(Objects::nonNull).filter(e-> Objects.equals(e.getType(), "painting"))
                .collect(Collectors.groupingBy(Painting::getPaintingAuthor,Collectors.counting()))
                .entrySet().parallelStream().filter(e->e.getValue()>minPaintingPerAuthor).map(Map.Entry::getKey).collect(Collectors.toList());

//       Stream<Painting> cc = allPaintings.stream().filter(e->a.contains(e.getPaintingName()));
//        System.out.println(cc.count());

//       .forEach(ac-> System.out.println(ac));//.collect(Collectors.toList());//.collect(Collectors.toList());


        List<Painting> listOutput =
                allPaintings.stream().filter(Objects::nonNull)
                        .filter(e -> a.stream().anyMatch(name -> name.equals(e.getPaintingAuthor())))
                        .collect(Collectors.toList());

       return listOutput;

    }

    Function<String, Painting> mapToItem = (line) -> {
        try{
            String[] p = line.split("\\t");// a CSV has comma separated lines
            Painting item = new Painting();
            item.setPaintingAuthor(p[0]);//<-- this is the first column in the csv file
            item.setPaintingName(p[2]);
            item.setType(p[7]);
            item.setPaintingUrl(p[6]);
            item.setBornDied(p[1]);
            return item;
        }
        catch(Exception e){
//            e.printStackTrace();
            return null;
        }
    };
}
