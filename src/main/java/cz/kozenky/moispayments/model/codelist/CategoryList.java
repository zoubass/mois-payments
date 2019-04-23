package cz.kozenky.moispayments.model.codelist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryList {

    private Category unclassified;

    private List<Category> categories;

    public CategoryList() {
        this.categories = new ArrayList<>();
        unclassified = new Category("Nezařazeno", new BigDecimal(0), new CategoryRule());
        categories.add(new Category("Elektronika", new BigDecimal(1.5), new CategoryRule()));
        categories.add(new Category("Jídlo & pití", new BigDecimal(2), new CategoryRule()));
        categories.add(new Category("Kosmetika & drogerie", new BigDecimal(3), new CategoryRule()));
        categories.add(new Category("Oblečení", new BigDecimal(4), new CategoryRule()));
        categories.add(new Category("Ostatní", new BigDecimal(5), new CategoryRule()));
        categories.add(unclassified);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getById(BigDecimal categoryId) {
        if (categoryId != null) {
            Optional<Category> foundCategory = categories.stream().filter(category -> category.getId().compareTo(categoryId) == 0).findAny();
            return foundCategory.orElseGet(() -> unclassified);
        }
        return unclassified;
    }

    public Category getByName(String name) {
        if (name != null) {
            Optional<Category> foundCategory = categories.stream().filter(category -> category.getName().equals(name)).findAny();
            return foundCategory.orElseGet(() -> unclassified);
        }
        return unclassified;
    }

    public Category resolveByRule(String bankAccount, String bankCode, BigDecimal accountId) {
        Optional<Category> foundCategory = Optional.empty();
        if (bankAccount != null && bankCode != null && accountId != null) {
            foundCategory = categories.stream()
                    .filter(category -> category.getRule().getAccountId().compareTo(accountId) == 0 && category.getRule().getBankCode()
                            .equals(bankCode) && category.getRule().getBankAccountNumber()
                            .equals(bankAccount)).findAny();
        }
        return foundCategory.orElseGet(() -> unclassified);
    }

    public List<Category> allValues() {
        return categories;
    }

    public boolean addUserCategory(Category category) {
        return categories.add(category);
    }

    public boolean removeUserCategory(Category category) {
        return categories.remove(category);
    }
}
