package cz.kozenky.moispayments.service;

import cz.kozenky.moispayments.model.Payment;
import cz.kozenky.moispayments.model.codelist.Category;
import cz.kozenky.moispayments.model.codelist.CategoryList;
import cz.kozenky.moispayments.model.codelist.CategoryRule;
import cz.kozenky.moispayments.model.web_model.CategoryDto;
import cz.kozenky.moispayments.model.web_model.Message;
import cz.kozenky.moispayments.model.web_model.PaymentDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CategoryService {

    @Autowired
    private CategoryList categoryList;

    public Message saveCategory(CategoryDto categoryDto) {
        Message message = new Message();

        if (validate(categoryDto, message)) {

            Category category = new Category(categoryDto.getName(), categoryDto.getId());
            CategoryRule rule = new CategoryRule();
            rule.setAccountId(categoryDto.getAccountId());
            rule.setBankAccountNumber(categoryDto.getAccountNumber());
            rule.setBankCode(categoryDto.getBankCode());
            category.setRule(rule);

            categoryList.addUserCategory(category);
            message.setMessage("Category added");
        }
        return message;
    }

    private boolean validate(CategoryDto categoryDto, Message message) {
        if (categoryDto.isEmpty()) {
            message.setMessage("Really? ...");
            return false;
        } else if (categoryDto.getAccountId() == null || StringUtils.isEmpty(categoryDto.getBankCode()) || StringUtils
                .isEmpty(categoryDto.getAccountNumber())) {
            message.setMessage("All parameters are mandatory! -.-");
            return false;
        } else if (categoryList.getCategories().stream().anyMatch(category -> category.getId().compareTo(categoryDto.getId()) == 0)) {
            message.setMessage("Category with id: " + categoryDto.getId() + " already exists.");
            return false;
        } else if (categoryList.getCategories().stream().anyMatch(category -> category.getName().equals(categoryDto.getName()))) {
            message.setMessage("Category with name: " + categoryDto.getName() + " already exists.");
            return false;
        } else if (categoryList.getCategories().stream().anyMatch(category -> checkRuleMatch(category, categoryDto))) {
            message.setMessage("Category with this rule exists");
            return false;
        }
        return true;

    }

    private boolean checkRuleMatch(Category category, CategoryDto categoryDto) {
        CategoryRule rule = category.getRule();
        return rule.getBankAccountNumber().equals(categoryDto.getAccountNumber()) && rule.getBankCode().equals(categoryDto.getBankCode())
                && rule.getAccountId().compareTo(categoryDto.getAccountId()) == 0;
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
            Category newCategory = new Category(paymentDto.getCategory(), new BigDecimal(Math.random() * 123));
            newCategory.setRule(rule);
            categoryList.addUserCategory(newCategory);
        }
    }

    public List<CategoryDto> countCategoryLove(List<Category> catList, List<Payment> payList) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category cat : catList) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(cat.getId());
            categoryDto.setName(cat.getName());
            categoryDto.setSummValue(new BigDecimal(0));
            categoryDtoList.add(categoryDto);
        }

        for (Payment pay : payList) {
            BigDecimal payCatId = pay.getCategoryId();
            if (payCatId == null) {
                for (CategoryDto categoryDto : categoryDtoList) {
                    if (categoryDto.getId().equals(new BigDecimal(0))) {
                        categoryDto.setSummValue(categoryDto.getSummValue().add(pay.getValue().getAmount()));
                        break;
                    }
                }
                continue;
            }

            for (CategoryDto categoryDto : categoryDtoList) {
                if (categoryDto.getId().equals(payCatId)) {
                    categoryDto.setSummValue(categoryDto.getSummValue().add(pay.getValue().getAmount()));
                    break;
                }
            }
        }
        return categoryDtoList;
    }
}
