package hello.inven.helloinven.serviceimpl;

import hello.inven.helloinven.exceptionhandler.NotFoundException;
import hello.inven.helloinven.model.Category;
import hello.inven.helloinven.response.ResponseAjax;
import hello.inven.helloinven.repository.CategoryRepository;
import hello.inven.helloinven.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//https://javabeginnerstutorial.com/spring-boot/making-spring-boot-thymeleaf-crud-application/

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories(){ return categoryRepository.findAll(); }

    @Override
    public Category getOneCategory(Integer id){
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) return category;
        else throw new NotFoundException("Category not found!");
    }

    @Override
    public Category createCategory(Category category){
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());
        newCategory = categoryRepository.save(newCategory);
        return newCategory;
    }

    @Override
    public Category editCategory(Category categoryRequest, Integer id){
        // https://stackoverflow.com/questions/49316751/spring-data-jpa-findone-change-to-optional-how-to-use-this
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null){
            category.setName(categoryRequest.getName());
            category.setDescription(categoryRequest.getDescription());
            category = categoryRepository.saveAndFlush(category);
            return category;
        }
        else throw new NotFoundException("Category edit Failed!");
    }

    @Override
    public ResponseAjax deleteCategory(Integer id){
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.deleteById(id);
            return new ResponseAjax("Deleted", "Category has been successfully deleted");
        }
//        return new ResponseAjax("Failed", "Category failed to be deleted!");
        else throw new NotFoundException("Category not found and failed to be deleted!");
    }

}
