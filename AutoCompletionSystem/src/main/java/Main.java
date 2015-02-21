import java.io.File;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Main {



    public static void main(String[] args){
        File propertiesFile = new File("src/main/java/resources/autocompletionsystem.properties");
        System.out.println(propertiesFile.exists());
    }
}
