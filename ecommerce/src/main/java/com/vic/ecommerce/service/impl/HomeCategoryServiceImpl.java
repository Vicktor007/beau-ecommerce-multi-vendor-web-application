package com.vic.ecommerce.service.impl;

import com.vic.ecommerce.model.HomeCategory;
import com.vic.ecommerce.repository.HomeCategoryRepository;
import com.vic.ecommerce.service.HomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {

     @Autowired
 HomeCategoryRepository homeCategoryRepository;


    @Override
    public HomeCategory createCategory(HomeCategory categories) {

        return homeCategoryRepository.save(categories);

    }

    @Override
    public List<HomeCategory> createCategories(List<HomeCategory> categories) {
        if (homeCategoryRepository.findAll().isEmpty()) {
            return homeCategoryRepository.saveAll(categories);
        }
        return homeCategoryRepository.findAll();
    }

    @Override
    public List<HomeCategory> getAllCategories() {
        return homeCategoryRepository.findAll();
    }

    @Override
    public HomeCategory updateCategory(HomeCategory category, Long id) throws Exception {
        HomeCategory existingCategory = homeCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found"));
        if(category.getImage()!=null){
            existingCategory.setImage(category.getImage());
        }
        if(category.getCategoryId()!=null){
            existingCategory.setCategoryId(category.getCategoryId());
        }
        return homeCategoryRepository.save(existingCategory);
    }


}
