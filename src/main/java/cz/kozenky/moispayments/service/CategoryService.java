package cz.kozenky.moispayments.service;

import cz.kozenky.moispayments.model.codelist.Category;
import cz.kozenky.moispayments.model.codelist.CategoryList;
import cz.kozenky.moispayments.model.codelist.CategoryRule;
import cz.kozenky.moispayments.model.web_model.CategoryDto;
import cz.kozenky.moispayments.model.web_model.PaymentDto;
import java.math.BigDecimal;
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
}
