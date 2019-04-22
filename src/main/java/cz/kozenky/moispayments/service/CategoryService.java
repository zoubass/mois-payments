package cz.kozenky.moispayments.service;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.codelist.Category;
import cz.kozenky.moispayments.model.codelist.CategoryList;
import cz.kozenky.moispayments.model.codelist.CategoryRule;
import cz.kozenky.moispayments.model.web_model.CategoryDto;
import cz.kozenky.moispayments.model.web_model.PaymentDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryList categoryList;
    
    public String saveCategory(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getName(), categoryDto.getId());
        CategoryRule rule = new CategoryRule();
        rule.setAccountId(categoryDto.getAccountId());
        rule.setBankAccountNumber(categoryDto.getAccountNumber());
        rule.setBankCode(categoryDto.getBankCode());
        category.setRule(rule);

        categoryList.addUserCategory(category);
        return "changeThisReturn";
    }

    public void saveRule(PaymentDto paymentDto) {
        CategoryRule rule = new CategoryRule();
        rule.setAccountId(paymentDto.getAccountId());
        rule.setBankAccountNumber(paymentDto.getAccountNumber());
        rule.setBankCode(paymentDto.getBankCode());
        
        Category category = categoryList.getByName(paymentDto.getCategory());
        if (category != null) {
            category.setRule(rule);
        } else {
            Category newCategory = new Category(paymentDto.getCategory(), new BigDecimal(Math.random()*123));
            newCategory.setRule(rule);
            categoryList.addUserCategory(newCategory);
        }
    }

    public List<CategoryDto> countCategoryLove (List<Category> catList, List<Payment> payList){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for(Category cat : catList){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(cat.getId());
            categoryDto.setName(cat.getName());
            categoryDto.setSummValue(new BigDecimal(0));
            categoryDtoList.add(categoryDto);
        }

        for (Payment pay : payList){
            BigDecimal payCatId = pay.getCategoryId();
            if (payCatId == null){
                for (CategoryDto categoryDto : categoryDtoList){
                    if(categoryDto.getId().equals(new BigDecimal(0))){
                        categoryDto.setSummValue(categoryDto.getSummValue().add(pay.getValue().getAmount()));
                        break;
                    }
                }
                continue;
            }

            for (CategoryDto categoryDto : categoryDtoList){
                if (categoryDto.getId().equals(payCatId)){
                    categoryDto.setSummValue(categoryDto.getSummValue().add(pay.getValue().getAmount()));
                    break;
                }
            }
        }
        return categoryDtoList;
    }
}
