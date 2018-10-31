package hello.inven.helloinven.ServiceImpl;

import hello.inven.helloinven.model.Category;
import hello.inven.helloinven.model.ResponseAjax;
import hello.inven.helloinven.repository.CategoryRepository;
import hello.inven.helloinven.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//https://javabeginnerstutorial.com/spring-boot/making-spring-boot-thymeleaf-crud-application/

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories(){ return categoryRepository.findAll(); }

    @Override
    public Optional<Category> getOneCategory(Integer id){
        return categoryRepository.findById(id);
    }
    @Override
    public Category createCategory(Category category){
//        Category newCategory = new Category();
//        newCategory.setName(category.getName());
//        newCategory.setDescription(category.getDescription());
//        return categoryRepository.save(newCategory);
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public ResponseAjax deleteCategory(Integer id){
        ResponseAjax responseAjax = new ResponseAjax(null, null);
        Optional<Category> category = categoryRepository.findById(id);
        if (category != null) {
            categoryRepository.deleteById(id);
            responseAjax.setStatus("Deleted");
            responseAjax.setData("Category has been successfully deleted");
        }
        else {
            responseAjax.setStatus("Failed");
            responseAjax.setData("Category failed to be deleted!");
        }
        return responseAjax;
    }

}
