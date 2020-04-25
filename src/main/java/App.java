import java.util.Date;

import org.hibernate.Session;

import com.rtpl.stock.Category;
import com.rtpl.stock.Stock;
import com.rtpl.stock.StockCategory;
import com.rtpl.util.HibernateUtil;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate many to many - join table + extra column (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
        stock.setStockCode("3456");
        stock.setStockName("sanvi");
 
        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        //new category, need save to get the id first
        session.save(category1);
        
        //Category category1 = (Category)session.get(Category.class, 8);
        
        StockCategory stockCategory = new StockCategory();
        
        stockCategory.setStock(stock);
        stockCategory.setCategory(category1);
        stockCategory.setCreatedDate(new Date());
        stockCategory.setCreatedBy("system");
        
        stock.getStockCategories().add(stockCategory);
        
        session.save(stock);
       
		session.getTransaction().commit();
		System.out.println("Done");
	}
}
