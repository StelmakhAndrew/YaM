package project.com.Controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.com.Entity.Book;
import project.com.Entity.Genre;
import project.com.Service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Before
    public void init() {
        Book book = new Book();
        book.setName("book For testing");
        book.setId(1L);
        book.setRating(3.5f);
        book.setAuthor("Test author");
        book.setGenre(Genre.CLASSIC);
        when(bookService.findById(1L)).thenReturn(java.util.Optional.of(book));
    }

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get("/bookById/1")).andExpect(status().isOk());
//                .andExpect(content().string(containsString("book For testing")));
    }

}
