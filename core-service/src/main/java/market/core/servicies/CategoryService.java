package market.core.servicies;

import lombok.RequiredArgsConstructor;
import market.core.repositories.CategoryRepository;
import market.core.entities.Category;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

}
