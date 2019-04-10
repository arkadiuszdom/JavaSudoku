package progkomp.zad2;


import java.util.ListResourceBundle;

public class AuthorsResourceBundle extends ListResourceBundle {
    private Object[][] contents = {
            {"FirstAuthorFirstName", new String("Mateusz")},
            {"FirstAuthorLastName", new String("B")},
            {"FirstAuthorIndex", new String("2")},
            {"SecondAuthorFirstName", new String("Arkadiusz")},
            {"SecondAuthorLastName", new String("Domrat")},
            {"SecondAuthorIndex", new String("2")}
    };
    public Object[][] getContents() {
        return this.contents;
    }
    
    
}
