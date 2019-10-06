package officeTest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called officeRepository
// CRUD refers Create, Read, Update, Delete

public interface OfficeRepository extends CrudRepository<Office, Integer> {

    List<Office> findOfficesByTitle(String title);

    void deleteOfficeByTitle(String title);

}
