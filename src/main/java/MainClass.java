import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by xumepa on 11/21/17.
 */
public class MainClass {


    public static void main(String[] args) throws IOException {

        CsvReader reader = new CsvReader();
        List<Painting> paintings = reader.parse("/media/Storage/Code/University/Machine Learning/Java/catalog.xls - catalog.tsv");

        File mainFolder = new File("paintings");

        if(!mainFolder.exists()){
            mainFolder.mkdir();
        }

        // this will save the paintings
        for (Painting painting : paintings) {
            URL url =  new URL(painting.getPaintingUrl().replaceFirst("html","detail").replaceFirst("html","jpg"));
            File paintingFolder = new File(mainFolder + "/" + painting.getPaintingAuthor().trim());
            if(!paintingFolder.exists()){
                paintingFolder.mkdir();
            }
            String fileName = paintingFolder + "/" + painting.getPaintingName() + painting.getBornDied() + ".jpg";
            File file = new File(fileName);
            if(file.exists()){
                System.out.println("exists : " + fileName);
            }else{
                System.out.println(fileName);
            }
            try {
                ImageIO.write(ImageIO.read(url), "jpg", file);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
