package edu.elnaazk.exercise8;

import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class BookShelfParser {

    public static void main(String[] args) throws Exception {
        // --- XML ---
        BookShelf xmlShelf = parseXml();
        printBooks("XML - original", xmlShelf.getBooks());

        xmlShelf.addBook(
                new Book(
                        "To Kill a Mockingbird",
                        1960,
                        281,
                        List.of("Harper Lee")
                )

        );
        printBooks("XML - after adding one book", xmlShelf.getBooks());

        System.out.println();

        // --- JSON ---
        BookShelf jsonShelf = parseJson();
        printBooks("JSON - original", jsonShelf.getBooks());

        jsonShelf.addBook(
                new Book(
                        "Java Concurrency in Practice",
                        2006,
                        384,
                        List.of("Brian Goetz", "Tim Peierls")
                )
        );
        printBooks("JSON - after adding one book", jsonShelf.getBooks());
    }

    // Parse XML from resources/books.xml
    private static BookShelf parseXml() throws Exception {
        BookShelf shelf = new BookShelf();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        try (InputStream is = BookShelfParser.class.getResourceAsStream("/books.xml")) {
            if (is == null) {
                throw new IllegalStateException("books.xml not found in resources folder");
            }

            Document doc = builder.parse(is);
            NodeList bookNodes = doc.getElementsByTagName("book");

            for (int i = 0; i < bookNodes.getLength(); i++) {
                Element bookElement = (Element) bookNodes.item(i);

                String title = getTagText(bookElement, "title");
                int year = Integer.parseInt(getTagText(bookElement, "publishedYear"));
                int pages = Integer.parseInt(getTagText(bookElement, "numberOfPages"));

                List<String> authors = new ArrayList<>();
                NodeList authorNodes = bookElement.getElementsByTagName("author");
                for (int j = 0; j < authorNodes.getLength(); j++) {
                    authors.add(authorNodes.item(j).getTextContent());
                }

                shelf.addBook(new Book(title, year, pages, authors));
            }
        }

        return shelf;
    }

    private static String getTagText(Element parent, String tagName) {
        return parent.getElementsByTagName(tagName).item(0).getTextContent();
    }

    // Parse JSON from resources/books.json
    private static BookShelf parseJson() throws Exception {
        Gson gson = new Gson();

        try (InputStream is = BookShelfParser.class.getResourceAsStream("/books.json")) {
            if (is == null) {
                throw new IllegalStateException("books.json not found in resources folder");
            }

            try (Reader reader = new InputStreamReader(is)) {
                return gson.fromJson(reader, BookShelf.class);
            }
        }
    }

    private static void printBooks(String label, List<Book> books) {
        System.out.println("---- " + label + " ----");
        for (Book b : books) {
            System.out.println(b);
        }
    }
}