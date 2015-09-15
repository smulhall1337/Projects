package map;

/**
 * A Book has a title (String), an author (String), and a number of pages (int)
 * 
 * @author (Sean Mulhall) 
 * @version (12.10.2014)
 */
public class Book
{
    String title;
    String author;
    int pages;

    public Book(String title, String author, int pages)
    {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public int getPages()
    {
        return pages;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getTitle()
    {
        return title;
    }

    /**
     * a book is considered equal if they have the same title
     */
    public boolean equals(Object object)
    {
        if (object instanceof Book)
        {
            Book other = (Book) object;
            if (other.title.equals(title))
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        return title;
    }

    public int hashCode()
    {
        String result = title;
        return result.hashCode();
    }
}
