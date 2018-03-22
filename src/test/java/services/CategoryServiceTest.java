//
// package services;
//
// import java.util.Collection;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.util.Assert;
//
// import utilities.AbstractTest;
// import domain.Category;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = {
// "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
// })
// @Transactional
// public class CategoryServiceTest extends AbstractTest {
//
// // Service under test
//
// @Autowired
// private CategoryService categoryService;
//
//
// // Tests
//
// @Test
// public void testCreateCategory() {
// // Setting up the authority to execute services.
// this.authenticate("admin");
//
// // Using create() to initialise a new entity. Necessary Id's taken from
// // populated database.
// final Category category = this.categoryService.create();
// category.setParentID(2812);
// category.setName("category10");//category9
// /*
// * Collection<Trip> trips = new ArrayList<>();
// * category.setTrip(trips);
// */
// // Saving entity to database and confirming it exists with findAll().
// final Category saved = this.categoryService.save(category);
//
// final Collection<Category> categories = this.categoryService.findAll();
// Assert.isTrue(categories.contains(saved));
//
// }
//
// @Test
// public void testListDeleteCategory() {
// // Setting up the authority to execute services.
// this.authenticate("admin");
//
// // We retrieve a list of all notes, and obtain the Id of one of them.
// final Collection<Category> categories = this.categoryService.findAll();
// final int id = categories.iterator().next().getId();
//
// // Using findOne() to retrieve a particular entity and verifying it.
// final Category category = this.categoryService.findOne(id);
// Assert.notNull(category);
//
// // Using delete() to delete the entity we retrieved.
//
// this.categoryService.delete(category);
//
// final Category bbdd = this.categoryService.findOne(category.getId());
// Assert.isNull(bbdd);
// }
//}
