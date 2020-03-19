package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repos.AuthorRepository;
import guru.springframework.spring5webapp.repos.BookRepository;
import guru.springframework.spring5webapp.repos.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.transform.sax.SAXSource;

@Component
public class BootstrapData implements CommandLineRunner  {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author max = new Author("Max", "Smith");
        Book book1 = new Book("bla bla By Max Smith", "12345");
        Publisher publisher = new Publisher("ORelly", "11789  St Peak Dr, San Diego,CA ");

        max.getBooks().add(book1);
        book1.getAuthors().add(max);
        authorRepository.save(max);
        bookRepository.save(book1);

        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);

        publisherRepository.save(publisher);

        Author joash = new Author("Joash", "Block");
        Book book2 = new Book("bla bla By Joash Block", "12346");
        max.getBooks().add(book2);
        book2.getAuthors().add(joash);
        publisher.getBooks().add(book2);
        book2.setPublisher(publisher);
        authorRepository.save(joash);
        bookRepository.save(book2);
        publisherRepository.save(publisher);

        System.out.println("Strated in Bootstrap");
        System.out.println("number of books " + bookRepository.count());
        System.out.println("number of publishers books " + publisher.getBooks().size());
    }
}
