import model.Author;
import model.Book;
import model.Genre;
import model.Library;
import repository.impl.BookRepositoryImpl;
import util.JAXBLibrarySerializer;
import util.pool.ConnectionPool;
import util.pool.DBCPPool;
import util.pool.Pools;

import javax.xml.bind.JAXBException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws JAXBException, SQLException {
//        Library library = JAXBLibrarySerializer.unmarshal("D:/Library/src/main/resources/library_storage.xml");
//        for (Book book : library.getBooks()){
//            System.out.println(book);
//        }
//        Book book1 = Book.builder()
//                .id(1)
//                .name("Airport")
//                .authors(Arrays.asList(
//                        Author.builder()
//                                .id(1)
//                                .name("Arthur")
//                                .surname("Hailey")
//                                .build(),
//                        Author.builder()
//                                .id(2)
//                                .name("Tom")
//                                .surname("Hardy")
//                                .build()
//                ))
//                .genres(Arrays.asList(
//                        Genre.builder()
//                                .id(1)
//                                .name("adventure")
//                                .build(),
//                        Genre.builder()
//                                .id(2)
//                                .name("Detective")
//                                .build()
//                ))
//                .isbn("123456")
//                .date(new Date())
//                .build();
//
//        Book book2 = Book.builder()
//                .id(1)
//                .name("The Story of Doctor Dolittle")
//                .authors(Arrays.asList(
//                        Author.builder()
//                                .id(1)
//                                .name("Hugh")
//                                .surname("Lofting")
//                                .build(),
//                        Author.builder()
//                                .id(2)
//                                .name("Alan")
//                                .surname("Milne")
//                                .build()
//                ))
//                .genres(Arrays.asList(
//                        Genre.builder()
//                                .id(1)
//                                .name("Fantasy")
//                                .build(),
//                        Genre.builder()
//                                .id(2)
//                                .name("Fairy tale")
//                                .build()
//                ))
//                .isbn("123456")
//                .date(new Date())
//                .build();
//        Library library = Library.builder()
//                .books(Arrays.asList(book1, book2))
//                .build();
//        JAXBLibrarySerializer.marshal(library, "D:/Library/src/main/resources/library_storage.xml");

        ConnectionPool cp = Pools.newPool(DBCPPool.class);
//        for (int i=0; i<100; i++) {
//            try(Connection connection = cp.getConnection()){
//                System.out.println(i + " " + connection);
//            }
//        }

        Library library = Library.builder()
                .books(new BookRepositoryImpl().getAll())
                .build();
        JAXBLibrarySerializer.marshal(library, "D:/Library/src/main/resources/library_storage.xml");

    }
}
