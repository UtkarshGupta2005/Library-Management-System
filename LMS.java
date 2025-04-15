import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    class Book {
    private static int bookID;
    private static String title;
    private static String author;
    private static int copies;

    public Book(int bookID, String title, String author, int copies) {
        this.bookID=bookID;
        this.title=title;
        this.author=author;
        this.copies=copies;
    }

    public int getBookId() {
        return bookID;
    }

    public String getBookTitle() {
        return title;
    }

    public String getBookAuthor() {
        return author;
    }

    public int getCopies() {
        return copies;
    }

    public void decrCopies() {
        if(copies>0)
        copies--;
    }

    public void incrCopies() {
        copies++;
    }
}

    class Student {
    private static int stID;
    private static String name;
    private static String branch;
    private static int year;

    public Student(int stID, String name, String branch, int year) {
        this.stID=stID;
        this.name=name;
        this.branch=branch;
        this.year=year;
    }

    public int getId() {
        return stID;
    }

    public String studentName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public int getYear() {
        return year;
    }
}

    class checkOut {
    public Book book;
    public Student stud;
    public int days;

    public checkOut(Book book,Student stud,int days) {
        this.book=book;
        this.stud=stud;
        this.days=days;
    }

    public Book getBook() {
        return book;
    }

    public Student getStud() {
        return stud;
    }

    public int getDays() {
        return days;
    }
}

        class Library {
        public static List<Book> books;
        public static List<Student> students;
        public static List<checkOut> checkOuts;

        public Library() {
            books=new ArrayList<>();
            students=new ArrayList<>();
            checkOuts=new ArrayList<>();
        }

        public static void addBook(Book book) {
            books.add(book);
        }

        public static void addStudent(Student student) {
            students.add(student);
        }

        public static void borrowBook(Book book, Student stud, int days) {
            if(book.getCopies()>0) {
                book.decrCopies();
                checkOuts.add(new checkOut(book,stud,days));
                System.out.println("Book is borrowed");
            }
            else
            System.out.println("Book is available");
        }

        public static void returnBook(Book book, Student stud) {
            for(checkOut c : checkOuts ) {
                if(c.getBook().equals(book) && c.getStud().equals(stud)) {
                    book.incrCopies();
                    checkOuts.remove(c);
                    System.out.println("Book returned");
                }
            }
            System.out.println("No records");
        }

        public Book findBookById(int id) {
            for(Book B:books) {
                if(B.getBookId()==id)
                return B;
            }
            return null;
        }

        public Student findStudentById(int id) {
            for(Student S : students) {
                if(S.getId()==id)
                return S;
            }
            return null;
        }

        public static void display(int bookID) {
            boolean found=false;
            for(checkOut c:checkOuts) {
                if(c.getBook().getBookId()==bookID) {
                    Student s=c.getStud();
                    System.out.println("Student ID : "+s.getId());
                    System.out.println("Name : "+s.studentName());
                    System.out.println("Branch : "+s.getBranch());
                    System.out.println("Year : "+s.getYear());
                    System.out.println("Borrowed for : "+c.getDays()+" days");
                    found=true;
                }
            }
            if(!found)
            System.out.println("Book not issued");
        }
    }

    public class LMS {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Library library = new Library();

        Library.addBook(new Book(1,"java","james gosling",10));
        Library.addStudent(new Student(101,"Alice","CSE",2));

        
        while (true) { 
            System.out.println("\n----Library Menu----");
            System.out.println("1.Add Book");
            System.out.println("2.Add Student");
            System.out.println("3.Borrow Book");
            System.out.println("4.Return Book");
            System.out.println("5.Check number of copies");
            System.out.println("6.Details of student");
            System.out.println("Enter choice : ");
            int ch=sc.nextInt();

            switch(ch) {
                case 1:
                    System.out.print("Enter BookId, Title, Author, Copies : ");
                    int id=sc.nextInt();
                    sc.nextLine();
                    String title=sc.nextLine();
                    String author=sc.nextLine();
                    int copies=sc.nextInt();
                    library.addBook(new Book(id, title, author, copies));
                    break;
            

            case 2:
                    System.out.print("Enter StudentId, Name, Branch, Year : ");
                    int sid=sc.nextInt();
                    sc.nextLine();
                    String name=sc.nextLine();
                    String branch=sc.nextLine();
                    int year=sc.nextInt();
                    library.addStudent(new Student(sid, name, branch, year));
                    break;

            case 3:
                    System.out.print("Enter BookID and StudentId : ");
                    int bId=sc.nextInt();
                    int sId=sc.nextInt();
                    System.out.println("Enter number of days : ");
                    int days=sc.nextInt();
                    Book bookToBorrow=library.findBookById(bId);
                    Student studToBorrow=library.findStudentById(sId);
                    if(bookToBorrow!=null && studToBorrow!=null)
                    library.borrowBook(bookToBorrow,studToBorrow,days);
                    else
                    System.out.println("Invalid detail(s)");
                    break;

            case 4:
                    System.out.print("Enter BookID and StudentId : ");
                    int rBId=sc.nextInt();
                    int rSId=sc.nextInt();
                    Book bookToReturn=library.findBookById(rBId);
                    Student studToReturn=library.findStudentById(rSId);
                    if(bookToReturn!=null && studToReturn!=null)
                    library.returnBook(bookToReturn,studToReturn);
                    else
                    System.out.println("Invalid detail(s)");
                    break;

            case 5:
                System.out.println("Enter BookID : ");
                int BID=sc.nextInt();
                Book b=library.findBookById(BID);
                System.out.println("Number of copies left : "+b.getCopies());
                break;                
            
            case 6:
                System.out.println("Enter BookID : ");
                int ID=sc.nextInt();
                Library.display(ID);
                break;

            default:
                System.out.println("Invalid choice");
            }            
        }
    }
}