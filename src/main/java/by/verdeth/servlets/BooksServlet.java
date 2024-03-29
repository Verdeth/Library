package by.verdeth.servlets;

import by.verdeth.dao.bookDao.BookDaoImplSingleton;
import by.verdeth.dao.genreDao.GenreDaoImplSingleton;
import by.verdeth.models.Book;
import by.verdeth.models.Genre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet ("/books")
public class BooksServlet extends HttpServlet {

    @Override
    public void init(){
        //connect database

//        DriverManagerDataSource dataSource;
//
//        try {
//            //CreateDataSource createDataSource = new CreateDataSource();
//            dataSource =  CreateDataSource.getInstance().getDriverManagerDataSource();
//
//            genreDao = new GenreDaoJdbcImpl(dataSource);
//            bookDao = new BookDaoJdbcImpl(dataSource);
//        }
//        catch (Exception ex)
//        {
//            throw new IllegalStateException(ex);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //give list of genres from database
        List<Genre> genres = GenreDaoImplSingleton.getInstance().getGenreDao().findAll();
        //        genreDao.findAll();

        //set attribute for jsp
        req.setAttribute("genresFromServer", genres);

        List<Book> books = BookDaoImplSingleton.getInstance().getBookDao().findAll();
        //        bookDao.findAll();
        req.setAttribute("booksFromServer", books);
        HttpSession session = req.getSession();
        session.removeAttribute("nameUser");
        //show jsp
        req.getServletContext().getRequestDispatcher("/jsp/books.jsp").forward(req,resp);
    }
}
