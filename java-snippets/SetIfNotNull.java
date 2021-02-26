import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.function.Consumer;


public class SetIfNotNull {


    public static <V> void setIfNotNull(V value, Consumer<V> setter) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        }
    }

    public static void main(String []args) {
        Movie movie = new Movie();
        String title = "Star Wars";
        LocalDate releaseDate = LocalDate.of( 1977 , Month.DECEMBER , 23 );


        setIfNotNull(title, movie::setTitle);
        setIfNotNull(releaseDate, movie::setReleaseDate);

        System.out.println(movie);

        title = null;
        releaseDate = null;

        setIfNotNull(title, movie::setTitle);
        setIfNotNull(releaseDate, movie::setReleaseDate);

        System.out.println(movie);

        title = "The Empire Strikes Back";
        releaseDate = LocalDate.of( 1980 , Month.DECEMBER , 25 );

        setIfNotNull(title, movie::setTitle);
        setIfNotNull(releaseDate, movie::setReleaseDate);

        System.out.println(movie);
    }

}


class Movie {
    private String title;
    private LocalDate releaseDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
